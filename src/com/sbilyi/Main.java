package com.sbilyi;

import com.sbilyi.solution.EasyOneMemorySolution;
import com.sbilyi.solution.EasyOneProcSolution;
import com.sbilyi.solution.EasyOneSolution;
import com.sbilyi.solution.EasyTwoSolutionImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static final String TEST_MESSAGE_PATTERN = "Test %s expected %s actual %s with %s";
    private static final int DEFAULT_VALUE = EasyOneSolution.DEFAULT_VALUE;
    private static final IllegalArgumentException EXPECTED_EXCEPTION = new IllegalArgumentException();

    public static void main(String[] args) {
        Map<String, EasyOneSolution> solutions = getSolutions();

        testEesyOneSolution(solutions.get("proc")::solve);
        System.out.println();
        testEesyOneSolution(solutions.get("memory")::solve);
        System.out.println();
        testEasyTwoSoltion(new EasyTwoSolutionImpl()::solve);
    }

    private static void testEasyTwoSoltion(Function<Integer, Integer> solution) {
        test(0, -5, solution);
        test(0, 0, solution);
        test(3, 3, solution);
        test(5, 4, solution);
        test(8, 5, solution);
    }

    private static Map<String, EasyOneSolution> getSolutions() {
        Map<String, EasyOneSolution> solutions = new HashMap<>();
        solutions.put("proc", new EasyOneProcSolution());
        solutions.put("memory", new EasyOneMemorySolution());
        return solutions;
    }

    private static void testEesyOneSolution(Function<int[], Integer> function) {
        test(EXPECTED_EXCEPTION, new int[]{}, function);
        test(11, new int[]{11}, function);
        test(3, new int[]{1, 1, 2, 2, 3}, function);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3}, function);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3, 3}, function); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        test(EXPECTED_EXCEPTION, new int[]{1, 2, 3, 4, 5, 6, 7}, function);
        test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, function);
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
                    "[" + collectArray(input) + "]");
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
                "[" + collectArray(input) + "]");

        System.out.println(message);
    }

    private static void test(int expectedResult, int input, Function<Integer, Integer> function) {
        Integer actualResult = function.apply(input);

        String message = String.format(
                TEST_MESSAGE_PATTERN,
                actualResult == expectedResult ? "passed" : "failed",
                expectedResult,
                actualResult,
                input);

        System.out.println(message);
    }

    private static String collectArray(int[] input) {
        return Arrays.stream(input).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }
}
