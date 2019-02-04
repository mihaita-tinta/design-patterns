package com.mihaita.learning.iterator;

public class IteratorTest {


    /**
     * factory method based
     * fail fast
     * navigation algorithm removed from the client
     *
     * have to iterate to a certain position
     * ListIterator can go both ways
     */
    public void test() {


    }

    static class ArrayBasedIterator implements Iterator<String> {

        private final String[] values;
        private int currentIndex;

        ArrayBasedIterator(String[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < values.length && values[currentIndex] != null;
        }

        @Override
        public String next() {
            return values[currentIndex++];
        }
    }
    interface Iterator<T> {

        boolean hasNext();

        T next();
    }
}
