package com.thevalenciandev.fpinjava.chapter3;

import java.util.List;

import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.foldLeft;
import static com.thevalenciandev.fpinjava.chapter3.CollectionUtilities.list;

public class Store {

    record Product(String name, double price, double weight) {}

    record OrderLine(Product product, int count) {

        public double weight() {
            return product.weight() * count;
        }

        public double amount() {
            return product.price() * count;
        }

    }

    public static void main(String[] args) {
        Product toothPaste = new Product("Tooth paste", 1.5, 0.5);
        Product toothBrush = new Product("Tooth brush", 3.5, 0.3);
        List<OrderLine> order = list(
                new OrderLine(toothPaste, 2),
                new OrderLine(toothBrush, 3));

        double weight = foldLeft(order, 0.0, x -> y -> x + y.weight());
        double price = foldLeft(order, 0.0, x -> y -> x + y.amount());

        System.out.println(String.format("Total weight: %s", weight));
        System.out.println(String.format("Total price: %s", price));
    }
}