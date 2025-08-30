package io.github.davidsantana06.service;

import java.util.List;
import java.util.Set;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.service.base.GameEngine;

public class RockPaperScissorsWell extends GameEngine {
    
    private static final String WELL = "Well";

    private static final Gesture ROCK_GESTURE = new Gesture(
        ROCK, Set.of(SCISSORS), Set.of(PAPER, WELL)
    );

    private static final Gesture PAPER_GESTURE = new Gesture(
        PAPER, Set.of(ROCK, WELL), Set.of(SCISSORS)
    );

    private static final Gesture SCISSORS_GESTURE = new Gesture(
        SCISSORS, Set.of(PAPER), Set.of(ROCK, WELL)
    );

    private static final Gesture WELL_GESTURE = new Gesture(
        WELL, Set.of(ROCK, SCISSORS), Set.of(PAPER)
    );

    public RockPaperScissorsWell() {
        title = "Rock Paper Scissors Well";
        gestures = List.of(ROCK_GESTURE, PAPER_GESTURE, SCISSORS_GESTURE, WELL_GESTURE);
    }
}
