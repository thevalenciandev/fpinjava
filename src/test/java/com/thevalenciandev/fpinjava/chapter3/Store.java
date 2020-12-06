package com.thevalenciandev.fpinjava.chapter3;

import java.util.List;
import java.util.function.Function;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.foldLeft;
import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.list;

public class Store {

    record Price(double value) {

        public static final Price ZERO = new Price(0.0);
        public static final Function<Weight, Function<OrderLine, Weight>> SUM = x -> y -> x.add(y.weight());

        public Price add(Price that) {
            return new Price(this.value + that.value);
        }

        public Price mult(int count) {
            return new Price(this.value * count);
        }

    }

    record Weight(double value) {

        public static final Weight ZERO = new Weight(0.0);
        public static final Function<Price, Function<OrderLine, Price>> SUM = x -> y -> x.add(y.amount());

        public Weight add(Weight that) {
            return new Weight(this.value + that.value);
        }

        public Weight mult(int count) {
            return new Weight(this.value * count);
        }
    }

    record Product(String name, Price price, Weight weight) {}

    record OrderLine(Product product, int count) {

        public Weight weight() {
            return product.weight().mult(count);
        }

        public Price amount() {
            return product.price().mult(count);
        }

    }

    public static void main(String[] args) {
        Product toothPaste = new Product("Tooth paste", new Price(1.5), new Weight(0.5));
        Product toothBrush = new Product("Tooth brush", new Price(3.5), new Weight(0.3));
        List<OrderLine> order = list(
                new OrderLine(toothPaste, 2),
                new OrderLine(toothBrush, 3));

        Weight weight = foldLeft(order, Weight.ZERO, Price.SUM);
        Price price = foldLeft(order, Price.ZERO, Weight.SUM);

        System.out.println(String.format("Total weight: %s", weight));
        System.out.println(String.format("Total price: %s", price));
    }
}