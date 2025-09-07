package io.github.davidsantana06.base;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import io.github.davidsantana06.model.*;
import io.github.davidsantana06.service.base.GameEngine;

public abstract class GameEngineTest {

    protected GameEngine game;
    protected List<Gesture> expectedGestures;

    @Test
    void testGetGestures() {
        List<Gesture> actualGestures = game.getGestures();
        assertNotNull(actualGestures);
        assertEquals(expectedGestures.size(), actualGestures.size());
        assertEquals(expectedGestures, actualGestures);
    }

    @Test
    void testGetRandomGesture() {
        int totalIterations = game.getGestures().size() * 3;
        for (int i = 0; i <= totalIterations; i++) {
            Gesture randomGesture = game.getRandomGesture();
            assertNotNull(randomGesture);
            assertTrue(game.getGestures().contains(randomGesture));
        }
    }

    @Test
    void testRandomGestureDistribution() {
        Map<Gesture, Integer> count = new HashMap<>();
        for (Gesture gesture : game.getGestures()) {
            count.put(gesture, 0);
        }

        int totalIterations = game.getGestures().size() * 300;
        for (int i = 0; i < totalIterations; i++) {
            Gesture gesture = game.getRandomGesture();
            count.computeIfPresent(gesture, (key, val) -> val + 1);
        }

        for (Gesture gesture : game.getGestures()) {
            assertTrue(count.get(gesture) >= 1);
        }
    }

    @Test
    void testEvaluateAllMatches() {
        for (Gesture playerGesture : game.getGestures()) {
            for (Gesture computerGesture : game.getGestures()) {
                Hand playerHand = new Hand(playerGesture);
                Hand computerHand = new Hand(computerGesture);

                Result expectedResult;
                Result actualResult;

                boolean wins = playerGesture.winsAgainst().contains(computerGesture.name());
                boolean loses = playerGesture.losesAgainst().contains(computerGesture.name());
                if (wins) expectedResult = Result.WINS;
                else if (loses) expectedResult = Result.LOSES;
                else expectedResult = Result.DRAWS;

                actualResult = game.evaluateMatch(playerHand, computerHand);
                assertEquals(expectedResult, actualResult);
            }
        }
    }

    @Test
    void testContextualizeResult() {
        Hand playerHand = new Hand(game.getRandomGesture());
        Hand computerHand = new Hand(game.getRandomGesture());
        Result result = game.evaluateMatch(playerHand, computerHand);
        String contextualizedResult = game.contextualizeResult(playerHand, computerHand, result);
        assertNotNull(contextualizedResult);
        assertTrue(contextualizedResult.contains(playerHand.gesture().name()));
        assertTrue(contextualizedResult.contains(computerHand.gesture().name()));
    }
}
