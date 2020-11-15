package com.thevalenciandev.fpinjava.chapter2;

/**
 * Done with currying...
 */
public class Function3 {

    public static void main(String[] args) {

        // Given a tax rate (double) and a price (double), return price with added tax (double)
        Function<Double, Function<Double, Double>> addTax = taxRate -> price -> price + price * taxRate;

        System.out.println(addTax.apply(0.09).apply(12.00));
    }

}
