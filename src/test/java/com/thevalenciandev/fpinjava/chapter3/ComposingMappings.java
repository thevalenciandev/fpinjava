package com.thevalenciandev.fpinjava.chapter3;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.*;

public class ComposingMappings {

    public static void main(String[] args) {
        Function<Double, Double> addTax = x -> x * 1.09;
        Function<Double, Double> addShipping = x -> x + 3.50;
        List<Double> prices = list(10.10, 23.45, 32.07, 9.23);

        List<Double> pricesIncludingTax = map(prices, addTax);
        List<Double> pricesIncludingShipping = map(pricesIncludingTax, addShipping);
        System.out.println(pricesIncludingShipping);

        List<Double> pricesWithTaxAndShipment = map(prices, addShipping.compose(addTax));
        System.out.println(pricesWithTaxAndShipment);

        List<Double> pricesWithTaxAndShipment2 = map(prices, addTax.andThen(addShipping));
        System.out.println(pricesWithTaxAndShipment2);

        Consumer<Double> printWith2Decimals = d -> {
            System.out.printf("%.2f", d);
            System.out.println();
        };
        Function<Runnable, Function<Runnable, Runnable>> compose = x -> y -> () -> {
            x.run();
            y.run();
        };
        Runnable ez = () -> {};
        Runnable program = foldLeft(pricesIncludingShipping, ez,
                e -> d -> compose.apply(e).apply(() -> printWith2Decimals.accept(d)));
        program.run();
    }
}
