package com.embosfer.fpinjava.chapter2;

public class Exercise2_3 {

    interface BinaryOperator extends Function<Integer, Function<Integer, Integer>> {}

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
        BinaryOperator mult = x -> y -> x * y;

        // Applying curried functions
        System.out.println(add.apply(3).apply(5));
        System.out.println(mult.apply(3).apply(5));
    }
}
