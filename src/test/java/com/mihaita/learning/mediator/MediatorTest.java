package com.mihaita.learning.mediator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MediatorTest {


    /**
     * Interface based
     * minimizes inheritance and handles communication between classes
     * decouples object
     * defines interaction
     *
     * Cons:
     * deity object
     */
    @Test
    public void test() {

        ConcreteMediator mediator = new ConcreteMediator();
        mediator.register(new Receiver());
        mediator.register(new Receiver());

        mediator.startAll();

    }


    static class Receiver {

        public void startFeature() {

        }
    }

    interface Mediator {

        void startAll();
    }

    static class ConcreteMediator implements Mediator {

        private final List<Receiver> receivers = new ArrayList<>();

        public void register(Receiver receiver) {
            receivers.add(receiver);
        }

        @Override
        public void startAll() {
            receivers.forEach(Receiver::startFeature);
        }

    }
}
