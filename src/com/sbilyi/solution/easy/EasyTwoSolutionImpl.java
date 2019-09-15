package com.sbilyi.solution.easy;

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

        int cacheIndex = input - 1;
        ensureCapacity(input);

        if (cache[cacheIndex] == null) {
            cache[cacheIndex] = solve(input - 1) + solve(input - 2);
        }

        return cache[cacheIndex];
    }

    private void ensureCapacity(int input) {
        if (cache.length < input) {
            cache = Arrays.copyOf(cache, input);
        }
    }

}