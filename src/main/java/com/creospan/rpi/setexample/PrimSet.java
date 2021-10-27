package com.creospan.rpi.setexample;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimSet {

    public static final String NO_NULLS_MESSAGE = "PrimSet does not allow nulls";

    String[] mySetContainer = new String[0];

    public void add(String someString) {
        if (someString == null) {
            throw new NullPointerException(NO_NULLS_MESSAGE);
        }

        synchronized(mySetContainer) {
            if (!this.contains(someString)) {
                mySetContainer = Arrays.copyOf(mySetContainer, mySetContainer.length + 1);
                mySetContainer[mySetContainer.length - 1] = someString;
            }
        }
    }

    public synchronized boolean contains(String someString) {
        return Arrays.stream(mySetContainer).anyMatch(valueInSet -> someString.equals(valueInSet));
    }

    public synchronized int size() {
        return mySetContainer.length;
    }

    public synchronized void clear() {
        mySetContainer = new String[0];
    }

    public void remove(String someString) {
        if (!this.contains(someString)) {
            return;
        }

        String[] newContainer = new String[mySetContainer.length -1];

        synchronized(mySetContainer) {
            AtomicInteger index = new AtomicInteger(0);

            Arrays.stream(mySetContainer).forEach(valueInSet -> {
                // Don't put someString on the left, protect from nulls
                if (!valueInSet.equals(someString)) {
                    newContainer[index.getAndIncrement()] = valueInSet;
                }
            });

            mySetContainer = newContainer;
        }
    }

    public boolean isEmpty() {
        return (mySetContainer.length == 0);
    }
}