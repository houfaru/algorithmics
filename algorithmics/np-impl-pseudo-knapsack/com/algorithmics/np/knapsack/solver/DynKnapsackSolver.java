package com.algorithmics.np.knapsack.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.core.Solver;
import com.algorithmics.np.knapsack.instance.KnapsackInstance;
import com.algorithmics.servicesupport.ExecutionException;
import com.algorithmics.np.knapsack.instance.Bag;
import com.algorithmics.np.knapsack.instance.Item;

@SolverMapping(name = "KNAPSACK_SOLVER_DYNAMIC_PROGRAMMING", fileExtension = "")
public class DynKnapsackSolver implements Solver<KnapsackInstance, Bag> {

    @Override
    public Optional<Bag> solve(KnapsackInstance p) {
        final int bagCapacity = p.getBagCapacity();
        final List<Item> items = p.getItems();
        final Bag[][] bags = new Bag[items.size() + 1][bagCapacity + 1];

        for (int curWeight = 0; curWeight <= bagCapacity; curWeight++) {
            bags[0][curWeight] = new Bag(curWeight, items);
        }

        for (int iCount = 0; iCount <= items.size(); iCount++) {
            bags[iCount][0] = new Bag(0, items);
        }

        for (int itemCount = 1; itemCount <= items.size(); itemCount++) {
            for (int curWeight = 1; curWeight <= bagCapacity; curWeight++) {

                final Item currentItem = items.get(itemCount - 1);
                final Bag oldBagWithoutItem = bags[itemCount - 1][curWeight];

                if (curWeight >= currentItem.getWeight()) {
                    final Bag prevBagWithCapaForNewItem =
                            bags[itemCount - 1][curWeight - currentItem.getWeight()];
                    final Bag newBagWithNewItem =
                            Bag.newBagWithItemFrom(prevBagWithCapaForNewItem, curWeight);
                    newBagWithNewItem.put(currentItem);
                    if (newBagWithNewItem.getValue() > oldBagWithoutItem.getValue()) {
                        bags[itemCount][curWeight] = newBagWithNewItem;
                    } else {
                        bags[itemCount][curWeight] = oldBagWithoutItem;
                    }
                } else {
                    bags[itemCount][curWeight] = oldBagWithoutItem;
                }

            }
        }

        return Optional.of(bags[items.size()][bagCapacity]);
    }

    @Override
    public boolean verify(KnapsackInstance p, Bag sc) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Optional<Bag> solveForDefaultFormat(String string) throws ExecutionException {
        return solve(getProblem(string));
    }

    @Override
    public KnapsackInstance getProblem(String string) throws ExecutionException {
        String[] tokens = string.split("[\\s]+");
        int bagCapacity = Integer.valueOf(tokens[0]);
        List<Item> items = new ArrayList<>();
        for (int i = 1; i < tokens.length - 1; i += 2) {
            Item item = new Item("item_" + String.valueOf((i + 1) / 2), Integer.parseInt(tokens[i]),
                    Integer.parseInt(tokens[i + 1]));
            items.add(item);
        }
        return new KnapsackInstance(items, bagCapacity);

    }

}
