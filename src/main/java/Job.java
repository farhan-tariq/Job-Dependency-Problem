class Job {
    private String name;
    private Job dependency;

    Job(String name) {
        this.name = name;
        this.dependency = null;
    }

    String getName() {
        return name;
    }

    Job getDependency() {
        if (dependency != null)
            return dependency;
        else return null;
    }

    void setDependency(Job dependency) {
        this.dependency = dependency;
    }

    boolean hasDependency() {
        return dependency != null;
    }
}
