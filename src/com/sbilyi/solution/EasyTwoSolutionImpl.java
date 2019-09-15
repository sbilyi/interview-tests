package com.sbilyi.solution;

import java.util.Arrays;

public class EasyTwoSolutionImpl implements EasyTwoSolution {

    private Integer[] cache = new Integer[2];

    public EasyTwoSolutionImpl() {
        cache[0] = 1;
        cache[1] = 2;
    }

    @Override
    public int solve(int input) {
        if (input <= 0) {
            return 0;
        }

        if (cache.length < input) {
            cache = Arrays.copyOf(cache, input);
        }
        if (cache[input - 1] == null) {
            cache[input - 1] = solve(input - 1) + solve(input - 2);
        }

        return cache[input - 1];
    }

}