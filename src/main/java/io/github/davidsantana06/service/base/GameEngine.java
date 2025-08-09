package io.github.davidsantana06.service.base;

import java.util.List;
import java.util.Random;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.model.Hand;
import io.github.davidsantana06.model.Result;

public abstract class GameEngine {

    private static final Random RANDOM = new Random();

    protected static final String ROCK = "Rock";
    protected static final String PAPER = "Paper";
    protected static final String SCISSORS = "Scissors";

    protected String title;
    protected List<Gesture> gestures;

    public String getTitle() { return title; }

    public List<Gesture> getGestures() { return gestures; }

    public Gesture getRandomGesture() {
        int index = RANDOM.nextInt(gestures.size());
        return gestures.get(index);
    }

    public Result evaluateMatch(Hand playerHand, Hand computerHand) {
        boolean wins = playerHand.gesture().winsAgainst().contains(
            computerHand.gesture().name()
        );
        if (wins) return Result.WINS;

        boolean loses = playerHand.gesture().losesAgainst().contains(
            computerHand.gesture().name()
        );
        if (loses) return Result.LOSES;

        return Result.DRAWS;
    }

    public String contextualizeResult(Hand playerHand, Hand computerHand, Result result) {
        return (
            playerHand.gesture().name()
            + " " + result.getRelation() + " " +
            computerHand.gesture().name()
        );
    }
}
