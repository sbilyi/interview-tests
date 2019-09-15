package com.sbilyi.solution.moderate;

import java.util.Stack;

public class ONModerateOne implements ModerateOne {

    @Override
    public int[] solve(int[] input) {
        if (input.length == 0) {
            return new int[]{};
        } else if (input.length == 1) {
            return new int[]{0};
        }

        Stack<Pair> store = new Stack<>();
        store.push(new Pair(1, 1));

        int[] result = new int[input.length];

        for (int i = input.length - 1; i >= 0; i--) {
            store.push(new Pair(input[i], store.peek().value * store.peek().product));
        }

        store.push(new Pair(1, 1));
        for (int i = 0; i < input.length; i++) {
            int prev = store.pop().value;
            result[i] = prev * store.peek().product;
            store.push(new Pair(prev * store.pop().value, 1));
        }

        return result;
    }

    class Pair {
        int value;
        int product;

        public Pair(int value, int product) {
            this.value = value;
            this.product = product;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", product=" + product +
                    '}';
        }
    }
}
