package com.sbilyi.solution.moderate;

import com.sbilyi.solution.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BasicModerateSecond implements Solution<Integer, String[]> {

    @Override
    public String[] solve(Integer numberOfParentheses) {
        if (numberOfParentheses <= 0) {
            return new String[]{};
        } else if (numberOfParentheses == 1) {
            return new String[]{"()"};
        }

        String[] result = solve(numberOfParentheses - 1);
        Stack<String> prev = new Stack<>();
        List<String> actual = new ArrayList<>();

        for (int i = result.length - 1; i >= 0; i--) {
            prev.push(result[i]);
        }

        for(; !prev.empty(); ) {
            String str = prev.pop();
            actual.add("(" + str + ")");
            actual.add("()" + str);
            if(str.contains("((")) {
                actual.add(str + "()");
            }
        }

        return actual.toArray(new String[0]);
    }

    private String[] ensureCapacity(String[] array, int input) {
        if (array.length < input) {
            return Arrays.copyOf(array, input);
        } else {
            return array;
        }
    }
}
