package com.sbilyi.solution.moderate;

public class OSquadNModerateOne implements ModerateOne {

    @Override
    public int[] solve(int[] input) {
        if(input.length == 0) {
            return new int[]{};
        } else if(input.length == 1) {
            return new int[]{0};
        }

        int[] result = new int[input.length];

        for(int i = 0; i < input.length; i++) {
            result[i] = 1;
            for(int j = 0; j < input.length; j++) {
                if(i== j) {
                    continue;
                }
                result[i] = result[i] * input[j];
            }
        }
        return result;
    }
}
