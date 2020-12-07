package com.thevalenciandev.fpinjava.chapter4;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.*;
import static com.thevalenciandev.fpinjava.chapter4.TailCall.ret;
import static com.thevalenciandev.fpinjava.chapter4.TailCall.sus;

public class Recursion {

    static int add(int x, int y) {
        return y == 0
                ? x
                : add(++x, --y);
    }

    static int addTailCall(int x, int y) {
        return addTailCallRec(x, y).eval();
    }

    private static TailCall<Integer> addTailCallRec(int x, int y) {
        return y == 0
                ? ret(x)
                : sus((() -> addTailCallRec(x + 1, y - 1)));
    }

    static int sum(List<Integer> list) {
        return list.isEmpty()
                ? 0
                : head(list) + sum(tail(list));
    }

    static int sumTail(List<Integer> list) {
        return sumTailHelper(list, 0);
    }

    static int sumTailHelper(List<Integer> list, int acc) {
        return list.isEmpty()
                ? acc
                : sumTailHelper(tail(list), acc + head(list));
    }

    static Function<Integer, Function<Integer, Integer>> ADD = x -> y -> {
        class AddHelper {
            final Function<Integer, Function<Integer, TailCall<Integer>>> addHelper = a -> b -> b == 0
                    ? ret(a)
                    : sus(() -> this.addHelper.apply(a + 1).apply(b - 1));
        }
        return new AddHelper().addHelper.apply(x).apply(y).eval();
    };

    static int fibonacci(int number) {
        if (number == 0 || number == 1) {
            return number;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }

    static BigInteger fibonacciTailRec(int number) {
        return fib_(BigInteger.valueOf(number), BigInteger.ONE, BigInteger.ZERO).eval();
    }

    private static TailCall<BigInteger> fib_(BigInteger number, BigInteger acc1, BigInteger acc2) {
        return BigInteger.ZERO.equals(number)
                ? ret(BigInteger.ZERO)
                : BigInteger.ONE.equals(number)
                    ? ret(acc1.add(acc2))
                    : sus(() -> fib_(number.subtract(BigInteger.ONE), acc2, acc1.add(acc2)));
    }

    public static void main(String[] args) {
//        System.out.println(add(3, 10000)); Will throw StackOverflow
        System.out.println(addTailCall(3, 1000000));
        System.out.println(ADD.apply(3).apply(10000));
        System.out.println(sum(list(1, 2, 3)));
        System.out.println(sumTail(list(1, 2, 3)));

        range(0, 11).forEach(i -> System.out.print(fibonacci(i) + " "));
        System.out.println();
        range(0, 5000).forEach(i -> System.out.print(fibonacciTailRec(i) + " "));
    }
}
