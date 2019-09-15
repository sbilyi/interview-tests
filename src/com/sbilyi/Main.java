package com.sbilyi;

import com.sbilyi.solution.Printer;
import com.sbilyi.solution.easy.EasyOneMemorySolution;
import com.sbilyi.solution.easy.EasyOneProcSolution;
import com.sbilyi.solution.easy.EasyOneSolution;
import com.sbilyi.solution.easy.EasyTwoSolutionImpl;
import com.sbilyi.solution.moderate.BasicModerateSecond;
import com.sbilyi.solution.moderate.ONModerateOne;
import com.sbilyi.solution.moderate.OSquadNModerateOne;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static final IllegalArgumentException EXPECTED_EXCEPTION = new IllegalArgumentException();
    private static final Printer printer = new Printer();


    public static void main(String[] args) {
        Map<String, EasyOneSolution> solutions = getSolutions();

        testEesyOneSolution(solutions.get("proc")::solve);
        System.out.println();
        testEesyOneSolution(solutions.get("memory")::solve);
        System.out.println();
        testEasyTwoSoltion(new EasyTwoSolutionImpl()::solve);
        System.out.println();
        testModerateOne(new OSquadNModerateOne()::solve);
        System.out.println();
        testModerateOne(new ONModerateOne()::solve);
        System.out.println();
        testModerateSecond(new BasicModerateSecond()::solve);
    }

    private static void testModerateSecond(Function<Integer, String[]> function) {
        test(new String[]{}, 0, function);
        test(new String[]{}, -5, function);
        test(new String[]{"()"}, 1, function);
        test(new String[]{"(())", "()()"}, 2, function);
        test(new String[]{"((()))", "()(())", "(())()", "(()())", "()()()"}, 3, function);
    }

    private static void testModerateOne(Function<int[], int[]> function) {
        test(new int[]{}, new int[]{}, function);
        test(new int[]{0}, new int[]{1}, function);
        test(new int[]{2, 1}, new int[]{1, 2}, function);
        test(new int[]{24, 12, 8, 6}, new int[]{1, 2, 3, 4}, function);
        test(new int[]{0, 0, 8, 0}, new int[]{1, 2, 0, 4}, function);
        test(new int[]{120, 60, 40, 30, 24}, new int[]{1, 2, 3, 4, 5}, function);
        test(new int[]{200, 300, 120, 150, 120}, new int[]{3, 2, 5, 4, 5}, function);
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
            printer.print(
                    false,
                    expectedException.getClass().getName(),
                    String.valueOf(actualResult),
                    collectArray(input));
        } catch (Exception actualException) {
            printer.print(
                    expectedException.getClass() == actualException.getClass(),
                    expectedException.getClass().getName(),
                    actualException.getClass().getName(),
                    "[" + collectArray(input) + "]");
        }
    }

    private static void test(int expectedResult, int[] input, Function<int[], Integer> function) {
        Integer actualResult = function.apply(input);

        printer.print(
                actualResult == expectedResult,
                String.valueOf(expectedResult),
                String.valueOf(actualResult),
                "[" + collectArray(input) + "]");
    }

    private static void test(int expectedResult, int input, Function<Integer, Integer> function) {
        Integer actualResult = function.apply(input);

        printer.print(
                actualResult == expectedResult,
                String.valueOf(expectedResult),
                String.valueOf(actualResult),
                String.valueOf(input));
    }

    private static void test(int[] expectedResult, int[] input, Function<int[], int[]> function) {
        int[] actualResult = function.apply(input);

        printer.print(
        Arrays.equals(expectedResult, actualResult),
                "[" + collectArray(expectedResult) + "]",
                "[" + collectArray(actualResult) + "]",
                "[" + collectArray(input) + "]");
    }

    private static void test(String[] expectedResult, int input, Function<Integer, String[]> function) {
        String[] actualResult = function.apply(input);

        printer.print(
                Arrays.equals(expectedResult, actualResult),
                "[" + collectArray(expectedResult) + "]",
                "[" + collectArray(actualResult) + "]",
                String.valueOf(input));
    }

    private static String collectArray(int[] input) {
        return Arrays.stream(input).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    private static String collectArray(String[] input) {
        if (input.length == 0) {
            return "";
        } else {
            return Arrays.stream(input).map(String::valueOf).map(e -> "\'" + e + "\'").collect(Collectors.joining(","));
        }
    }
}
