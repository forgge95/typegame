package com.forg.typegame;

public class Word {
    private String word;
    private char[] wordCharArray;
    private int charIndex = 0;
    
    public Word(String word){
        this.word = word;
        wordCharArray = word.toCharArray();
    }
    public char[] getWordCharArray() {
        return wordCharArray;
    }
    public int getCharIndex() {
        return charIndex;
    }
    public void setCharIndex(int charIndex) {
        this.charIndex = charIndex;
    }

    @Override
    public String toString() {
        return word;
    }
    @Override
    public boolean equals(Object obj) {
        return word.equals((String)obj);
    }
    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
