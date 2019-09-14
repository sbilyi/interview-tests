package com.sbilyi;

import com.sbilyi.solution.EasyOneMemorySafeSolution;
import com.sbilyi.solution.EasyOneSolution;
import com.sbilyi.solution.Solution;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static final String TEST_MESSAGE_PATTERN = "Test %s expected %d actual %d with [%s]";
    private static final int DEFAULT_VALUE = Solution.DEFAULT_VALUE;

    /**
     * QUESTION EASY#1
     * Implement a method easy1 that given an integer array, which each element appears twice
     * except for one. Find that one
     * You must solve it with O(n) time-complexity
     *
     * Example #1
     *  Input: [1, 1, 2, 2, 3 ]
     *  Output: 3
     *
     * Example #2
     *  Input: [-1, 2, 4 , 2, -1]
     *  Output: 4
     *
     * (Optional) bonus point: solve with O(1) space-complexity
     *
     * @param args
     */

    public static void main(String[] args) {
        Solution easyOneSolution = new EasyOneSolution();
        Solution memoryBasedSolution = new EasyOneMemorySafeSolution();
        Function<int[], Integer> EASYONE = easyOneSolution::solve;
        Function<int[], Integer> EASYONEO1MEMORY = memoryBasedSolution::solve;

        test(DEFAULT_VALUE, new int[]{}, EASYONE);
        test(11, new int[]{11}, EASYONE);
        test(3, new int[]{1, 1, 2, 2, 3}, EASYONE);
        test(DEFAULT_VALUE, new int[]{1, 1, 2, 2, 3, 3}, EASYONE);
        test(DEFAULT_VALUE, new int[]{1, 1, 2, 2, 3, 3, 3}, EASYONE); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        test(DEFAULT_VALUE, new int[]{1, 2, 3, 4, 5, 6, 7}, EASYONE);
        test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, EASYONE);

        System.out.println();

        test(DEFAULT_VALUE, new int[]{}, EASYONEO1MEMORY);
        test(11, new int[]{11}, EASYONEO1MEMORY);
        test(3, new int[]{1, 1, 2, 2, 3}, EASYONEO1MEMORY);
        test(DEFAULT_VALUE, new int[]{1, 1, 2, 2, 3, 3}, EASYONEO1MEMORY);
        test(DEFAULT_VALUE, new int[]{1, 1, 2, 2, 3, 3, 3}, EASYONEO1MEMORY); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        test(DEFAULT_VALUE, new int[]{1, 2, 3, 4, 5, 6, 7}, EASYONEO1MEMORY);
        test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, EASYONEO1MEMORY);
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
