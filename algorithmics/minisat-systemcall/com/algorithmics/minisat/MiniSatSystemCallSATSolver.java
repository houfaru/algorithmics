package com.algorithmics.minisat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.instance.tree.SentenceTree;
import com.algorithmics.np.SAT.preprocessor.SATParser;
import com.algorithmics.np.SAT.util.SentenceUtil;
import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.UserExecutionException;

/**
 * This class depends on minisat {@link http://minisat.se/}<br>
 *
 */
@SolverMapping(name = "SAT_SOLVER_MINISAT", fileExtensions = "cnf")
public class MiniSatSystemCallSATSolver implements Solver<SentenceInCNF, VariableAssignment> {
    private String outputFilePath = "out.txt";

    @Override
    public Optional<VariableAssignment> solve(SentenceInCNF sentence) throws UserExecutionException {
        String dimacsFile = sentence.toDimacsFile();
        try {
            Process process =
                    Runtime.getRuntime().exec("minisat " + dimacsFile + " " + outputFilePath);
            BufferedReader stdInput =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            System.out.println("Here is the standard output of the command:\n");
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            return interpretOutput();
        } catch (IOException e) {
            throw new UserExecutionException(e);
        }

    }


    public Optional<VariableAssignment> solve(String dimacsFile) throws UserExecutionException {

        try {
            Process process =
                    Runtime.getRuntime().exec("minisat " + dimacsFile + " " + outputFilePath);
            BufferedReader stdInput =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError =
                    new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            return interpretOutput();
        } catch (IOException e) {
            throw new UserExecutionException(e);
        }

    }

    public Optional<VariableAssignment> interpretOutput() {
        VariableAssignment va = VariableAssignment.constructEmptyAssignment();
        try (Stream<String> stream = Files.lines(Paths.get(outputFilePath))) {

            List<String> lines = stream.collect(Collectors.toList());
            String result = lines.get(0);
            if (result.equals("SAT")) {
                System.out.println(lines);
                String string = lines.get(1);
                Arrays.stream(string.trim().split("\\S++")).map(s -> Integer.parseInt(s))
                        .forEach(intVal -> {
                            if (intVal != 0) {
                                Variable v = new Variable(Math.abs(intVal) + "");
                                va.assign(v, intVal > 0);
                            }

                        });
                return Optional.of(va);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean verify(SentenceInCNF p, VariableAssignment sc) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public SentenceInCNF getProblem(String problemString) throws UserExecutionException {
        final SATParser parser = new SATParser();
        final SentenceTree s = parser.parse(problemString);
        return SentenceUtil.toCNF(s);
    }

}
