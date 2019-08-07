package com.algorithmics.np.knapsack.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algorithmics.np.core.NPHardProblem;

public class KnapsackInstance implements NPHardProblem<Bag>{
	private int bagCapacity;
	private List<Item> items;

	public KnapsackInstance(List<Item>items,int bagCapacity) {
		this.bagCapacity = bagCapacity;
		this.items=items;
	}
	public Bag getInitialBag() {
		return new Bag(bagCapacity, items);
	}
	
	public int getBagCapacity() {
		return bagCapacity;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	
	@Override
	public boolean verify(Bag certificate) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}
