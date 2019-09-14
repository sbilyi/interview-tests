package com.sbilyi.solution;

import java.util.Arrays;

public class EasyOneMemorySafeSolution implements Solution {
    @Override
    public int solve(int[] input) {
        int result = DEFAULT_VALUE;

        if (input.length % 2 != 1) {
            return DEFAULT_VALUE;
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
                if (!alreadyFoundResult(result, DEFAULT_VALUE)) {
                    result = input[i];
                } else {
                    return DEFAULT_VALUE;
                }
            }
        }

        return result;
    }

    private static boolean alreadyFoundResult(int result, int i) {
        return result != i;
    }
}
