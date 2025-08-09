package io.github.davidsantana06.service;

import java.util.List;
import java.util.Set;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.service.base.GameEngine;

public class RockPaperScissorsLizardSpockService extends GameEngine {

    private static final String LIZARD = "Lizard";
    private static final String SPOCK = "Spock";

    public static final Gesture ROCK_GESTURE = new Gesture(
        ROCK, Set.of(SCISSORS, LIZARD), Set.of(PAPER, SPOCK)
    );

    public static final Gesture PAPER_GESTURE = new Gesture(
        PAPER, Set.of(ROCK, SPOCK), Set.of(SCISSORS, LIZARD)
    );

    public static final Gesture SCISSORS_GESTURE = new Gesture(
        SCISSORS, Set.of(PAPER, LIZARD), Set.of(ROCK, SPOCK)
    );

    public static final Gesture LIZARD_GESTURE = new Gesture(
        LIZARD, Set.of(SPOCK, PAPER), Set.of(ROCK, SCISSORS)
    );

    public static final Gesture SPOCK_GESTURE = new Gesture(
        SPOCK, Set.of(SCISSORS, ROCK), Set.of(LIZARD, PAPER)
    );

    public RockPaperScissorsLizardSpockService() {
        title = "Rock Paper Scissors Lizard Spock";
        gestures = List.of(
            ROCK_GESTURE,
            PAPER_GESTURE,
            SCISSORS_GESTURE,
            LIZARD_GESTURE,
            SPOCK_GESTURE
        );
    }
}
