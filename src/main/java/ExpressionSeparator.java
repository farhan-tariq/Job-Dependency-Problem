import java.util.*;

class ExpressionSeparator {
    private String expression;
    private ArrayList<Job> jobList = new ArrayList<>();
    private Map<Integer, String> jobMap = new HashMap<>();
    private Map<Integer, String> dependencyMap = new HashMap<>();


    ExpressionSeparator(String expression) {
        this.expression = expression.trim();
        initJobAndDependencyList();
        initList();
    }

    private void initJobAndDependencyList() {
        if (this.expression != null) {
            String separator = "=>";
            String delimiter = "\n";
            int length = expression.split(separator + "|" + delimiter).length;
            for (int i = 0; i < length; i++) {
                String a = expression.split(separator + "|" + delimiter)[i].trim();
                if (i % 2 == 0 && !a.equals("")) jobMap.put(i, a);
                if (i % 2 != 0 && !a.equals("")) dependencyMap.put(i, a);
            }
        }
    }

    private void initList() {
        for (int i = 0; i <= Collections.max(jobMap.keySet()); i++) {
            String jobName = jobMap.get(i);
            if (jobMap.containsKey(i) && dependencyMap.containsKey(i + 1)) {
                String dependencyName = dependencyMap.get(i + 1);

                if (checkIfAnyMatch(dependencyName) && checkIfAnyMatch(jobName)) {
                    Job j1 = getMatchingJob(jobName);
                    Job j2 = getMatchingJob(dependencyName);
                    j1.setDependency(j2);
                } else if (checkIfAnyMatch(dependencyName) && !checkIfAnyMatch(jobName)) {
                    Job j1 = new Job(jobName);
                    Job j2 = getMatchingJob(dependencyName);
                    j1.setDependency(j2);
                    jobList.add(j1);
                } else {

                    if (checkIfAnyMatch(jobName)) {
                        Job j1 = getMatchingJob(jobName);
                        Job j2 = new Job(dependencyName);
                        j1.setDependency(j2);
                        jobList.add(j2);
                    } else {
                        Job j1 = new Job(jobName);
                        Job j2 = new Job(dependencyName);
                        j1.setDependency(j2);
                        jobList.add(j1);
                        jobList.add(j2);
                    }
                }
            } else if (jobMap.containsKey(i) && !dependencyMap.containsKey(i + 1)) {
                if (!checkIfAnyMatch(jobName)) {
                    Job j1 = new Job(jobName);
                    jobList.add(j1);
                }
            }
        }
    }

    ArrayList<Job> getJobsList() {
        return jobList;
    }

    private boolean checkIfAnyMatch(String name) {
        return jobList.stream().anyMatch(t -> t.getName().equals(name));
    }

    private Job getMatchingJob(String name) {
        Optional<Job> matchingObject = jobList.stream().
                filter(p -> p.getName().equals(name)).
                findFirst();
        return matchingObject.orElse(null);
    }

}
