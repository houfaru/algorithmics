package com.algorithmics.np.SAT.instance;

import java.util.HashSet;

import com.algorithmics.np.core.NPHardProblem;

public abstract class Sentence implements NPHardProblem<VariableAssignment> {
	
	private boolean isNegated=false;
	
	public void setNegated(boolean b) {
		isNegated=b;
	}
	
	public void flipNegation() {
		isNegated=!isNegated;
	}
	
	public boolean isNegated() {
		return isNegated;
	}

	public abstract HashSet<Variable>getVariables() throws Exception;
}
