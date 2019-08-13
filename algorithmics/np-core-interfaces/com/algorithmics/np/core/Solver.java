package com.algorithmics.np.core;

import java.util.Optional;

public interface Solver<P extends NPProblem,S extends Certificate> {
	public Optional<S> solve(P  p) ;
	public boolean verify(P p,S sc) ;
}
