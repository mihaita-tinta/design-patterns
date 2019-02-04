package com.mihaita.learning.memento;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Stack;

public class MementoTest {

    /**
     * example: Serializable
     */
    @Test
    public void test() {
        EntityA a = new EntityA(10);
        InMemoryRepository repository = new InMemoryRepository();

        repository.save(a);

        a.a = 20;

        repository.save(a);

        a.a = 5;
        repository.save(a);
        repository.rollback(a);
        repository.rollback(a);

        System.out.println("a = " + a.a);
    }


    class EntityA {// originator
        int a;

        EntityA(int a) {
            this.a = a;
        }

        void revertFromMemento(EntityAMemento memento) {
            this.a = memento.a;
        }
        EntityAMemento createMemento() {
            EntityAMemento memento = new EntityAMemento();
            memento.a = this.a;
            return memento;
        }
    }

    class InMemoryRepository { // caretaker
        private Stack<EntityAMemento> history = new Stack<>();

        public void save(EntityA entity) {
            history.push(entity.createMemento());
        }

        public void rollback(EntityA entity) {
            Optional.ofNullable(history.pop())
                    .ifPresent(memento -> entity.revertFromMemento(memento));
        }
    }

    class EntityAMemento {
        int a;

    }
}
