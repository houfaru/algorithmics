package com.algorithmics.pschedulling.instance;

public class Task {
    private final int weight;
    private final int processingTime;

    public Task(int weight, int processingTime) {
        super();
        this.weight = weight;
        this.processingTime = processingTime;
    }

    public int getWeight() {
        return weight;
    }

    public int getProcessingTime() {
        return processingTime;
    }

}
