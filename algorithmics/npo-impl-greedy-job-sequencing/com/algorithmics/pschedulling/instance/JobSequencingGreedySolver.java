package com.algorithmics.pschedulling.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.InvalidInputFormatException;
import com.algorithmics.servicesupport.UserExecutionException;

/**
 * 
 * Precedence constrained Tasks Schedulling.
 *
 */
@SolverMapping(name = "P_SCHEDULLING_GREEDY_SOLVER", fileExtensions = "")
public class JobSequencingGreedySolver implements Solver<JobSequencingInstance, Tasks> {

    @Override
    public Optional<Tasks> solve(JobSequencingInstance p) throws UserExecutionException {
        List<Task> tasks = new ArrayList<>(p.getTasks());
        Collections.sort(tasks, (t1, t2) -> Double.compare(t2.getProfit(), t1.getProfit()));
        Set<Task> assignedTasks = new HashSet<>();
        Set<Integer> timeSlots = new HashSet<>();
        for (Task task : tasks) {
            int currentDeadline = task.getDeadline();
            if (timeSlots.contains(currentDeadline)) {
                continue;
            } else {
                assignedTasks.add(task);
                timeSlots.add(currentDeadline);
            }
        }
        Tasks toBeExecutedTasks = new Tasks(assignedTasks);
        if (p.verify(toBeExecutedTasks)) {
            return Optional.of(toBeExecutedTasks);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean verify(JobSequencingInstance p, Tasks sc) throws UserExecutionException {
        return p.verify(sc);
    }

    @Override
    public JobSequencingInstance getProblem(String string) throws UserExecutionException {
        String[] tokens = string.split("[\\s]+");
        try {
            int deadLine = Integer.parseInt(tokens[0]);

            Set<Task> tasks = new HashSet<>();
            for (int i = 1; i < tokens.length - 1; i += 2) {
                Task t = new Task(Integer.parseInt(tokens[i]), Double.parseDouble(tokens[i + 1]));
                tasks.add(t);
            }
            return new JobSequencingInstance(tasks, deadLine);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException(e);
        }
    }



}
