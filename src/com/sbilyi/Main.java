package com.sbilyi;

import com.sbilyi.solution.Solution;
import com.sbilyi.solution.TestPrinter;
import com.sbilyi.solution.easy.EasyOneMemorySolution;
import com.sbilyi.solution.easy.EasyOneProcSolution;
import com.sbilyi.solution.easy.EasyTwoSolutionImpl;
import com.sbilyi.solution.moderate.BasicModerateSecond;
import com.sbilyi.solution.moderate.ONModerateOne;
import com.sbilyi.solution.moderate.OSquadNModerateOne;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static final IllegalArgumentException EXPECTED_EXCEPTION = new IllegalArgumentException();
    private static final TestPrinter TEST_PRINTER = new TestPrinter();
    private static final String EASY_ONE_PROC_EFF = "easyOneProcEff";
    private static final String EASY_ONE_MEMORY_EFF = "easyOneMemoryEff";
    private static final String EASY_SECOND_SOLUTION = "easy_second_solution";
    private static final String O_SQUAD_N_MODERATE_ONE = "o_squad_n_moderate_one";
    private static final String O_N_MODERATE_ONE = "o_n_moderate_one";
    private static final String BASIC_MODERATE_SECOND = "basic_moderate_second";

    public static void main(String[] args) {
        Map<String, Solution> solutions = getSolutions();

        testEesyOneSolution(solutions.get(EASY_ONE_PROC_EFF));
        System.out.println();
        testEesyOneSolution(solutions.get(EASY_ONE_MEMORY_EFF));
        System.out.println();
        testEasyTwoSolution(solutions.get(EASY_SECOND_SOLUTION));
        System.out.println();
        testModerateOne(solutions.get(O_SQUAD_N_MODERATE_ONE));
        System.out.println();
        testModerateOne(solutions.get(O_N_MODERATE_ONE));
        System.out.println();
        testModerateSecond(solutions.get(BASIC_MODERATE_SECOND));
    }

    private static void testModerateSecond(Solution<Integer, String[]> solution) {
        TEST_PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<Integer, String[]> function = solution::solve;

        test(new String[]{}, 0, function);
        test(new String[]{}, -5, function);
        test(new String[]{"()"}, 1, function);
        test(new String[]{"(())", "()()"}, 2, function);
        test(new String[]{"((()))", "()(())", "(())()", "(()())", "()()()"}, 3, function);
    }

    private static void testModerateOne(Solution<int[], int[]> solution) {
        TEST_PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<int[], int[]> function = solution::solve;

        test(new int[]{}, new int[]{}, function);
        test(new int[]{0}, new int[]{1}, function);
        test(new int[]{2, 1}, new int[]{1, 2}, function);
        test(new int[]{24, 12, 8, 6}, new int[]{1, 2, 3, 4}, function);
        test(new int[]{0, 0, 8, 0}, new int[]{1, 2, 0, 4}, function);
        test(new int[]{120, 60, 40, 30, 24}, new int[]{1, 2, 3, 4, 5}, function);
        test(new int[]{200, 300, 120, 150, 120}, new int[]{3, 2, 5, 4, 5}, function);
    }

    private static void testEasyTwoSolution(Solution<Integer, Integer> solution) {
        TEST_PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<Integer, Integer> function = solution::solve;

        test(0, -5, function);
        test(0, 0, function);
        test(3, 3, function);
        test(5, 4, function);
        test(8, 5, function);
    }

    private static Map<String, Solution> getSolutions() {
        Map<String, Solution> solutions = new HashMap<>();
        solutions.put(EASY_ONE_PROC_EFF, new EasyOneProcSolution());
        solutions.put(EASY_ONE_MEMORY_EFF, new EasyOneMemorySolution());
        solutions.put(EASY_SECOND_SOLUTION, new EasyTwoSolutionImpl());
        solutions.put(O_SQUAD_N_MODERATE_ONE, new OSquadNModerateOne());
        solutions.put(O_N_MODERATE_ONE, new ONModerateOne());
        solutions.put(BASIC_MODERATE_SECOND, new BasicModerateSecond());
        return solutions;
    }

    private static void testEesyOneSolution(Solution<int[], Integer> solution) {
        TEST_PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<int[], Integer> function = solution::solve;

        test(EXPECTED_EXCEPTION, new int[]{}, function);
        test(11, new int[]{11}, function);
        test(3, new int[]{1, 1, 2, 2, 3}, function);
        test(EXPECTED_EXCEPTION, new int[]{1, 1, 2, 2, 3, 3}, function);
        test(3, new int[]{1, 1, 2, 2, 3, 3, 3}, function); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        test(EXPECTED_EXCEPTION, new int[]{1, 2, 3, 4, 5, 6, 7}, function);
        test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, function);
    }

    private static void test(Exception expectedException, int[] input, Function<int[], Integer> function) {
        try {
            Integer actualResult = function.apply(input);
            TEST_PRINTER.print(
                    false,
                    expectedException.getClass().getName(),
                    String.valueOf(actualResult),
                    collectArray(input));
        } catch (Exception actualException) {
            TEST_PRINTER.print(
                    expectedException.getClass() == actualException.getClass(),
                    expectedException.getClass().getName(),
                    actualException.getClass().getName(),
                    "[" + collectArray(input) + "]");
        }
    }

    private static <I, O> void test(O expectedResult, I input, Function<I, O> function) {
        O actualResult = function.apply(input);
        Function<O, String> resultCollector = collectorFor(actualResult.getClass());
        BiFunction<O, O, Boolean> resultComparator = comparatorFor(actualResult.getClass());

        TEST_PRINTER.print(
                resultComparator.apply(expectedResult, actualResult),
                resultCollector.apply(expectedResult),
                resultCollector.apply(actualResult),
                String.valueOf(input));
    }

    private static <O> Function<O, String> collectorFor(Class clazz) {
        if (clazz.isArray()) {
            if (clazz.getName().equals(int[].class.getName())) {
                return (e) -> "[" + collectArray((int[]) e) + "]";
            } else {
                return (e) -> "[" + collectArray((Object[]) e) + "]";
            }
        } else if (Integer.class.equals(clazz)) {
            return (e) -> String.valueOf(e);
        }
        return null;
    }

    private static <O> BiFunction<O, O, Boolean> comparatorFor(Class clazz) {
        if (clazz.isArray()) {
            if (clazz.getName().equals(int[].class.getName())) {
                return (expected, actual) -> Arrays.equals((int[]) expected, (int[]) actual);
            } else {
                return (expected, actual) -> Arrays.equals((Object[]) expected, (Object[]) actual);
            }
        } else if (Integer.class.equals(clazz)) {
            return Object::equals;
        }
        return null;
    }

    private static String collectArray(Object[] input) {
        return Arrays.stream(input).map(String::valueOf).collect(Collectors.joining(","));
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
