package com.forg.typegame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordTest {
    private Word testWordObj = new Word("testWord");
    private String testWord = "testWord";
    @Test
    void testGetWordCharArray() {
        char[] charArray = {'t','e','s','t','W','o','r','d'};
        assertArrayEquals(charArray, testWordObj.getWordCharArray(), "Character arrays have different values in them");
    }
    @Test
    void testWordHashCode() {
        assertEquals(testWord.hashCode(), testWordObj.hashCode(), "Hash codes of strings don't match");
    }
    @Test
    void testWordEquals() {
        assertTrue(testWordObj.equals("testWord"), "Word isn't equal to it's value during it's initialisation");
    }
    @Test
    void testToString() {
        assertEquals(testWord, testWordObj.toString(), "Word object toString() method works incorectly");
    }
    
}
