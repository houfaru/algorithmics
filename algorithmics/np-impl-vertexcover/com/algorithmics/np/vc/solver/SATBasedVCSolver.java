package com.algorithmics.np.vc.solver;

import com.algorithmics.np.core.SatBasedSolver;
import com.algorithmics.np.vc.instance.VertexCover;
import com.algorithmics.np.vc.instance.VertexCoverInstance;
import com.algorithmics.np.vc.reduction_to_sat.VCToSATReducer;

public class SATBasedVCSolver
        extends SatBasedSolver<VertexCover, VertexCoverInstance> {

    private static VCToSATReducer reducer = new VCToSATReducer();

    public SATBasedVCSolver() {
        super(reducer);
    }


}
