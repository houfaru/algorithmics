package com.algorithmics.np.SAT.instance;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.algorithmics.np.core.Certificate;
import com.algorithmics.util.Symbol;

public class VariableAssignment implements Certificate {
    
    private HashMap<Variable, Boolean> varValueMap = new HashMap<Variable, Boolean>();

    public static VariableAssignment constructEmptyAssignment() {
        VariableAssignment va = new VariableAssignment();
        return va;
    }

    public void assign(Variable v, boolean b) {
        varValueMap.put(v, b);
    }

    public Boolean getAssignment(Variable v) {
        if (!varValueMap.containsKey(v)) {
            throw new RuntimeException("Internal Error: Variable does not exist");
        }
        return varValueMap.get(v);
    }

    @Override
    public String toString() {
        String s = "";
        for (Variable v : varValueMap.keySet()) {
            s += v.toString() + " " + Symbol.LEFT_ARROW + " " + (varValueMap.get(v) ? "T" : "F")
                    + "\n";
        }
        return s;
    }

    public void assignAll(VariableAssignment otherVariableAssignment) {
        varValueMap.putAll(otherVariableAssignment.varValueMap);
    }

    public List<Variable> getTrueAssignedVars() {
        return varValueMap.keySet().stream().filter(v -> varValueMap.get(v))
                .collect(Collectors.toList());
    }

}
