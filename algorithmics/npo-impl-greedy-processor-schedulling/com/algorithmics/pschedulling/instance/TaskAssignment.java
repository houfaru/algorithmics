package com.algorithmics.pschedulling.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algorithmics.np.core.Certificate;

public class TaskAssignment implements Certificate {
    private Map<Integer, List<Integer>> machineToTasksMap;

    public TaskAssignment() {
        machineToTasksMap = new HashMap<>();
    }

    public void assignTaskToProcessor(int taskId, int processorId) {
        machineToTasksMap.computeIfAbsent(processorId, p -> new ArrayList<>()).add(taskId);
    }

}
