package io.github.davidsantana06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.model.Hand;
import io.github.davidsantana06.model.Result;
import io.github.davidsantana06.service.RockPaperScissorsLizardSpockService;

@DisplayName("RockPaperScissorsLizardSpockService Tests")
class RockPaperScissorsLizardSpockServiceTest {

    private RockPaperScissorsLizardSpockService rockPaperScissorsLizardSpockService;

    @BeforeEach
    void setUp() {
        rockPaperScissorsLizardSpockService = new RockPaperScissorsLizardSpockService();
    }

    @Test
    @DisplayName("Should return all five gestures when calling getGestures")
    void testGetGestures() {
        List<Gesture> expectedGestures = List.of(
            RockPaperScissorsLizardSpockService.ROCK_GESTURE,
            RockPaperScissorsLizardSpockService.PAPER_GESTURE,
            RockPaperScissorsLizardSpockService.SCISSORS_GESTURE,
            RockPaperScissorsLizardSpockService.LIZARD_GESTURE,
            RockPaperScissorsLizardSpockService.SPOCK_GESTURE
        );

        List<Gesture> actualGestures = rockPaperScissorsLizardSpockService.getGestures();

        assertNotNull(actualGestures);
        assertEquals(5, actualGestures.size());
        assertEquals(expectedGestures, actualGestures);
    }

    @RepeatedTest(
        value = 25,
        name = "Random gesture test {currentRepetition}/{totalRepetitions}"
    )
    @DisplayName("Should return a valid gesture from the available gestures when randomizing")
    void testGetRandomGesture() {
        Gesture randomGesture = rockPaperScissorsLizardSpockService.getRandomGesture();

        assertNotNull(randomGesture);
        assertTrue(rockPaperScissorsLizardSpockService.getGestures().contains(randomGesture));
    }

    @Test
    @DisplayName("Should generate all possible gestures over multiple randomizations")
    void testGetRandomGestureDistribution() {
        Map<Gesture, Integer> gestureCount = new HashMap<>();
        gestureCount.put(RockPaperScissorsLizardSpockService.ROCK_GESTURE, 0);
        gestureCount.put(RockPaperScissorsLizardSpockService.PAPER_GESTURE, 0);
        gestureCount.put(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE, 0);
        gestureCount.put(RockPaperScissorsLizardSpockService.LIZARD_GESTURE, 0);
        gestureCount.put(RockPaperScissorsLizardSpockService.SPOCK_GESTURE, 0);

        for (int i = 0; i < 1500; i++) {
            Gesture randomGesture = rockPaperScissorsLizardSpockService.getRandomGesture();
            gestureCount.compute(randomGesture, (gesture, count) -> count + 1);
        }

        assertTrue(gestureCount.get(RockPaperScissorsLizardSpockService.ROCK_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsLizardSpockService.PAPER_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsLizardSpockService.LIZARD_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsLizardSpockService.SPOCK_GESTURE) >= 1);
    }

    @Test
    @DisplayName("Rock should win against Scissors")
    void testEvaluateMatch_RockWinsAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should win against Lizard")
    void testEvaluateMatch_RockWinsAgainstLizard() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should lose against Paper")
    void testEvaluateMatch_RockLosesAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should lose against Spock")
    void testEvaluateMatch_RockLosesAgainstSpock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should draw with Rock")
    void testEvaluateMatch_RockDrawsWithRock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Paper should win against Rock")
    void testEvaluateMatch_PaperWinsAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should win against Spock")
    void testEvaluateMatch_PaperWinsAgainstSpock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should lose against Scissors")
    void testEvaluateMatch_PaperLosesAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should lose against Lizard")
    void testEvaluateMatch_PaperLosesAgainstLizard() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should draw with Paper")
    void testEvaluateMatch_PaperDrawsWithPaper() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Scissors should win against Paper")
    void testEvaluateMatch_ScissorsWinsAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should win against Lizard")
    void testEvaluateMatch_ScissorsWinsAgainstLizard() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should lose against Rock")
    void testEvaluateMatch_ScissorsLosesAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should lose against Spock")
    void testEvaluateMatch_ScissorsLosesAgainstSpock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should draw with Scissors")
    void testEvaluateMatch_ScissorsDrawsWithScissors() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Lizard should win against Spock")
    void testEvaluateMatch_LizardWinsAgainstSpock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Lizard should win against Paper")
    void testEvaluateMatch_LizardWinsAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Lizard should lose against Rock")
    void testEvaluateMatch_LizardLosesAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Lizard should lose against Scissors")
    void testEvaluateMatch_LizardLosesAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Lizard should draw with Lizard")
    void testEvaluateMatch_LizardDrawsWithLizard() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Spock should win against Scissors")
    void testEvaluateMatch_SpockWinsAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Spock should win against Rock")
    void testEvaluateMatch_SpockWinsAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Spock should lose against Lizard")
    void testEvaluateMatch_SpockLosesAgainstLizard() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.LIZARD_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Spock should lose against Paper")
    void testEvaluateMatch_SpockLosesAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.PAPER_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Spock should draw with Spock")
    void testEvaluateMatch_SpockDrawsWithSpock() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.SPOCK_GESTURE);

        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Should contextualize LOSES result")
    void testContextualizeResult() {
        Hand playerHand = new Hand(RockPaperScissorsLizardSpockService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsLizardSpockService.ROCK_GESTURE);
        Result result = rockPaperScissorsLizardSpockService.evaluateMatch(playerHand, computerHand);

        String contextualizedResult = rockPaperScissorsLizardSpockService.contextualizeResult(
            playerHand, computerHand, result
        );

        assertEquals("Scissors loses against Rock", contextualizedResult);
    }
}
