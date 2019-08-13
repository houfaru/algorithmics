package com.algorithmics.np.vc.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PlainGraph {
	private HashMap<Integer,HashSet<Integer>> adjacencyList=new HashMap<Integer, HashSet<Integer>>();
	
	public boolean containsVertex(int i) {
		return adjacencyList.keySet().contains(i);
	}
	
	public List<Integer>getNeighborsOf(int vertex){
		return new ArrayList<Integer>(adjacencyList.get(vertex));
	}
	public int getSize() {
		return adjacencyList.keySet().size();
	}
	public void addVertex(int v) {
		adjacencyList.putIfAbsent(v, new HashSet<Integer>());
	}
	public void addEdge(int v,int w) {
		adjacencyList.putIfAbsent(v, new HashSet<Integer>());
		adjacencyList.putIfAbsent(w, new HashSet<Integer>());
		adjacencyList.get(v).add(w);
		adjacencyList.get(w).add(v);
	}
	public HashMap<Integer, HashSet<Integer>> getAdjacencyList() {
		return adjacencyList;
	}
	public List<Integer>getVertices(){
		List<Integer> list=new ArrayList<Integer>(adjacencyList.keySet());
		return list;
	}
	@Override
	public String toString() {
		String s="v="+adjacencyList.keySet().toString();
		s+="e="+adjacencyList.toString();
		return s;
	}
	
}
