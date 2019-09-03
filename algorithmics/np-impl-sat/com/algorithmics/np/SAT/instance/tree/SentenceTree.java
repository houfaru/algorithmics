package com.algorithmics.np.SAT.instance.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import com.algorithmics.np.SAT.instance.Sentence;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.util.Symbol;

public class SentenceTree extends Sentence {

    private Optional<BooleanOperationEnum> operation;
    private Optional<List<SentenceTree>> subTrees;
    private Optional<Variable> var;

    protected SentenceTree() {};

    public static SentenceTree constractLeafTree(Variable var) {
        final SentenceTree leafTree = new SentenceTree();
        leafTree.var = Optional.of(var);
        leafTree.operation = Optional.empty();
        leafTree.subTrees = Optional.empty();
        return leafTree;
    }

    
    public Optional<BooleanOperationEnum>getOperation(){
        return operation;
    }
    
    public static SentenceTree constructNonLeafTree(BooleanOperationEnum op, SentenceTree firstTree,
            SentenceTree secondTree, SentenceTree... otherTrees) {
        final SentenceTree nonLeafTree = new SentenceTree();
        nonLeafTree.var = Optional.empty();
        nonLeafTree.subTrees = Optional.of(new ArrayList<>());
        nonLeafTree.subTrees.get().add(firstTree);
        nonLeafTree.subTrees.get().add(secondTree);
        nonLeafTree.subTrees.get().addAll(Arrays.asList(otherTrees));
        nonLeafTree.operation = Optional.of(op);
        return nonLeafTree;
    }

    @Override
    public HashSet<Variable> getVariables() {
        final HashSet<Variable> variableSet = new HashSet<Variable>();
        if (isLeaf()) {
            if (!var.isPresent()) {
                throw new RuntimeException(
                        "Internal Error, a sentence tree leaf do not contain variable");
            }
            variableSet.add(var.get());
        } else {
            if (!subTrees.isPresent()) {
                throw new RuntimeException("Internal Error, subtrees empty");
            }
            subTrees.get().forEach(tree -> {
                try {
                    variableSet.addAll(tree.getVariables());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return variableSet;
    }

    public boolean isLeaf() {
        return !subTrees.isPresent();
    }

    public int getDepth() {
        if (isLeaf()) {
            return 0;
        } else {
            int maxDepth = Integer.MIN_VALUE;
            for (SentenceTree subTree : subTrees.get()) {
                maxDepth = Math.max(maxDepth, subTree.getDepth());
            }
            return maxDepth + 1;
        }
    }

    @Override
    public boolean verify(VariableAssignment certificate) {
        if (isLeaf()) {
            return isNegated() && !certificate.getAssignment(var.get())
                    || !isNegated() && certificate.getAssignment(var.get());
        } else {
            if (operation.get() == BooleanOperationEnum.AND) {
                boolean res = true;
                for (SentenceTree subTree : subTrees.get()) {
                    boolean cur = subTree.verify(certificate);
                    if (cur == false) {
                        res = false;
                        break;
                    }
                }
                return res;
            }
            if (operation.get() == BooleanOperationEnum.OR) {
                boolean res = false;
                for (SentenceTree tree : subTrees.get()) {
                    boolean cur = tree.verify(certificate);
                    if (cur == true) {
                        res = true;
                        break;
                    }
                }
                return res;
            }
        }
        throw new RuntimeException(
                "Internal Error: undefined boolean Operation on nonleaf node : " + operation.get());
    }

    @Override
    public String toString() {
        if (isLeaf()) {
            if (isNegated()) {
                return Symbol.NOT + var.get().toString();
            } else {
                return var.get().toString();
            }
        } else {
            if (!operation.isPresent()) {
                throw new RuntimeException(
                        "Internal Error: undefined boolean Operation on nonleaf node");
            }
            if (!subTrees.isPresent()) {
                throw new RuntimeException("Internal Error: subtrees undefined on nonleaf node");
            }
            final String operatorSymbol =
                    operation.get().toString().equals("AND") ? Symbol.AND + "" : Symbol.OR + "";
            final StringJoiner stringJoiner = new StringJoiner(" " + operatorSymbol + " ");
            for (SentenceTree subTree : subTrees.get()) {
                stringJoiner.add(subTree.toString());
            }
            return isNegated() ? "" + Symbol.NOT : "(" + stringJoiner.toString() + ")";
        }
    }

    public Optional<List<SentenceTree>> getSubTrees() {
        if (!subTrees.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(Collections.unmodifiableList(subTrees.get()));
    }

    public void toNAryTree() {
        if (isLeaf()) {
            return;
        }

        List<SentenceTree> newSubTrees = new ArrayList<SentenceTree>();
        for (SentenceTree subTree : subTrees.get()) {
            subTree.toNAryTree();
            if (subTree.operation.equals(this.operation)) {
                newSubTrees.addAll(subTree.getSubTrees().get());
            } else {
                newSubTrees.add(subTree);
            }
        }
        subTrees = Optional.of(newSubTrees);
    }

    

    
    public void propagateNegation() {
        if (isLeaf() || !isNegated()) {
            return;
        }
        flipNegation();
        flipOperation();
        for (SentenceTree s : subTrees.get()) {
            s.flipNegation();
            s.propagateNegation();
        }
    }

    public void flipOperation() {
        if (isLeaf()) {
            return;
        }
        if (this.operation.get().equals(BooleanOperationEnum.AND)) {
            this.operation = Optional.of(BooleanOperationEnum.OR);
        } else {
            this.operation = Optional.of(BooleanOperationEnum.AND);
        }
    }

}
