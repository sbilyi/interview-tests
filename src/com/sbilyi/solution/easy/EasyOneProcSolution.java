package com.sbilyi.solution.easy;

import com.sbilyi.solution.Solution;

import java.util.HashSet;
import java.util.Set;

public class EasyOneProcSolution implements Solution<int[], Integer> {

    @Override
    public Integer solve(int[] input) throws IllegalArgumentException {
        if (input.length % 2 != 1) {
            throw new IllegalArgumentException("There couldn't be odd number of elements");
        }

        Set<Integer> markedElements = new HashSet<>();

        for (int v : input) {
            if (markedElements.contains(v)) {
                markedElements.remove(v);
            } else {
                markedElements.add(v);
            }
        }

        if (markedElements.size() != 1) {
            throw new IllegalArgumentException("There couldn't be more than one unique number");
        } else {
            return markedElements.iterator().next();
        }
    }
}
