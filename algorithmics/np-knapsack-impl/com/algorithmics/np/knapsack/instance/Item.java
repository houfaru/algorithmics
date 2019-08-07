package com.algorithmics.np.knapsack.instance;

public class Item {
	private String name;
	private int value;
	private int weight;
	public Item(String name, int value, int weight) {
		super();
		this.name = name;
		this.value = value;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "name:"+name+"  value:"+value+"  weight:"+weight;
	}
}
