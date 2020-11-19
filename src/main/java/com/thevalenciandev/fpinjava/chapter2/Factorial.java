package com.thevalenciandev.fpinjava.chapter2;

public class Factorial {

    // Not so great way of defining it, it's not final
    static Function<Integer, Integer> factorial = null;
    static {
        factorial = n -> n == 0 ? 1 : n * factorial.apply(n - 1);
    }

    // better way of defining it
    final static Function<Integer, Integer> factorial2 =
            factorial = n -> n == 0 ? 1 : n * Factorial.factorial2.apply(n - 1);
}
