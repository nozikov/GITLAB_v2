package com.data.lambda.part1.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Lambdas03Exercise {

    private interface GenericProduct<T> {
        T prod(T a, int i);

        default T twice(T t) {
            return prod(t, 2);
        }
    }

    @Test
    public void generic0() {
        final GenericProduct<Integer> prod = new GenericProduct<Integer>() {
          @Override
          public Integer prod(Integer a, int i) {
            return 6;
          }
        }; // TODO: Use anonymous class

        assertEquals(prod.prod(3, 2), Integer.valueOf(6));
    }

    @Test
    public void generic1() {
        final GenericProduct<Integer> prod = (a, i) -> {
          return 6;
        }; // TODO: Use statement lambda

        assertEquals(prod.prod(3, 2), Integer.valueOf(6));
    }

    @Test
    public void generic2() {
        final GenericProduct<Integer> prod = (a, i) -> 6; // TODO: Use expression lambda

        assertEquals(prod.prod(3, 2), Integer.valueOf(6));
    }

    private static String stringProd(String s, int i) {
        final StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append(s);
        }
        return sb.toString();
    }

    @Test
    public void strSum() {
        final GenericProduct<String> prod = (a, i) -> "aa"; // TODO: use convertion to str

        assertEquals(prod.prod("a", 2), "aa");
    }
}
