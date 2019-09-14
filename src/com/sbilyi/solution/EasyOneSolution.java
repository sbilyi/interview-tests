package com.sbilyi.solution;

import java.util.HashSet;
import java.util.Set;

public class EasyOneSolution implements Solution {

    private static final int DEFAULT_VALUE = -1;

    @Override
    public int solve(int[] input) {
        if (input.length % 2 != 1) {
            return DEFAULT_VALUE;
        }

        Set<Integer> markedElements = new HashSet<>();

        for (int v : input) {
            if (markedElements.contains(v)) {
                markedElements.remove(v);
            } else {
                markedElements.add(v);
            }
        }

        return markedElements.size() != 1 ? DEFAULT_VALUE : markedElements.iterator().next();
    }
}
