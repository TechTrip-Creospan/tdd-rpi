package com.creospan.rpi.setexample;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class GenericPrimSet<T> {

    public static final String NO_NULLS_MESSAGE = "GenericPrimSet does not allow nulls";

    Object[] mySetContainer = new Object[0];

    public void add(T someObject) {
        if (someObject == null) {
            throw new NullPointerException(NO_NULLS_MESSAGE);
        }

        synchronized(mySetContainer) {
            if (!this.contains(someObject)) {
                mySetContainer = Arrays.copyOf(mySetContainer, mySetContainer.length + 1);
                mySetContainer[mySetContainer.length - 1] = someObject;
            }
        }
    }

    public synchronized boolean contains(T someObject) {
        return Arrays.stream(mySetContainer).anyMatch(valueInSet -> someObject.equals(valueInSet));
    }

    public synchronized int size() {
        return mySetContainer.length;
    }

    public synchronized void clear() {
        mySetContainer = new Object[0];
    }

    public void remove(T someObject) {
        if (!this.contains(someObject)) {
            return;
        }

        Object[] newContainer = new Object[mySetContainer.length -1];

        synchronized(mySetContainer) {
            AtomicInteger index = new AtomicInteger(0);

            Arrays.stream(mySetContainer).forEach(valueInSet -> {
                // Don't put someObject on the left, protect from nulls
                if (!valueInSet.equals(someObject)) {
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