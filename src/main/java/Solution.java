import java.util.ArrayList;

class Solution {
    private ArrayList<String> expressionList = new ArrayList<>();
    private ArrayList<ArrayList<Job>> listOfLists = new ArrayList<>();

    Solution() {
        initExpressionList();
        initListOfLists();
        solve();
    }

    private void initListOfLists() {
        for (String exp : expressionList) {
            ExpressionSeparator separator = new ExpressionSeparator(exp);
            ArrayList<Job> jobArrayList = separator.getJobsList();
            listOfLists.add(jobArrayList);
        }
    }

    private void initExpressionList() {
        expressionList.add("a=>");
        expressionList.add("a=>\nb=>\nc=>");
        expressionList.add("a=>\nb=>c\nc=>");
        expressionList.add("a=>\nb=>c\nc=>f\nd=>a\ne=>b\nf=>");
        expressionList.add("a=>\nb=>\nc=>c");
        expressionList.add("a=>\nb=>c\nc=>f\nd=>a\ne=>\nf=>b");
    }

    private void solve() {
        JobSequence sequence;
        for (ArrayList<Job> listOfJobs : listOfLists) {
            sequence = new JobSequence(listOfJobs);
            sequence.start();
            sequence.printOrderedList();
        }
    }
}
