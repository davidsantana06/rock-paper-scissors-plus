package io.github.davidsantana06.service;

import java.util.List;
import java.util.Set;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.service.base.GameEngine;

public class RockPaperScissorsNineService extends GameEngine {

    private static final String FIRE = "Fire";
    private static final String HUMAN = "Human";
    private static final String SPONGE = "Sponge";
    private static final String AIR = "Air";
    private static final String WATER = "Water";
    private static final String GUN = "Gun";

    public static final Gesture ROCK_GESTURE = new Gesture(
        ROCK,
        Set.of(FIRE, SCISSORS, HUMAN, SPONGE),
        Set.of(PAPER, AIR, WATER, GUN)
    );

    public static final Gesture PAPER_GESTURE = new Gesture(
        PAPER,
        Set.of(ROCK, AIR, WATER, GUN),
        Set.of(SCISSORS, FIRE, HUMAN, SPONGE)
    );

    public static final Gesture SCISSORS_GESTURE = new Gesture(
        SCISSORS,
        Set.of(PAPER, HUMAN, SPONGE, AIR),
        Set.of(ROCK, FIRE, WATER, GUN)
    );

    public static final Gesture FIRE_GESTURE = new Gesture(
        FIRE,
        Set.of(SCISSORS, PAPER, HUMAN, SPONGE),
        Set.of(ROCK, AIR, WATER, GUN)
    );

    public static final Gesture HUMAN_GESTURE = new Gesture(
        HUMAN,
        Set.of(SPONGE, PAPER, AIR, WATER),
        Set.of(ROCK, SCISSORS, FIRE, GUN)
    );

    public static final Gesture SPONGE_GESTURE = new Gesture(
        SPONGE,
        Set.of(PAPER, AIR, WATER, GUN),
        Set.of(ROCK, SCISSORS, FIRE, HUMAN)
    );

    public static final Gesture AIR_GESTURE = new Gesture(
        AIR,
        Set.of(FIRE, ROCK, WATER, GUN),
        Set.of(PAPER, SCISSORS, HUMAN, SPONGE)
    );

    public static final Gesture WATER_GESTURE = new Gesture(
        WATER,
        Set.of(ROCK, FIRE, SCISSORS, GUN),
        Set.of(PAPER, HUMAN, SPONGE, AIR)
    );

    public static final Gesture GUN_GESTURE = new Gesture(
        GUN,
        Set.of(ROCK, SCISSORS, HUMAN),
        Set.of(PAPER, SPONGE, AIR, WATER, FIRE)
    );

    public RockPaperScissorsNineService() {
        title = "Rock Paper Scissors Nine (RPS-9)";
        gestures = List.of(
            ROCK_GESTURE,
            PAPER_GESTURE,
            SCISSORS_GESTURE,
            FIRE_GESTURE,
            HUMAN_GESTURE,
            SPONGE_GESTURE,
            AIR_GESTURE,
            WATER_GESTURE,
            GUN_GESTURE
        );
    }
}
