package com.sbilyi.solution;

public class Printer {
    private static final String PASSED = "OK.......";
    private static final String FAILED = "--> Fail.";
    private static final String TEST_MESSAGE_PATTERN = "%s expected %s actual %s with %s";

    public void print(boolean passed, String expectedValue, String actualValue, String input) {
        String message = String.format(
                TEST_MESSAGE_PATTERN,
                passed ? PASSED : FAILED,
                expectedValue,
                actualValue,
                input);
        System.out.println(message);
    }

}
