package com.thevalenciandev.fpinjava.chapter4;

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

    static Function<Integer, Function<Integer, TailCall<Integer>>> ADD =
            x -> y -> y == 0
                    ? ret(x)
                    : sus(() -> Recursion.ADD.apply(x + 1).apply(y - 1));

    public static void main(String[] args) {
        System.out.println(add(3, 10000));
        System.out.println(addTailCall(3, 1000000));
        System.out.println(ADD.apply(3).apply(10000).eval());
        System.out.println(sum(list(1, 2, 3)));
        System.out.println(sumTail(list(1, 2, 3)));
    }
}
