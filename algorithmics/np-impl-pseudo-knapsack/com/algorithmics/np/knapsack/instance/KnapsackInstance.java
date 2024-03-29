package com.algorithmics.np.knapsack.instance;

import java.util.ArrayList;
import java.util.List;

import com.algorithmics.np.core.NPHardProblem;
import com.algorithmics.servicesupport.UserExecutionException;

public class KnapsackInstance implements NPHardProblem<Bag> {
    private final int bagCapacity;
    private final List<Item> items;

    public KnapsackInstance(List<Item> items, int bagCapacity) {
        this.bagCapacity = bagCapacity;
        this.items = items;
    }

    public Bag getInitialBag() {
        return new Bag(bagCapacity, items);
    }

    public int getBagCapacity() {
        return bagCapacity;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }


    @Override
    public boolean verify(Bag certificate) throws UserExecutionException {
        throw new UserExecutionException(new UnsupportedOperationException("Not yet implemented"));
    }

    @Override
    public String toString() {
        return "Bag capacity: " + bagCapacity + "\nItems :\n"
                + items.stream().map(Item::toString).reduce((a, b) -> a + "\n" + b).orElse("NA");
    }

}
