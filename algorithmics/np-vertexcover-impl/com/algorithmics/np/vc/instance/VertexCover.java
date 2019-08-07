package com.algorithmics.np.vc.instance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.algorithmics.np.core.Certificate;

public class VertexCover implements Certificate{
	
	private HashSet<Integer>vertices=new HashSet<Integer>();
	
	public void addVertex(int v) {
		vertices.add(v);
	}
	
	public void removeVertex(int v) {
		if(vertices.contains(v)) {
			vertices.remove(v);
		}
	}
	
	public boolean contains(int v) {
		return vertices.contains(v);
	}
	
	public List<Integer>getVertices(){
		return new ArrayList<Integer>(vertices);
	}
	
}
