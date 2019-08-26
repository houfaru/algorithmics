package com.algorithmics.np.SAT.instance;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.algorithmics.np.SAT.util.Symbol;
import com.algorithmics.np.core.Certificate;

public class VariableAssignment extends HashMap<Variable, Boolean> implements Certificate {

    private static final long serialVersionUID = 1L;

    public static VariableAssignment constructEmptyAssignment() {
        VariableAssignment va = new VariableAssignment();
        return va;
    }

    public void assign(Variable v, boolean b) {
        put(v, b);
    }

    public Boolean getAssignment(Variable v) {
        if (!containsKey(v)) {
            throw new RuntimeException("Internal Error: Variable does not exist");
        }
        return get(v);
    }

    @Override
    public String toString() {
        String s = "";
        for (Variable v : keySet()) {
            s += v.toString() + " " + Symbol.LEFT_ARROW + " " + (get(v) ? "T" : "F") + "\n";
        }
        return s;
    }

    public void assignAll(VariableAssignment otherVariableAssignment) {
        putAll(otherVariableAssignment);
    }

    public List<Variable> getTrueAssignedVars() {
        return keySet().stream().filter(this::getAssignment).collect(Collectors.toList());
    }

}
