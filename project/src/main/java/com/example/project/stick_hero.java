package com.example.project;
//singleton design patter is being implemented here....

public class stick_hero {
    private int char_score;
    private static stick_hero hero;

    private stick_hero() {
        this.char_score = 0;
    }

    public static stick_hero getInstance() {
        if (hero == null) {
            hero = new stick_hero();
        }
        return hero;

    }
}
