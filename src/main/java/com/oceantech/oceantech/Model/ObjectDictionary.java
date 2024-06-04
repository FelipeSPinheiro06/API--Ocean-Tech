package com.oceantech.oceantech.Model;

public enum ObjectDictionary {

    PLASTIC(1),
    METAL(2),
    GLASS(3),
    PAPER(4),
    ORGANIC(5);

    private int score;

    ObjectDictionary(int score) {
        this.score = score;
    }

    ObjectDictionary() {

    }

    public int getScore() {
        return score;
    }
}
