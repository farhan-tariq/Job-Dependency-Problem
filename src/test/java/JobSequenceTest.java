import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JobSequenceTest {
    private ArrayList<Job> unorderedJobsList = new ArrayList<>();
    private ArrayList<Job> expectedListOrder = new ArrayList<>();
    private ArrayList<Job> returnedListOrder = new ArrayList<>();


    @Test
    void testSequence1() {
        clearListItems();
        initTestSequence1List();
        testSequence();
    }

    private void initTestSequence1List() {
        Job a = new Job("a");
        unorderedJobsList.add(a);
        expectedListOrder.add(a);
    }

    @Test
    void testSequence2() {
        clearListItems();
        initTestSequence2List();
        testSequence();
    }


    private void initTestSequence2List() {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");

        unorderedJobsList.add(a);
        unorderedJobsList.add(b);
        unorderedJobsList.add(c);

        Collections.addAll(unorderedJobsList, a, b, c);
        Collections.addAll(expectedListOrder, a, b, c);
    }

    @Test
    void testSequence3() {
        clearListItems();
        initTestSequence3List();
        testSequence();
    }

    private void initTestSequence3List() {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");
        b.setDependency(c);

        unorderedJobsList.add(a);
        unorderedJobsList.add(b);
        unorderedJobsList.add(c);
        Collections.addAll(unorderedJobsList, a, b, c);
        Collections.addAll(expectedListOrder, a, c, b);
    }

    @Test
    void testSequence4() {
        clearListItems();
        initTestSequence4List(unorderedJobsList, expectedListOrder);
        testSequence();
    }

    private void initTestSequence4List(ArrayList<Job> unorderedJobsList, ArrayList<Job> expectedListOrder) {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");
        Job d = new Job("d");
        Job e = new Job("e");
        Job f = new Job("f");
        b.setDependency(c);
        c.setDependency(f);
        d.setDependency(a);
        e.setDependency(b);
        Collections.addAll(unorderedJobsList, a, b, c, d, e, f);
        Collections.addAll(expectedListOrder, a, f, c, b, d, e);
    }

    @Test
    void testIsReflexiveSequence5() {
        clearListItems();
        initReflexiveMethodList(unorderedJobsList);
        JobSequence sequence = new JobSequence(unorderedJobsList);
        assertTrue(sequence.isReflexive(unorderedJobsList));
    }

    private void initReflexiveMethodList(ArrayList<Job> list) {
        list.add(new Job("a"));
        list.add(new Job("b"));
        list.add(new Job("c"));
        Job job = list.get(2);
        job.setDependency(job);
    }

    @Test
    void testHasCyclicDependencySequence6() {
        clearListItems();
        initCyclicDependencyList(unorderedJobsList);

        JobSequence sequence = new JobSequence(unorderedJobsList);
        assertTrue(sequence.hasCyclicDependency(unorderedJobsList));
    }

    private void initCyclicDependencyList(ArrayList<Job> list) {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");
        Job d = new Job("d");
        Job e = new Job("e");
        Job f = new Job("f");
        b.setDependency(c);
        c.setDependency(f);
        d.setDependency(a);
        f.setDependency(b);
        Collections.addAll(list, a, b, c, d, e, f);
    }

    private void testSequence() {
        JobSequence sequence = new JobSequence(unorderedJobsList);
        sequence.sortByDependency();
        returnedListOrder = sequence.getOrderedJobsList();
        assertArrayEquals(expectedListOrder.toArray(), returnedListOrder.toArray());
    }

    private void clearListItems() {
        unorderedJobsList.clear();
        expectedListOrder.clear();
        returnedListOrder.clear();
    }

}