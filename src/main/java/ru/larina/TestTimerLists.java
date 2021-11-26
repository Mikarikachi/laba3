package ru.larina;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestTimerLists {
    private static final Object OBJECT = new Object();
    private static final int COUNTER = 10_000;
    private static final String LINE = "----------------------------------------------";

    public static void main(String[] args) {
        List<Object> listLinked = new LinkedList<>();
        List<Object> listArray = new ArrayList<>();

        List<Long> linkedInfo = new ArrayList<>();
        List<Long> arrayInfo = new ArrayList<>();


        linkedInfo.add(testAddWithoutIndexMethod(listLinked));
        arrayInfo.add(testAddWithoutIndexMethod(listArray));

        linkedInfo.add(testAddWithIndexMethod(listLinked));
        arrayInfo.add(testAddWithIndexMethod(listArray));

        linkedInfo.add(testGetMethod(listLinked));
        arrayInfo.add(testGetMethod(listArray));

        linkedInfo.add(testRemoveMethod(listLinked));
        arrayInfo.add(testRemoveMethod(listArray));

        linkedInfo.add(testAddZeroIndexMethod(listLinked));
        arrayInfo.add(testAddZeroIndexMethod(listArray));

        linkedInfo.add(testRemoveZeroIndexMethod(listLinked));
        arrayInfo.add(testRemoveZeroIndexMethod(listArray));

        outputAsTableInConsole(linkedInfo, arrayInfo);
    }

    /**
     * Check the execution time of the add without index method.
     *
     * @param list  received list
     * @return the method runtime
     */
    private static long testAddWithoutIndexMethod(List<Object> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < COUNTER; ++i) {
            list.add(OBJECT);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Check the execution time of the add with index method.
     *
     * @param list  received list
     * @return the method runtime
     */
    private static long testAddWithIndexMethod(List<Object> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < COUNTER; ++i) {
            list.add(list.size() / 2, OBJECT);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Check the execution time of the add with zero index method.
     *
     * @param list  received list
     * @return the method runtime
     */
    private static long testAddZeroIndexMethod(List<Object> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < COUNTER; ++i) {
            list.add(0, OBJECT);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Check the execution time of the get method.
     *
     * @param list  received list
     * @return the method runtime
     */
    private static long testGetMethod(List<Object> list) {
        Random rnd = new Random(5);
        long startTime = System.nanoTime();
        for (int i = 0; i < COUNTER; ++i) {
            int t = rnd.nextInt(list.size());
            list.get(t);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Check the execution time of the remove method.
     *
     * @param list  received list
     * @return the method runtime
     */
    private static long testRemoveMethod(List<Object> list) {
        Random rnd = new Random(6);
        long startTime = System.nanoTime();
        for (int i = 0; i < COUNTER; ++i) {
            int t = rnd.nextInt(list.size());
            list.remove(t);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Check the execution time of the remove with zero index method.
     *
     * @param list  received list
     * @return the method runtime
     */
    private static long testRemoveZeroIndexMethod(List<Object> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < COUNTER; ++i) {
            list.remove(0);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Output the data about the methods as a table format.
     *
     * @param linkedInfo list of information about the execution of the list methods
     * @param arrayInfo list of information about the execution of the array methods
     */
    private static void outputAsTableInConsole(List<Long> linkedInfo, List<Long> arrayInfo) {
        System.out.println(LINE);
        System.out.println(String.format(
                "| %1$13s | %2$8s | %3$15s |",
                "Method name", "Count", "Time (ns)"));
        System.out.println(LINE);

        methodInfoOutput("Add", linkedInfo.get(0), arrayInfo.get(0));
        methodInfoOutput("AddIndex", linkedInfo.get(1), arrayInfo.get(1));
        methodInfoOutput("Get", linkedInfo.get(2), arrayInfo.get(2));
        methodInfoOutput("Remove", linkedInfo.get(3), arrayInfo.get(3));
        methodInfoOutput("AddZero", linkedInfo.get(4), arrayInfo.get(4));
        methodInfoOutput("RemoveZero", linkedInfo.get(5), arrayInfo.get(5));
    }

    /**
     * Forms a line from the received data.
     *
     * @param Name method name
     * @param time method runtime
     *@return the string of data
     */
    private static String lineCreator(String Name, long time) {
        return String.format(
                "| %1$13s | %2$8d | %3$15d |",
                Name, COUNTER, time);
    }

    /**
     * Outputs the data of each method to a table.
     *
     * @param methodName method name
     * @param linkedInfo list of information about the execution of the list methods
     * @param arrayInfo list of information about the execution of the array methods
     */
    private static void methodInfoOutput(String methodName, long linkedInfo, long arrayInfo) {
        System.out.println(lineCreator(methodName + "(L)", linkedInfo));
        System.out.println(lineCreator(methodName + "(A)", arrayInfo));
        System.out.println(LINE);
    }
}