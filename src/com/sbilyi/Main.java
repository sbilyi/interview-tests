package com.sbilyi;

import com.sbilyi.solution.Solution;
import com.sbilyi.solution.TestPrinter;
import com.sbilyi.solution.easy.EasyOneMemorySolution;
import com.sbilyi.solution.easy.EasyOneProcSolution;
import com.sbilyi.solution.easy.EasyTwoSolutionImpl;
import com.sbilyi.solution.moderate.BasicModerateSecond;
import com.sbilyi.solution.moderate.ONModerateOne;
import com.sbilyi.solution.moderate.OSquadNModerateOne;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {

    private static final TestPrinter PRINTER = new TestPrinter();
    private static final TestCase testCase = new TestCase(PRINTER);

    private static final String EASY_ONE_PROC_EFF = "easyOneProcEff";
    private static final String EASY_ONE_MEMORY_EFF = "easyOneMemoryEff";
    private static final String EASY_SECOND_SOLUTION = "easy_second_solution";
    private static final String O_SQUAD_N_MODERATE_ONE = "o_squad_n_moderate_one";
    private static final String O_N_MODERATE_ONE = "o_n_moderate_one";
    private static final String BASIC_MODERATE_SECOND = "basic_moderate_second";

    public static void main(String[] args) {
        Map<String, Solution> solutions = getSolutions();
        defaultTest(solutions);
    }

    private static void defaultTest(Map<String, Solution> solutions) {
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
        PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<Integer, String[]> function = solution::solve;

        testCase.test(new String[]{}, 0, function);
        testCase.test(new String[]{}, -5, function);
        testCase.test(new String[]{"()"}, 1, function);
        testCase.test(new String[]{"(())", "()()"}, 2, function);
        testCase.test(new String[]{"((()))", "()(())", "(())()", "(()())", "()()()"}, 3, function);
    }

    private static void testModerateOne(Solution<int[], int[]> solution) {
        PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<int[], int[]> function = solution::solve;

        testCase.test(new int[]{}, new int[]{}, function);
        testCase.test(new int[]{0}, new int[]{1}, function);
        testCase.test(new int[]{2, 1}, new int[]{1, 2}, function);
        testCase.test(new int[]{24, 12, 8, 6}, new int[]{1, 2, 3, 4}, function);
        testCase.test(new int[]{0, 0, 8, 0}, new int[]{1, 2, 0, 4}, function);
        testCase.test(new int[]{120, 60, 40, 30, 24}, new int[]{1, 2, 3, 4, 5}, function);
        testCase.test(new int[]{200, 300, 120, 150, 120}, new int[]{3, 2, 5, 4, 5}, function);
    }

    private static void testEasyTwoSolution(Solution<Integer, Integer> solution) {
        PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<Integer, Integer> function = solution::solve;

        testCase.test(0, -5, function);
        testCase.test(0, 0, function);
        testCase.test(3, 3, function);
        testCase.test(5, 4, function);
        testCase.test(8, 5, function);
    }

    private static void testEesyOneSolution(Solution<int[], Integer> solution) {
        final IllegalArgumentException expectedException = new IllegalArgumentException();
        PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<int[], Integer> function = solution::solve;

        testCase.test(expectedException, new int[]{}, function);
        testCase.test(11, new int[]{11}, function);
        testCase.test(3, new int[]{1, 1, 2, 2, 3}, function);
        testCase.test(expectedException, new int[]{1, 1, 2, 2, 3, 3}, function);
        testCase.test(3, new int[]{1, 1, 2, 2, 3, 3, 3}, function); // this is OK due to the issue limitations, the input should'n have more than 2 copies of same obj
        testCase.test(expectedException, new int[]{1, 2, 3, 4, 5, 6, 7}, function);
        testCase.test(7, new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1}, function);
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

}
