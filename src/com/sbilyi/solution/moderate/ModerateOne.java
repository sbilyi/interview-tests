package com.sbilyi.solution.moderate;

/**
 * QUESTION MODERATE#1
 * Implement a method moderate1 that given an array of integers nums , returns an array of
 * integers output where output[i] = product of all elements in nums except nums[i]
 * You must solve it with O(n) time-complexity and without using division operation
 * Example
 * Input: [1, 2, 3, 4]
 * Output: [24, 12, 8, 6]
 * From the example, output[1] = product of all elements in nums except nums[1] which is 1 *
 * 3 * 4 = 12
 * (Optional) bonus point: solve with O(1) space-complexity (except the output array)
 */
public interface ModerateOne {

    int[] solve(int[] input);
}
