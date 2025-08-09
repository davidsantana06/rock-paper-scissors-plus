package io.github.davidsantana06.service;

import java.util.List;
import java.util.Set;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.service.base.GameEngine;

public class RockPaperScissorsService extends GameEngine {

    public static final Gesture ROCK_GESTURE = new Gesture(
        ROCK, Set.of(SCISSORS), Set.of(PAPER)
    );

    public static final Gesture PAPER_GESTURE = new Gesture(
        PAPER, Set.of(ROCK), Set.of(SCISSORS)
    );

    public static final Gesture SCISSORS_GESTURE = new Gesture(
        SCISSORS, Set.of(PAPER), Set.of(ROCK)
    );

    public RockPaperScissorsService() {
        title = "Rock Paper Scissors (Classic)";
        gestures = List.of(ROCK_GESTURE, PAPER_GESTURE, SCISSORS_GESTURE);
    }
}
