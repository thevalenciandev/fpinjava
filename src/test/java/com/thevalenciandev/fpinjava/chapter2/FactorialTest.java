package com.thevalenciandev.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static com.thevalenciandev.fpinjava.chapter2.Factorial.*;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void factorialTest() {
        assertEquals(120, factorial2.apply(5));
    }

}