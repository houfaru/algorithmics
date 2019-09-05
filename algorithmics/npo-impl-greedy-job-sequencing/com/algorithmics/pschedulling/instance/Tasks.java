package com.algorithmics.pschedulling.instance;

import java.util.HashSet;
import java.util.Set;

import com.algorithmics.np.core.Certificate;

public class Tasks implements Certificate {
    private Set<Task> tasks;

    public Tasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Tasks() {
        tasks = new HashSet<>();
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return tasks.stream().map(t->t.toString()).reduce((t1,t2)->t1+"\n"+t2).orElse("()");
    }
    
}
