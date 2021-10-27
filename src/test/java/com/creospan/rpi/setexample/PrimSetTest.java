package com.creospan.rpi.setexample;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrimSetTest {

    PrimSet primSet;

    @BeforeEach
    void setUp() {
        primSet = new PrimSet();
    }

    @DisplayName("TEST")
    @Test
    void testThatSetCanHoldAnObject() {
        String someString = "someString";
        primSet.add(someString);
        assertThat(primSet.contains(someString)).isTrue();
    }

    @Test
    void TestThatSetCannotContainNulls() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> {
            primSet.add(null);
        });

        assertThat(nullPointerException.getMessage()).isEqualTo(PrimSet.NO_NULLS_MESSAGE);
    }

    @Test
    void testThatSetCannotHoldDuplicates() {

        // Think About how to test This
        // Check size equals 1 and
        // Check that it contains the element
        String someString = "someString";

        primSet.add(someString);
        primSet.add(someString);

        assertThat(primSet.size()).isEqualTo(1);
        assertThat(primSet.contains(someString));

    }

    @Test
    void testTHatWeCanPutANumberOfObjectsInTheSet() {
        String[] someStrings = {"String1", "String2", "String3"};

        Arrays.stream(someStrings).forEach(s -> {
            primSet.add(s);
        });

        assertThat(primSet.size()).isEqualTo(someStrings.length);

        Arrays.stream(someStrings).forEach(s -> {
            assertThat(primSet.contains(s));
        });
    }

    @Test
    void testThatTheSetIsEmpty() {
        assertThat(primSet.isEmpty()).isTrue();
    }

    @Test
    void tesThatWeCanClearTheSet() {
        primSet.clear();
        assertThat(primSet.size()).isEqualTo(0);
        assertThat(primSet.isEmpty()).isTrue();
    }

    @Test
    void testThatWeCanRemoveAnObjectFromTheSet() {
        String[] someStrings = {"String1", "String2", "String3"};

        Arrays.stream(someStrings).forEach(s -> {
            primSet.add(s);
        });

        primSet.remove(someStrings[0]);

        assertThat(primSet.contains(someStrings[0])).isFalse();
        assertThat(primSet.size()).isEqualTo(someStrings.length -1);
    }

}