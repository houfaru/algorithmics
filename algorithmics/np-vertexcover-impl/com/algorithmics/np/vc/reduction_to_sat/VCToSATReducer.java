package com.algorithmics.np.vc.reduction_to_sat;

import java.util.ArrayList;
import java.util.List;

import com.algorithmics.ds.graphs.UndirectedGraph;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.core.Reducer;
import com.algorithmics.np.vc.instance.VertexCover;
import com.algorithmics.np.vc.instance.VertexCoverInstance;

public class VCToSATReducer implements Reducer<VertexCoverInstance, SentenceInCNF>{

	/**
	 * {@link Programming in Propositional Logic or Reductions: Back to the Roots (Satisability) Hermann Stamm-Wilbrandt}
	 */
	@Override
	public SentenceInCNF reduce(VertexCoverInstance vcInstance) {
		
		int k=vcInstance.getK();
		UndirectedGraph plainGraph = vcInstance.getPlainGraph();
		List<Integer> vertices = plainGraph.getVertices();
		
		//This is to constraint that at most K vertices are chosen
		SentenceInCNF resultCNF=SentenceInCNF.constructMinimalTrueSentence();
		for(int curK=1;curK<=k;curK++) {
			List<Variable>variables=new ArrayList<>();
			for(int vertex:vertices) {
				Variable var=new Variable(varFormatter(vertex, curK));
				variables.add(var);
			}
			AtMostOneCNF atMostOneVarCanBeTrue=new AtMostOneCNF(variables);
			resultCNF.getClauses().addAll(atMostOneVarCanBeTrue.getClauses());
		}
		
		//This is to constraint that the chosen vertices are indeed vertex cover
		for(int v1:vertices) {
			for(int v2:plainGraph.getAdjacencyList().get(v1)) {
				//No reflective edges and directed edges
				if(v1>=v2) {
					continue;
				}
				List<Variable> variables=new ArrayList<Variable>();
				for(int curK=1;curK<=k;curK++) {
					variables.add(new Variable(varFormatter(v1, curK)));
					variables.add(new Variable(varFormatter(v2, curK)));
				}
				
				AtLeastOneCNF atLeastOneCanBeTrue=new AtLeastOneCNF(variables);
				resultCNF.getClauses().addAll(atLeastOneCanBeTrue.getClauses());
			}
		}
		
		return resultCNF;
		
	}
	
	public VertexCover certificateReducer(VariableAssignment v) {
		List<Variable> trueAssignedVars = v.getTrueAssignedVars();
		VertexCover vc=new VertexCover();
		for(Variable var: trueAssignedVars) {
            int vertex = getVertexFromVarRepresentation(var.toString());
            vc.add(vertex);
        }
		
		return vc;
		
	}
	
	private String varFormatter(int vertex,int k) {
		return "v"+vertex+"_k"+k;
	}
	
	private int getVertexFromVarRepresentation(String varRep) {
		String[] split = varRep.split("_");
		String vertex = split[0].replace("v", "");
		return Integer.valueOf(vertex);
	};
	

}
