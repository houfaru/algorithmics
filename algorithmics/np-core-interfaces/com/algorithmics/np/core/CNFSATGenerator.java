package com.algorithmics.np.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;

public class CNFSATGenerator {
    public static SentenceInCNF generate() {
        List<Clause> clauses = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            List<Literal> literals = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {
                Literal l = new Literal(new Variable(String.valueOf((int) (Math.random() * 200))));
                l.setNegated(Math.random() < 0.5);
                literals.add(l);
            }

            Clause c = new Clause(literals);
            clauses.add(c);

        }
        return new SentenceInCNF(clauses);
    }

    @Test
    public void test() {
        System.out.println("started");
        long current=System.currentTimeMillis();
        SentenceInCNF cnf = generate();
        SATSolverRecursive solver = new SATSolverRecursive();
        Optional<VariableAssignment> solve = solver.solve(cnf);
        System.out.println("finished in" + (System.currentTimeMillis()-current)+" ms ");
    }
}
