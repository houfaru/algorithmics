package com.algorithmics.np.SAT.solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.instance.tree.SentenceTree;
import com.algorithmics.np.SAT.preprocessor.SATParser;
import com.algorithmics.np.core.Solver;

@SolverMapping(name = "SAT_SOLVER_RECURSIVE", fileExtension = "cnf")
public class SATSolverRecursive implements Solver<SentenceInCNF, VariableAssignment> {
    /**
     * Solves a SAT instance as follows:
     * 
     * 1. remove pure literals<br>
     * 2. get one of the variables<br>
     * 3. branch into two recursive calls by assigning TRUE and FALSE to the acquired variable<br>
     */
    @Override
    public Optional<VariableAssignment> solve(SentenceInCNF sentence) {
        SentenceInCNF newSentence = new SentenceInCNF(sentence.getClauses());
        VariableAssignment pureLiteralAssignment = removePureLiterals(sentence);

        final List<Clause> unitClauses = newSentence.getUnitClauses();
        final VariableAssignment unitVA = new VariableAssignment();
        for (Clause c : unitClauses) {
            final Literal literal = c.getLiterals().stream().findAny().get();
            newSentence = newSentence.assignOneVariableAndReduce(
                    literal.getVariables().stream().findAny().get(), !literal.isNegated());
            unitVA.assign(literal.getVariable(), !literal.isNegated());
        }

        if (newSentence.getClauses().size() == 0) {
            return null == pureLiteralAssignment
                    ? Optional.of(VariableAssignment.constructEmptyAssignment())
                    : Optional.of(pureLiteralAssignment);
        }
        if (newSentence.containsEmptyClause()) {
            return Optional.empty();
        }

        final HashSet<Variable> variables = newSentence.getVariables();
        final Variable oneOfTheVariable = variables.stream().findAny().get();

        final SentenceInCNF firstReducedSentence =
                newSentence.assignOneVariableAndReduce(oneOfTheVariable, true);
        final Optional<VariableAssignment> firstSentenceSolution = solve(firstReducedSentence);
        if (firstSentenceSolution.isPresent()) {
            firstSentenceSolution.get().assign(oneOfTheVariable, true);
            firstSentenceSolution.get().assignAll(unitVA);
            firstSentenceSolution.get().assignAll(pureLiteralAssignment);
            return firstSentenceSolution;
        }

        final SentenceInCNF secondReducedSentence =
                newSentence.assignOneVariableAndReduce(oneOfTheVariable, false);
        final Optional<VariableAssignment> secondSentenceSolution = solve(secondReducedSentence);
        if (secondSentenceSolution.isPresent()) {
            secondSentenceSolution.get().assign(oneOfTheVariable, false);
            secondSentenceSolution.get().assignAll(unitVA);
            firstSentenceSolution.get().assignAll(pureLiteralAssignment);
            return secondSentenceSolution;
        }

        return Optional.empty();
    }

    @Override
    public Optional<VariableAssignment> solveForDefaultFormat(String sentence) {
        final SATParser parser = new SATParser();
        final SentenceTree s = parser.parse(sentence);
        final SentenceInCNF p = s.toCNF();
        return solve(p);
    }

    public VariableAssignment removePureLiterals(SentenceInCNF sentence) {
        VariableAssignment va = new VariableAssignment();

        List<Clause> newClauses = new ArrayList<Clause>(sentence.getClauses());
        List<Literal> literals = newClauses.stream().flatMap(c -> c.getLiterals().stream())
                .distinct().collect(Collectors.toList());
        Set<Literal> negatedLiterals =
                literals.stream().filter(l -> l.isNegated()).collect(Collectors.toSet());
        Set<Literal> nonNegatedLiterals =
                literals.stream().filter(l -> !l.isNegated()).collect(Collectors.toSet());
        List<Literal> pureLiterals = literals.stream()
                .filter(l -> !negatedLiterals.contains(l) || !nonNegatedLiterals.contains(l))
                .collect(Collectors.toList());
        negatedLiterals.retainAll(pureLiterals);
        nonNegatedLiterals.retainAll(pureLiterals);
        for (Literal literal : negatedLiterals) {
            va.assign(literal.getVariable(), Boolean.FALSE);
            for (Clause clause : newClauses) {
                if (clause.getLiterals().contains(literal)) {
                    sentence.removeClause(clause);
                }
            }
        }
        for (Literal literal : nonNegatedLiterals) {
            va.assign(literal.getVariable(), Boolean.TRUE);
            for (Clause clause : newClauses) {
                if (clause.getLiterals().contains(literal)) {
                    sentence.removeClause(clause);
                }
            }
        }
        if (va.size() == 0) {
            return null;
        }
        return va;
    }

    @Override
    public boolean verify(SentenceInCNF sentence, VariableAssignment variableAssignment) {
        return sentence.verify(variableAssignment);

    }

}
