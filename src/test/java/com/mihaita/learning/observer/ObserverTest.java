package com.mihaita.learning.observer;

import org.junit.jupiter.api.Test;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class ObserverTest {

    /**
     * One to many observers
     * decouple objects
     * event handling
     * tipically synch
     */
    @Test
    public void test() {
        EntityA a  = new EntityA();
        a.addObserver(new EntityAListener());
        a.setA(1);
        a.setA(2);
    }

    class EntityA extends Observable {

        int a;

        public void setA(int a) {
            this.a = a;
            setChanged();
            notifyObservers(a);
        }
    }

    class EntityAListener implements Observer {
        private final Logger log = Logger.getLogger(EntityAListener.class.getSimpleName());

        @Override
        public void update(Observable o, Object arg) {
            log.info("update - observable: " + o + ", args: " + arg);
        }
    }

}
