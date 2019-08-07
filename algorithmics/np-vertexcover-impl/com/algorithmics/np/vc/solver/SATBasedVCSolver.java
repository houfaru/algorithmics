package com.algorithmics.np.vc.solver;

import java.util.Optional;

import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;
import com.algorithmics.np.core.Solver;
import com.algorithmics.np.vc.instance.VertexCover;
import com.algorithmics.np.vc.instance.VertexCoverInstance;
import com.algorithmics.np.vc.reduction_to_sat.VCToSATReducer;

public class SATBasedVCSolver implements Solver<VertexCoverInstance, VertexCover> {

	@Override
	public Optional<VertexCover> solve(VertexCoverInstance p) {
		VCToSATReducer reducer=new VCToSATReducer();
		SentenceInCNF sentence = reducer.reduce(p);
		SATSolverRecursive solver=new SATSolverRecursive();
		Optional<VariableAssignment> solution = solver.solve(sentence);
		if(!solution.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.of(reducer.certificateReducer(solution.get()));
		}
	}	
	
	@Override
	public boolean verify(VertexCoverInstance p, VertexCover sc) {
		return p.verify(sc);
		
	}

}
