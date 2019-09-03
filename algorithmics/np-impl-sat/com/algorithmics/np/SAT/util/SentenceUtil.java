package com.algorithmics.np.SAT.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.instance.tree.BooleanOperationEnum;
import com.algorithmics.np.SAT.instance.tree.SentenceTree;

public class SentenceUtil {
    public static SentenceInCNF toCNF(SentenceTree sentenceTree) {
        sentenceTree.propagateNegation();
        if (sentenceTree.isLeaf()) {
            final List<Literal> literals = new ArrayList<Literal>();
            HashSet<Variable> variables = sentenceTree.getVariables();
            Optional<Variable> var = variables.stream().findAny();
            final Literal literal = new Literal(var.get());
            literal.setNegated(sentenceTree.isNegated());
            literals.add(literal);
            final Clause c = new Clause(literals);
            final List<Clause> clauses = new ArrayList<Clause>();
            clauses.add(c);
            final SentenceInCNF s = new SentenceInCNF(clauses);
            return s;
        }
        if (sentenceTree.getOperation().get().equals(BooleanOperationEnum.OR)
                && sentenceTree.getDepth() == 1) {
            final SentenceInCNF s = SentenceInCNF.constructMinimalTrueSentence();
            final List<Literal> literals = new ArrayList<Literal>();

            for (SentenceTree subTree : sentenceTree.getSubTrees().get()) {
                SentenceInCNF subCNF = toCNF(subTree);
                literals.addAll(subCNF.getClauses().get(0).getLiterals());
            }
            final Clause newClause = new Clause(literals);
            s.getClauses().add(newClause);
            return s;
        }
        if (sentenceTree.getOperation().get().equals(BooleanOperationEnum.AND)) {
            final SentenceInCNF newCNF = SentenceInCNF.constructMinimalTrueSentence();
            for (SentenceTree subTree : sentenceTree.getSubTrees().get()) {
                SentenceInCNF subCNF = toCNF(subTree);
                newCNF.getClauses().addAll(subCNF.getClauses());
            }
            return newCNF;
        }
        if (sentenceTree.getOperation().get().equals(BooleanOperationEnum.OR)) {

            SentenceInCNF newCNF = SentenceInCNF.constructMinimalTrueSentence();
            for (SentenceTree subTree : sentenceTree.getSubTrees().get()) {
                SentenceInCNF cnf = toCNF(subTree);
                newCNF = crossProduct(newCNF, cnf);
            }

            return newCNF;
        }
        throw new RuntimeException("Unreachable code");
    }

    private static SentenceInCNF crossProduct(SentenceInCNF firstTrees, SentenceInCNF secondTrees) {
        final SentenceInCNF newCNF = SentenceInCNF.constructMinimalTrueSentence();
        if (firstTrees.isTautology().isPresent() && firstTrees.isTautology().get()) {
            return secondTrees;
        }
        for (Clause c1 : firstTrees.getClauses()) {
            for (Clause c2 : secondTrees.getClauses()) {
                List<Literal> literals = new ArrayList<Literal>();
                literals.addAll(c1.getLiterals());
                literals.addAll(c2.getLiterals());
                Clause newClause = new Clause(literals);
                newCNF.getClauses().add(newClause);
            }
        }
        return newCNF;
    }


}
