package com.algorithmics.np.knapsack.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.algorithmics.np.core.Certificate;

public class Bag implements Certificate {
	private int bagCapacity;
	private int curWeightSum;
	private Map<Item, Boolean> isItemPicked = new HashMap<>();
	private List<Item> items;
	public Bag(int bagCapacity, List<Item> items) {
		this.bagCapacity = bagCapacity;
		this.curWeightSum = 0;
		this.items=items;
		for (Item item : items) {
			isItemPicked.put(item, false);
		}
	}
	public static Bag newBagWithItemFrom(Bag other,int newCapacity) {
		Bag newBag=new Bag(newCapacity, other.items);
		newBag.isItemPicked=new HashMap<Item, Boolean>(other.isItemPicked);
		return newBag;
	}
	
	public boolean canPut(Item item) {
		if (isItemPicked.get(item)) {
			return false;
		}
		if (item.getWeight() + curWeightSum > bagCapacity) {
			return false;
		}
		return true;
	}
	public void put(Item item) {
		if (!isItemPicked.containsKey(item)) {
			throw new IllegalArgumentException("Illegal Item : " + item.toString());
		}
		if (isItemPicked.get(item)) {
			throw new IllegalArgumentException("item already added : " + item.toString());
		}
		if (item.getWeight() + curWeightSum > bagCapacity) {
			throw new IllegalArgumentException("bag capacity violated : " + item.toString());
		}
		isItemPicked.put(item, true);
		curWeightSum += item.getValue();
	}

	public void remove(Item item) {
		if (!isItemPicked.containsKey(item)) {
			throw new IllegalArgumentException("Illegal Item : " + item.toString());
		}
		if (!isItemPicked.get(item)) {
			throw new IllegalArgumentException("Item does not exist : " + item.toString());
		}
		isItemPicked.put(item, false);
		curWeightSum -= item.getValue();
	}

	public List<Item>getContents(){
		return isItemPicked.keySet().stream().filter(isItemPicked::get).collect(Collectors.toList());
	}
	
	public int getCurWeightSum() {
		return curWeightSum;
	}

	public int getBagCapacity() {
		return bagCapacity;
	}

	public int getValue() {
		return isItemPicked.keySet().stream().filter(isItemPicked::get).map(Item::getValue).reduce(0,
				(i1, i2) -> i1 + i2);
	}

	@Override
	public String toString() {
		String result="bag\n";
		for (Item item : items) {
			result+=" "+isItemPicked.get(item)+" "+item.toString()+"\n";
		}
		return result;
	}
}
