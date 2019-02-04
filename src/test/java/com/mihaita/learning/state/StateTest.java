package com.mihaita.learning.state;

import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    public void testWithoutState() {
        Fan f = new Fan();
        f.up();
        f.down();
    }

    @Test
    public void testWithState() {
        BetterFan f = new BetterFan();
        f.up();
        f.down();
    }

    class Fan {
        final int OFF = 0;
        final int LOW = 1;
        final int HIGH = 2;

        int state = OFF;

        public void up() {
            switch (state) {
                case HIGH:
                case LOW: {
                    state = HIGH;
                    break;
                }
                default:
                    state = LOW;
            }
        }

        public void down() {
            switch (state) {
                case HIGH: {
                    state = LOW;
                    break;
                }
                case LOW:
                default:
                    state = OFF;
            }

        }
    }

    abstract class State {

        public abstract void up();
        public abstract void down();
    }
    class OffState extends State{
        private final BetterFan fan;

        OffState(BetterFan fan) {
            this.fan = fan;
        }

        @Override
        public void up() {
            fan.state = fan.LOW;
        }

        @Override
        public void down() {
            fan.state = fan.OFF;
        }
    }
    class LowState extends State{
        private final BetterFan fan;

        LowState(BetterFan fan) {
            this.fan = fan;
        }

        @Override
        public void up() {
            fan.state = fan.HIGH;
        }

        @Override
        public void down() {
            fan.state = fan.OFF;
        }
    }
    class HighState extends State{
        private final BetterFan fan;

        HighState(BetterFan fan) {
            this.fan = fan;
        }

        @Override
        public void up() {
            fan.state = fan.HIGH;
        }

        @Override
        public void down() {
            fan.state = fan.LOW;
        }
    }

    class BetterFan {
        final State OFF = new OffState(this);
        final State LOW = new LowState(this);
        final State HIGH = new HighState(this);
        State state = OFF;

        public void up() {
            state.up();
        }
        public void down() {
            state.down();
        }
    }
}
