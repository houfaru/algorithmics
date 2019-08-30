package com.algorithmics.np.SAT.instance.CNF;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.algorithmics.np.SAT.instance.Sentence;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.util.Symbol;

public class SentenceInCNF extends Sentence {

    private List<Clause> clauses;

    @Override
    public HashSet<Variable> getVariables() {
        HashSet<Variable> varSet = new HashSet<Variable>();
        clauses.forEach(c -> varSet.addAll(c.getVariables()));
        return varSet;
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public SentenceInCNF(List<Clause> clauses) {
        super();
        this.clauses = clauses;
    }

    public void removeClause(Clause c) {
        if(clauses.contains(c)) {
            clauses.remove(c);
        }
    }
    
    /**
     * We define TRUE sentence as a sentence in CNF with no clauses
     * 
     * @return
     */
    public static SentenceInCNF constructMinimalTrueSentence() {
        return new SentenceInCNF(new ArrayList<Clause>());
    }


    /**
     * We define FALSE sentence as a sentence in CNF with one empty clause
     * 
     * @return
     */
    public static SentenceInCNF constructMinimalFalseSentence() {
        ArrayList<Clause> cl = new ArrayList<Clause>();
        cl.add(Clause.constructFalseClause());
        return new SentenceInCNF(cl);
    }

    public boolean containsEmptyClause() {
        return clauses.stream().anyMatch(c -> c.isEmptyClause());

    }

    public Optional<Boolean> isTautology() {
        if (containsEmptyClause()) {
            return Optional.of(false);
        }
        if (clauses.isEmpty()) {
            return Optional.of(true);
        }
        return Optional.empty();
    }    

    public List<Clause> getUnitClauses() {
        return clauses.stream().filter(c -> c.getLiterals().size() == 1)
                .collect(Collectors.toList());
    }

    public SentenceInCNF assignOneVariableAndReduce(Variable variable, boolean value) {
        SentenceInCNF scnf = SentenceInCNF.constructMinimalTrueSentence();
        for (Clause c : clauses) {
            Clause newClause = c.clone();
            newClause.assignOneVariableAndReduce(variable, value);
            if (!newClause.isTrueClause() && !newClause.isEmptyClause()) {
                scnf.getClauses().add(newClause);
            }
            if (newClause.isEmptyClause()) {
                return SentenceInCNF.constructMinimalFalseSentence();
            }
        }
        return scnf;
    }

    @Override
    public boolean verify(VariableAssignment certificate) {
        for (Clause c : clauses) {
            boolean v = c.verify(certificate);
            if (!v) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (isTautology().isPresent() && isTautology().get()) {
            return "TRUE";
        }
        if (isTautology().isPresent() && !isTautology().get()) {
            return "FALSE";
        }
        return clauses.stream().map(c -> c.toString())
                .reduce((a, b) -> a + " " + Symbol.AND + " " + b).get();
    }

    public String toDimacsFile() {
        String pathString = "data/tempIn.dimacs";
        Path path = Paths.get(pathString);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            String newCNFContent =
                    "p cnf " + this.getVariables().size() + " " + this.getClauses().size() + "\n";
            for (Clause c : this.clauses) {
                String clauseString = "";
                for (Literal l : c.getLiterals()) {
                    String varString = l.getVariables().stream().findAny().get().toString();

                    String negatedSymbol = l.isNegated() ? "-" : "";
                    clauseString += negatedSymbol;
                    clauseString += varString;
                    clauseString += " ";
                }
                clauseString += "0\n";
                newCNFContent += clauseString;

            }
            writer.write(newCNFContent);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pathString;
    }
}
