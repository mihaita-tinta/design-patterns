package com.mihaita.learning.chain;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class ChaingOfResponsabilityTest {

    /**
     * Advantages:
     * - loose coupling
     * - decouple sender and receiver
     * - receiver has reference of the next receiver
     *
     * Disadvantages:
     * - handling/handler guarantee
     * - runtime configuration risk
     * - chain length issue
     */
    @Test
    public void test_chainOfResponsability() {
        Handler handler1 = new PositiveRequestHandler();
        Handler handler2 = new NegativeRequestHandler();
        handler1.successor = handler2;
        handler2.successor = new DefaultRequestHandler();

        handler1.handle(new Request(10));
        handler1.handle(new Request(-10));
        handler2.handle(new Request(10));

    }
    static class PositiveRequestHandler extends Handler {
        private static final Logger log = Logger.getLogger(PositiveRequestHandler.class.getSimpleName());

        @Override
        public void handle(Request request) {
            if (request.value >= 0) {
                log.info("handle - value: " + request.value);
            } else {
                successor.handle(request);
            }
        }
    }

    static class NegativeRequestHandler extends Handler {
        private static final Logger log = Logger.getLogger(NegativeRequestHandler.class.getSimpleName());

        @Override
        public void handle(Request request) {
            if (request.value < 0) {
                log.info("handle - value: " + request.value);
            } else {
                successor.handle(request);
            }
        }
    }


    static class DefaultRequestHandler extends Handler {
        private static final Logger log = Logger.getLogger(DefaultRequestHandler.class.getSimpleName());

        @Override
        public void handle(Request request) {
            log.info("handle - value: " + request.value);
        }
    }


    static abstract class Handler {

        protected Handler successor;

        public abstract void handle(Request request);
    }

    static class Request {
        private int value;

        Request(int value) {
            this.value = value;
        }
    }
}
