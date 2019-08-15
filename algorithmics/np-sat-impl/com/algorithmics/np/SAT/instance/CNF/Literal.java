package com.algorithmics.np.SAT.instance.CNF;

import java.util.HashSet;

import com.algorithmics.np.SAT.instance.Sentence;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;

public class Literal extends Sentence{
	private Variable v;
	public Literal(Variable v) {
		this.v=v;
	}
	@Override
	public HashSet<Variable> getVariables() {
		HashSet<Variable>newVars=new HashSet<Variable>();
		newVars.add(v);
		return newVars;
	}
	@Override
	public boolean verify(VariableAssignment certificate) {
		return certificate.getAssignment(v) && isNegated();
		
	}
	
	@Override
	public String toString() {
		if(isNegated()) {
			return (char)172+v.toString();
		}else {
			return v.toString();
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Literal)) {
			return false;
		}
		Literal other=(Literal)obj;
		Variable otherVar = other.getVariables().stream().findAny().get();
		Variable thisVar = this.getVariables().stream().findAny().get();
		if(thisVar.equals(otherVar) && this.isNegated()==other.isNegated()) {
			return true;
		}else {
			return false;
		}
	}

	public Variable getVariable() {
	    return v;
	}
	
}
