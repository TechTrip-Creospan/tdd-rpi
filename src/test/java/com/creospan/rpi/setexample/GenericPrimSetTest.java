package com.creospan.rpi.setexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenericPrimSetTest {

    GenericPrimSet<Integer> genericPrimSet;
    
    @BeforeEach
    void setUp() {
        genericPrimSet = new GenericPrimSet<>();
    }

    @Test
    @DisplayName("Test that the Set can hold an Object")
    void testThatSetCanHoldAnObject() {
        Integer someInteger = Integer.valueOf(1);
        genericPrimSet.add(someInteger);
        assertThat(genericPrimSet.contains(someInteger)).isTrue();
    }

    @Test
    @DisplayName("Test that the Set cannot hold nulls")
    void TestThatSetCannotContainNulls() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> {
            genericPrimSet.add(null);
        });

        assertThat(nullPointerException.getMessage()).isEqualTo(genericPrimSet.NO_NULLS_MESSAGE);
    }

    @Test
    @DisplayName("Test that the Set cannot hold duplicate values")
    void testThatSetCannotHoldDuplicates() {

        // Think About how to test This
        // Check size equals 1 and
        // Check that it contains the element
        Integer someInteger = Integer.valueOf(1);

        genericPrimSet.add(someInteger);
        genericPrimSet.add(someInteger);

        assertThat(genericPrimSet.size()).isEqualTo(1);
        assertThat(genericPrimSet.contains(someInteger));

    }

    @Test
    @DisplayName("Test that we can put a number of objects in the Set")
    void testTHatWeCanPutANumberOfObjectsInTheSet() {
        Integer[] someStrings = {1, 2, 3};

        Arrays.stream(someStrings).forEach(s -> {
            genericPrimSet.add(s);
        });

        assertThat(genericPrimSet.size()).isEqualTo(someStrings.length);

        Arrays.stream(someStrings).forEach(s -> {
            assertThat(genericPrimSet.contains(s));
        });
    }

    @Test
    @DisplayName("Test that the Set is Empty")
    void testThatTheSetIsEmpty() {
        assertThat(genericPrimSet.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test that we can clear the Set")
    void tesThatWeCanClearTheSet() {
        genericPrimSet.clear();
        assertThat(genericPrimSet.size()).isEqualTo(0);
        assertThat(genericPrimSet.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test that we can remove and Object from the Set")
    void testThatWeCanRemoveAnObjectFromTheSet() {
        Integer[] someStrings = {1, 2, 3};

        Arrays.stream(someStrings).forEach(s -> {
            genericPrimSet.add(s);
        });

        genericPrimSet.remove(someStrings[0]);

        assertThat(genericPrimSet.contains(someStrings[0])).isFalse();
        assertThat(genericPrimSet.size()).isEqualTo(someStrings.length -1);
    }
}