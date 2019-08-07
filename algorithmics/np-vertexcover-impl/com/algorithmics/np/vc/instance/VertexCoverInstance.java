package com.algorithmics.np.vc.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.algorithmics.np.core.NPHardProblem;

public class VertexCoverInstance implements NPHardProblem<VertexCover>{
	PlainGraph plainGraph;
	int k;
	public VertexCoverInstance(PlainGraph g,int k) {
		this.k=k;
		this.plainGraph=g;
	}
	
	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public PlainGraph getPlainGraph() {
		return plainGraph;
	}

	@Override
	public boolean verify(VertexCover certificate) {
		if(certificate.getVertices().size()>k) {
			return false;
		}
		HashSet<Integer>coveredNode=new HashSet<>();
		for(Integer vertex:certificate.getVertices()) {
			if(plainGraph.containsVertex(vertex)) {
				coveredNode.add(vertex);
				coveredNode.addAll(plainGraph.getNeighborsOf(vertex));
			}
			if(plainGraph.getSize()==coveredNode.size()) {
				return true;
			}
		}
		return false;
	}
}
