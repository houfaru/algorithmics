package com.algorithmics.pschedulling.instance;

import java.util.Set;

import com.algorithmics.npo.core.NPOProblem;
import com.algorithmics.servicesupport.UserExecutionException;

public class JobSequencingInstance implements NPOProblem<Tasks> {
    private final int deadline;
    private Set<Task> tasks;

    public JobSequencingInstance(Set<Task> tasks, int deadline) {
        super();
        this.tasks = tasks;
        this.deadline = deadline;
    }

    public double getDeadline() {
        return deadline;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean verify(Tasks certificate) throws UserExecutionException {
        long numOfDeadlines =
                certificate.getTasks().stream().mapToInt(t -> t.getDeadline()).count();
        long numOfDistinctDeadlines =
                certificate.getTasks().stream().mapToInt(t -> t.getDeadline()).distinct().count();
        int max = certificate.getTasks().stream().mapToInt(t -> t.getDeadline()).max()
                .orElseGet(() -> 0);
        return numOfDeadlines == numOfDistinctDeadlines && max <= deadline;
    }

    @Override
    public double opt(Tasks certificate) throws UserExecutionException {
        if (verify(certificate)) {
            return certificate.getTasks().stream().mapToDouble(t -> t.getProfit()).sum();
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {
        String s = "deadline : " + deadline + "\n";
        s += "tasks : \n";
        s += tasks.stream().map(t -> t.toString()).reduce((t1, t2) -> t1 + "\n" + t2).orElse("()");
        return s;
    }

}
