package com.sbilyi;

import com.sbilyi.solution.EasyOneO1MemorySolution;
import com.sbilyi.solution.EasyOneSolution;
import com.sbilyi.solution.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static final String TEST_MESSAGE_PATTERN = "Test %s expected %s actual %s with [%s]";
    private static final int DEFAULT_VALUE = Solution.DEFAULT_VALUE;
    public static final IllegalArgumentException EXPECTED_EXCEPTION = new IllegalArgumentException();

    /**
     * QUESTION EASY#1
     * Implement a method easy1 that given an integer array, which each element appears twice
     * except for one. Find that one
     * You must solve it with O(n) time-complexity
     * <p>
     * Example #1
     * Input: [1, 1, 2, 2, 3 ]
     * Output: 3
     * <p>
     * Example #2
     * Input: [-1, 2, 4 , 2, -1]
     * Output: 4
     * <p>
     * (Optional) bonus point: solve with O(1) space-complexity
     *
     * @param args
     */

    public static void main(String[] args) {
        Map<String, Solution> solutions = getSolutions();

        testEesyOneSolution(solutions.get("proc")::solve);
        System.out.println();
        testEasyOneO1MemorySolution(solutions.get("memory")::solve);
    }

    private static Map<String, Solution> getSolutions() {
        Map<String, Solution> solutions = new HashMap<>();
        solutions.put("proc", new EasyOneSolution());
        solutions.put("memory", new EasyOneO1MemorySolution());
        return solutions;
    }

    private static void testEasyOneO1MemorySolution(Function<int[], Integer> EASY_ONE_O1_MEMORY) {
        test(EXPECTED_EXCEPTION, new int[]{}, EASY_ONE_O1_MEMORY);
        test(11, new int[]{11}, EASY_ONE_O1_MEMORY);
        test(3, new int[]{1, 1, 2, 2, 3}, EASY_ONE_O1_MEMORY);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3}, EASY_ONE_O1_MEMORY);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3, 3}, EASY_ONE_O1_MEMORY); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        test(EXPECTED_EXCEPTION, new int[]{1, 2, 3, 4, 5, 6, 7}, EASY_ONE_O1_MEMORY);
        test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, EASY_ONE_O1_MEMORY);
    }

    private static void testEesyOneSolution(Function<int[], Integer> EASY_ONE) {
        test(EXPECTED_EXCEPTION, new int[]{}, EASY_ONE);
        test(11, new int[]{11}, EASY_ONE);
        test(3, new int[]{1, 1, 2, 2, 3}, EASY_ONE);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3}, EASY_ONE);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3, 3}, EASY_ONE); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        test(EXPECTED_EXCEPTION, new int[]{1, 2, 3, 4, 5, 6, 7}, EASY_ONE);
        test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, EASY_ONE);
    }

    private static void test(Exception expectedException, int[] input, Function<int[], Integer> function) {
        try {
            Integer actualResult = function.apply(input);
            String message = String.format(
                    TEST_MESSAGE_PATTERN,
                    "failed",
                    expectedException.getClass().getName(),
                    actualResult,
                    collectArray(input));
            System.out.println(message);

        } catch (Exception actualException) {
            String message = String.format(
                    TEST_MESSAGE_PATTERN,
                    expectedException.getClass() == actualException.getClass() ? "passed" : "failed",
                    expectedException.getClass().getName(),
                    actualException.getClass().getName(),
                    collectArray(input));
            System.out.println(message);

        }

    }

    private static void test(int expectedResult, int[] input, Function<int[], Integer> function) {
        Integer actualResult = function.apply(input);

        String message = String.format(
                TEST_MESSAGE_PATTERN,
                actualResult == expectedResult ? "passed" : "failed",
                expectedResult,
                actualResult,
                collectArray(input));

        System.out.println(message);
    }

    private static String collectArray(int[] input) {
        return Arrays.stream(input).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }
}
