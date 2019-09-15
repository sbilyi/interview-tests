package com.sbilyi;

import com.sbilyi.solution.TestPrinter;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestCase {
    private final TestPrinter printer;

    public TestCase(TestPrinter printer) {
        this.printer = printer;
    }

    public void test(Exception expectedException, int[] input, Function<int[], Integer> function) {
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

    public <I, O> void test(O expectedResult, I input, Function<I, O> function) {
        O actualResult = function.apply(input);
        Function<O, String> resultCollector = collectorFor(actualResult.getClass());
        BiFunction<O, O, Boolean> resultComparator = comparatorFor(actualResult.getClass());

        printer.print(
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
