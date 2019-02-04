package com.mihaita.learning.strategy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategyTest {

    /**
     * Choose algorithm at runtime
     * eliminate conditional statements
     */
    @Test
    public void test() {

        List<EntityA> list = new ArrayList<>();
        list.add(EntityA.of("c"));
        list.add(EntityA.of("b"));
        list.add(EntityA.of("a"));
        Collections.sort(list, (a, b) ->  a.value.compareTo(b.value));
    }
    static class EntityA {
        String value;

        public static EntityA of(String value) {
            EntityA a = new EntityA();
            a.value = value;
            return a;
        }
    }
}
