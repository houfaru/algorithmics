package com.algorithmics.np.core;

public interface NPHardProblem<C extends Certificate> extends NPProblem{
	public boolean verify(C certificate) ;
}
