package com.sbilyi.solution;

/**
 * QUESTION EASY#1
 * Implement a method easy1 that given an integer array, which each element appears twice
 * except for one. Find that one
 * You must solve it with O(n) time-complexity
 * <p>
 * Example #1
 * Input: [1, 1, 2, 2, 3 ]
 * Output: 3
 * <p>
 * Example #2
 * Input: [-1, 2, 4 , 2, -1]
 * Output: 4
 * <p>
 * (Optional) bonus point: solve with O(1) space-complexity
 *
 */
public interface EasyOneSolution {
    int DEFAULT_VALUE = -1;
    int solve(int[] input) throws IllegalArgumentException;
}
