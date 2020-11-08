package com.embosfer.fpinjava.chapter2;

import static com.embosfer.fpinjava.chapter2.Exercise2_1.*;

public class OverflowStackByComposingFunctions {

    public static void main(String[] args) {
        int fnum = 10_000;
        Function<Integer, Integer> g = x -> x;
        Function<Integer, Integer> f = x -> x + 1;

        for (int i = 0; i < fnum; i++) {
            g = compose(f, g);
        }

        System.out.println(g.apply(0));

    }
}
