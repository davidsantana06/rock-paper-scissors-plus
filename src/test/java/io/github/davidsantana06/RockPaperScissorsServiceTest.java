package io.github.davidsantana06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

import io.github.davidsantana06.model.*;
import io.github.davidsantana06.service.RockPaperScissorsService;

@DisplayName("RockPaperScissorsService Tests")
class RockPaperScissorsServiceTest {

    private RockPaperScissorsService rockPaperScissorsService;

    @BeforeEach
    void setUp() {
        rockPaperScissorsService = new RockPaperScissorsService();
    }

    @Test
    @DisplayName("Should return all three gestures when calling getGestures")
    void testGetGestures() {
        List<Gesture> expectedGestures = List.of(
            RockPaperScissorsService.ROCK_GESTURE,
            RockPaperScissorsService.PAPER_GESTURE,
            RockPaperScissorsService.SCISSORS_GESTURE
        );

        List<Gesture> actualGestures = rockPaperScissorsService.getGestures();

        assertNotNull(actualGestures);
        assertEquals(3, actualGestures.size());
        assertEquals(expectedGestures, actualGestures);
    }

    @RepeatedTest(
        value = 3,
        name = "Random gesture test {currentRepetition}/{totalRepetitions}"
    )
    @DisplayName("Should return a valid gesture from the available gestures when randomizing")
    void testGetRandomGesture() {
        Gesture randomGesture = rockPaperScissorsService.getRandomGesture();

        assertNotNull(randomGesture);
        assertTrue(rockPaperScissorsService.getGestures().contains(randomGesture));
    }

    @Test
    @DisplayName("Should generate all possible gestures over multiple randomizations")
    void testGetRandomGestureDistribution() {
        Map<Gesture, Integer> gestureCount = new HashMap<>();
        for (Gesture g : rockPaperScissorsService.getGestures()) {
            gestureCount.put(g, 0);
        }

        for (int i = 0; i < 900; i++) {
            Gesture randomGesture = rockPaperScissorsService.getRandomGesture();
            gestureCount.computeIfPresent(randomGesture, (gesture, count) -> count + 1);
        }

        for (Gesture g : rockPaperScissorsService.getGestures()) {
            assertTrue(gestureCount.get(g) >= 1);
        }
    }

    @Test
    @DisplayName("Rock should win against Scissors")
    void testEvaluateMatch_JanVsPon() {
        Hand playerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should lose against Paper")
    void testEvaluateMatch_JanVsKen() {
        Hand playerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.PAPER_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should draw with Rock")
    void testEvaluateMatch_JanVsJan() {
        Hand playerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Paper should win against Rock")
    void testEvaluateMatch_KenVsJan() {
        Hand playerHand = new Hand(RockPaperScissorsService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should lose against Scissors")
    void testEvaluateMatch_KenVsPon() {
        Hand playerHand = new Hand(RockPaperScissorsService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should draw with Paper")
    void testEvaluateMatch_KenVsKen() {
        Hand playerHand = new Hand(RockPaperScissorsService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.PAPER_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Scissors should win against Paper")
    void testEvaluateMatch_PonVsKen() {
        Hand playerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.PAPER_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should lose against Rock")
    void testEvaluateMatch_PonVsJan() {
        Hand playerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should draw with Scissors")
    void testEvaluateMatch_PonVsPon() {
        Hand playerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Should contextualize WINS result")
    void testContextualizeResult() {
        Hand playerHand = new Hand(RockPaperScissorsService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsService.SCISSORS_GESTURE);
        Result result = rockPaperScissorsService.evaluateMatch(playerHand, computerHand);

        String contextualizedResult = rockPaperScissorsService.contextualizeResult(
            playerHand, computerHand, result
        );

        assertEquals("Rock wins against Scissors", contextualizedResult);
    }
}
