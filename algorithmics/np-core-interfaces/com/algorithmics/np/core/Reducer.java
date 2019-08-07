package com.algorithmics.np.core;

public interface Reducer<F extends NPProblem ,T extends NPHardProblem<?> > {
	T reduce(F p);
}
