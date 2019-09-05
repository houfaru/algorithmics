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
import com.algorithmics.np.SAT.util.SentenceUtil;
import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.UserExecutionException;

@SolverMapping(name = "SAT_SOLVER_RECURSIVE", fileExtensions = {"cnf"})
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
            newSentence = assignOneVariableAndReduce(newSentence,
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
                assignOneVariableAndReduce(newSentence, oneOfTheVariable, true);
        final Optional<VariableAssignment> firstSentenceSolution = solve(firstReducedSentence);
        if (firstSentenceSolution.isPresent()) {
            firstSentenceSolution.get().assign(oneOfTheVariable, true);
            firstSentenceSolution.get().assignAll(unitVA);
            firstSentenceSolution.get().assignAll(pureLiteralAssignment);
            return firstSentenceSolution;
        }

        final SentenceInCNF secondReducedSentence =
                assignOneVariableAndReduce(newSentence, oneOfTheVariable, false);
        final Optional<VariableAssignment> secondSentenceSolution = solve(secondReducedSentence);
        if (secondSentenceSolution.isPresent()) {
            secondSentenceSolution.get().assign(oneOfTheVariable, false);
            secondSentenceSolution.get().assignAll(unitVA);
            secondSentenceSolution.get().assignAll(pureLiteralAssignment);
            return secondSentenceSolution;
        }

        return Optional.empty();
    }

    @Override
    public Optional<VariableAssignment> solveForDefaultFormat(String sentence)
            throws UserExecutionException {
        return solve(getProblem(sentence));
    }

    public VariableAssignment removePureLiterals(SentenceInCNF sentence) {
        VariableAssignment va = new VariableAssignment();

        List<Clause> newClauses = new ArrayList<Clause>(sentence.getClauses());
        List<Literal> literals = newClauses.stream().flatMap(c -> c.getLiterals().stream())
                .distinct().collect(Collectors.toList());
        Set<Variable> negatedVars = literals.stream().filter(l -> l.isNegated())
                .map(l -> l.getVariable()).collect(Collectors.toSet());
        Set<Variable> nonNegatedVars = literals.stream().filter(l -> !l.isNegated())
                .map(l -> l.getVariable()).collect(Collectors.toSet());
        List<Variable> pureVarLiterals = literals.stream()
                .filter(l -> !negatedVars.contains(l.getVariable())
                        || !nonNegatedVars.contains(l.getVariable()))
                .map(l -> l.getVariable()).collect(Collectors.toList());
        negatedVars.retainAll(pureVarLiterals);
        nonNegatedVars.retainAll(pureVarLiterals);
        for (Variable var : negatedVars) {
            va.assign(var, Boolean.FALSE);
            for (Clause clause : newClauses) {
                if (clause.getLiterals().contains(Literal.negatedLiteral(var))) {
                    sentence.removeClause(clause);
                }
            }
        }
        for (Variable var : nonNegatedVars) {
            va.assign(var, Boolean.TRUE);
            for (Clause clause : newClauses) {
                if (clause.getLiterals().contains(Literal.nonNegatedLiteral(var))) {
                    sentence.removeClause(clause);
                }
            }
        }
        return va;
    }

    public SentenceInCNF assignOneVariableAndReduce(SentenceInCNF cnfSentence, Variable variable,
            boolean value) {
        SentenceInCNF scnf = SentenceInCNF.constructMinimalTrueSentence();
        for (Clause c : cnfSentence.getClauses()) {
            Clause newClause = c.clone();
            newClause = assignOneVariableAndReduce(newClause, variable, value);
            if (!newClause.isTrueClause() && !newClause.isEmptyClause()) {
                scnf.getClauses().add(newClause);
            }
            if (newClause.isEmptyClause()) {
                return SentenceInCNF.constructMinimalFalseSentence();
            }
        }
        return scnf;
    }
    
    public Clause assignOneVariableAndReduce(Clause c, Variable variable,boolean value) {
        List<Literal>newLiterals=new ArrayList<Literal>();
        boolean certificateFound=false;
        for(Literal l:c.getLiterals()) {
            Variable vLiteral=l.getVariables().stream().findAny().get();
            if(vLiteral.equals(variable)) {
                if(value && !l.isNegated()|| !value && l.isNegated()) {
                    certificateFound=true;
                    break;
                }
            }else {
                newLiterals.add(l);
            }
        }
        if(certificateFound) {
            return Clause.constructTrueClause();
        }else {
            return new Clause(newLiterals);
        }
    }
    

    @Override
    public boolean verify(SentenceInCNF sentence, VariableAssignment variableAssignment) {
        return sentence.verify(variableAssignment);

    }

    @Override
    public SentenceInCNF getProblem(String problemString) throws UserExecutionException {
        final SATParser parser = new SATParser();
        final SentenceTree s = parser.parse(problemString);
        return SentenceUtil.toCNF(s);
    }

}
