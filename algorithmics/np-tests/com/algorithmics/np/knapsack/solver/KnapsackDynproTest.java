package com.algorithmics.np.knapsack.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.algorithmics.np.knapsack.instance.Bag;
import com.algorithmics.np.knapsack.instance.Item;
import com.algorithmics.np.knapsack.instance.KnapsackInstance;

public class KnapsackDynproTest {
	@Test
	public void test() {
		int bagCapacity = 5;
		List<Item> items = Arrays.asList(new Item("1", 100, 3), new Item("2", 20, 2), new Item("3", 60, 4),
				new Item("4", 40, 1));
		KnapsackInstance instance = new KnapsackInstance(items, bagCapacity);
		DynKnapsackSolver solver = new DynKnapsackSolver();
		Optional<Bag> bag = solver.solve(instance);
		assertTrue(bag.isPresent());
		List<Item> contents = bag.get().getContents();
		assertTrue(contents.contains(items.get(0)));
		assertTrue(contents.contains(items.get(3)));
		assertEquals(contents.size(), 2);
	}
}
