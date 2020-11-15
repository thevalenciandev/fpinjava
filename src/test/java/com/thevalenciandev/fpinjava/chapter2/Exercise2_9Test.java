package com.thevalenciandev.fpinjava.chapter2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise2_9Test {

    @Test
    void traditionalJavaFunctionAndCurriedFunctionReturnTheSame() {

        String trad = Exercise2_9.func("1", "2", "3", "4");
        String curr = Exercise2_9.funcCurry().apply("1").apply("2").apply("3").apply("4");

        assertEquals(trad, curr);
    }

}