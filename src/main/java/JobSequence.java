import java.util.ArrayList;

class JobSequence {
    private ArrayList<Job> jobsList;
    private ArrayList<Job> orderedJobsList;


    JobSequence(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

     void sortByDependency() {
        this.orderedJobsList = new ArrayList<>();
        for (Job job : jobsList) {
            if (!orderedJobsList.contains(job)) orderedJobsList.add(job);
            if (job.hasDependency()) {
                if (!orderedJobsList.contains(job.getDependency())) {
                    int index = orderedJobsList.indexOf(job);
                    orderedJobsList.set(index, job.getDependency());
                    orderedJobsList.add(index + 1, job);
                }
            }
        }
    }

     boolean isReflexive(ArrayList<Job> list) {
        for (Job job : list) {
            if (job.hasDependency()) {
                if (job.getName().equals(job.getDependency().getName())) {
                    return true;
                }
            }
        }
        return false;
    }


     boolean hasCyclicDependency(ArrayList<Job> list) {
        for (Job job : list) {
            if (job.hasDependency()) {
                ArrayList<String> dependencies = getDependencies(job, list);
                if (dependencies.stream().anyMatch(x -> x.equals(job.getName()))) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> getDependencies(Job job, ArrayList<Job> list) {
        ArrayList<String> dependencies = new ArrayList<>();
        int index = 0;
        while (job != null && index < list.size()) {
            if (job.hasDependency()) {
                if (!dependencies.contains(job.getDependency().getName())) {
                    dependencies.add(job.getDependency().getName());
                }
                job = job.getDependency();
            }
            index++;
        }
        return dependencies;
    }

    void start() {
        if (hasCyclicDependency(jobsList) || isReflexive(jobsList)) {
            System.out.println("Is Circular or Reflexive");
        } else {
            sortByDependency();
        }
    }


     ArrayList<Job> getOrderedJobsList() {
        return orderedJobsList;
    }

    void printOrderedList() {
        if (orderedJobsList != null) {
            for (Job job : orderedJobsList) System.out.print(job.getName() + " ");
            System.out.println();
        }
    }
}
