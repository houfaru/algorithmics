package com.algorithmics.pschedulling.instance;

public class Task {
    private final int deadline;
    private final double profit;

    public Task(int deadline, double profit) {
        super();
        this.deadline = deadline;
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "(p=" + profit + ",d=" + deadline + ")";
    }
}
