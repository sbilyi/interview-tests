package com.sbilyi.solution.easy;

import java.util.Arrays;

public class EasyOneMemorySolution implements EasyOneSolution {

    @Override
    public int solve(int[] input) throws IllegalArgumentException {
        Integer result = null;

        if (input.length % 2 != 1) {
            throw new IllegalArgumentException("There couldn't be odd number of elements");
        }
        if (input.length == 1) {
            return input[0];
        }

        Arrays.sort(input);
        for (int i = 0; i < input.length; i += 2) {
            if (i + 1 == input.length) {
                result = input[i];
                break;
            }
            if (input[i] != input[i + 1]) {
                if (!alreadyFoundResult(result)) {
                    result = input[i];
                } else {
                    throw new IllegalArgumentException("There couldn't be more than one unique number");
                }
            }
        }

        return result;
    }

    private static boolean alreadyFoundResult(Integer result) {
        return result != null;
    }
}
