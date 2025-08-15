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
import io.github.davidsantana06.service.RockPaperScissorsNineService;

@DisplayName("RockPaperScissorsNineService Tests")
class RockPaperScissorsNineServiceTest {

    private RockPaperScissorsNineService rockPaperScissorsNineService;

    @BeforeEach
    void setUp() {
        rockPaperScissorsNineService = new RockPaperScissorsNineService();
    }

    @Test
    @DisplayName("Should return all nine gestures when calling getGestures")
    void testGetGestures() {
        List<Gesture> expectedGestures = List.of(
            RockPaperScissorsNineService.ROCK_GESTURE,
            RockPaperScissorsNineService.PAPER_GESTURE,
            RockPaperScissorsNineService.SCISSORS_GESTURE,
            RockPaperScissorsNineService.FIRE_GESTURE,
            RockPaperScissorsNineService.HUMAN_GESTURE,
            RockPaperScissorsNineService.SPONGE_GESTURE,
            RockPaperScissorsNineService.AIR_GESTURE,
            RockPaperScissorsNineService.WATER_GESTURE,
            RockPaperScissorsNineService.GUN_GESTURE
        );

        List<Gesture> actualGestures = rockPaperScissorsNineService.getGestures();

        assertNotNull(actualGestures);
        assertEquals(9, actualGestures.size());
        assertEquals(expectedGestures, actualGestures);
    }

    @RepeatedTest(
        value = 30,
        name = "Random gesture test {currentRepetition}/{totalRepetitions}"
    )
    @DisplayName("Should return a valid gesture from the available gestures when randomizing")
    void testGetRandomGesture() {
        Gesture randomGesture = rockPaperScissorsNineService.getRandomGesture();

        assertNotNull(randomGesture);
        assertTrue(rockPaperScissorsNineService.getGestures().contains(randomGesture));
    }

    @Test
    @DisplayName("Should generate all possible gestures over multiple randomizations")
    void testGetRandomGestureDistribution() {
        Map<Gesture, Integer> gestureCount = new HashMap<>();
        gestureCount.put(RockPaperScissorsNineService.ROCK_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.PAPER_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.SCISSORS_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.FIRE_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.HUMAN_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.SPONGE_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.AIR_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.WATER_GESTURE, 0);
        gestureCount.put(RockPaperScissorsNineService.GUN_GESTURE, 0);

        for (int i = 0; i < 2700; i++) {
            Gesture randomGesture = rockPaperScissorsNineService.getRandomGesture();
            gestureCount.compute(randomGesture, (gesture, count) -> count + 1);
        }

        assertTrue(gestureCount.get(RockPaperScissorsNineService.ROCK_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.PAPER_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.SCISSORS_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.FIRE_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.HUMAN_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.SPONGE_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.AIR_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.WATER_GESTURE) >= 1);
        assertTrue(gestureCount.get(RockPaperScissorsNineService.GUN_GESTURE) >= 1);
    }

    @Test
    @DisplayName("Rock should win against Fire")
    void testEvaluateMatch_RockWinsAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should win against Scissors")
    void testEvaluateMatch_RockWinsAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should win against Human")
    void testEvaluateMatch_RockWinsAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should win against Sponge")
    void testEvaluateMatch_RockWinsAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Rock should lose against Paper")
    void testEvaluateMatch_RockLosesAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should lose against Air")
    void testEvaluateMatch_RockLosesAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should lose against Water")
    void testEvaluateMatch_RockLosesAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should lose against Gun")
    void testEvaluateMatch_RockLosesAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Rock should draw with Rock")
    void testEvaluateMatch_RockDrawsWithRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Paper should win against Rock")
    void testEvaluateMatch_PaperWinsAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should win against Air")
    void testEvaluateMatch_PaperWinsAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should win against Water")
    void testEvaluateMatch_PaperWinsAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should win against Gun")
    void testEvaluateMatch_PaperWinsAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Paper should lose against Scissors")
    void testEvaluateMatch_PaperLosesAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should lose against Fire")
    void testEvaluateMatch_PaperLosesAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should lose against Human")
    void testEvaluateMatch_PaperLosesAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should lose against Sponge")
    void testEvaluateMatch_PaperLosesAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Paper should draw with Paper")
    void testEvaluateMatch_PaperDrawsWithPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Scissors should win against Paper")
    void testEvaluateMatch_ScissorsWinsAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should win against Human")
    void testEvaluateMatch_ScissorsWinsAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should win against Sponge")
    void testEvaluateMatch_ScissorsWinsAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should win against Air")
    void testEvaluateMatch_ScissorsWinsAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Scissors should lose against Rock")
    void testEvaluateMatch_ScissorsLosesAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should lose against Fire")
    void testEvaluateMatch_ScissorsLosesAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should lose against Water")
    void testEvaluateMatch_ScissorsLosesAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should lose against Gun")
    void testEvaluateMatch_ScissorsLosesAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Scissors should draw with Scissors")
    void testEvaluateMatch_ScissorsDrawsWithScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Fire should win against Scissors")
    void testEvaluateMatch_FireWinsAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Fire should win against Paper")
    void testEvaluateMatch_FireWinsAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Fire should win against Human")
    void testEvaluateMatch_FireWinsAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Fire should win against Sponge")
    void testEvaluateMatch_FireWinsAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Fire should lose against Rock")
    void testEvaluateMatch_FireLosesAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Fire should lose against Air")
    void testEvaluateMatch_FireLosesAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Fire should lose against Water")
    void testEvaluateMatch_FireLosesAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Fire should lose against Gun")
    void testEvaluateMatch_FireLosesAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Fire should draw with Fire")
    void testEvaluateMatch_FireDrawsWithFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Human should win against Sponge")
    void testEvaluateMatch_HumanWinsAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Human should win against Paper")
    void testEvaluateMatch_HumanWinsAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Human should win against Air")
    void testEvaluateMatch_HumanWinsAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Human should win against Water")
    void testEvaluateMatch_HumanWinsAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Human should lose against Rock")
    void testEvaluateMatch_HumanLosesAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Human should lose against Scissors")
    void testEvaluateMatch_HumanLosesAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Human should lose against Fire")
    void testEvaluateMatch_HumanLosesAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Human should lose against Gun")
    void testEvaluateMatch_HumanLosesAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Human should draw with Human")
    void testEvaluateMatch_HumanDrawsWithHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Sponge should win against Paper")
    void testEvaluateMatch_SpongeWinsAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Sponge should win against Air")
    void testEvaluateMatch_SpongeWinsAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Sponge should win against Water")
    void testEvaluateMatch_SpongeWinsAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Sponge should win against Gun")
    void testEvaluateMatch_SpongeWinsAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Sponge should lose against Rock")
    void testEvaluateMatch_SpongeLosesAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Sponge should lose against Scissors")
    void testEvaluateMatch_SpongeLosesAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Sponge should lose against Fire")
    void testEvaluateMatch_SpongeLosesAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Sponge should lose against Human")
    void testEvaluateMatch_SpongeLosesAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Sponge should draw with Sponge")
    void testEvaluateMatch_SpongeDrawsWithSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Air should win against Fire")
    void testEvaluateMatch_AirWinsAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Air should win against Rock")
    void testEvaluateMatch_AirWinsAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Air should win against Water")
    void testEvaluateMatch_AirWinsAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Air should win against Gun")
    void testEvaluateMatch_AirWinsAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Air should lose against Paper")
    void testEvaluateMatch_AirLosesAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Air should lose against Scissors")
    void testEvaluateMatch_AirLosesAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Air should lose against Human")
    void testEvaluateMatch_AirLosesAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Air should lose against Sponge")
    void testEvaluateMatch_AirLosesAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Air should draw with Air")
    void testEvaluateMatch_AirDrawsWithAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Water should win against Rock")
    void testEvaluateMatch_WaterWinsAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Water should win against Fire")
    void testEvaluateMatch_WaterWinsAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Water should win against Scissors")
    void testEvaluateMatch_WaterWinsAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Water should win against Gun")
    void testEvaluateMatch_WaterWinsAgainstGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Water should lose against Paper")
    void testEvaluateMatch_WaterLosesAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Water should lose against Human")
    void testEvaluateMatch_WaterLosesAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Water should lose against Sponge")
    void testEvaluateMatch_WaterLosesAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Water should lose against Air")
    void testEvaluateMatch_WaterLosesAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Water should draw with Water")
    void testEvaluateMatch_WaterDrawsWithWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Gun should win against Rock")
    void testEvaluateMatch_GunWinsAgainstRock() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.ROCK_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Gun should win against Scissors")
    void testEvaluateMatch_GunWinsAgainstScissors() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SCISSORS_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Gun should win against Human")
    void testEvaluateMatch_GunWinsAgainstHuman() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.HUMAN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.WINS, result);
    }

    @Test
    @DisplayName("Gun should lose against Paper")
    void testEvaluateMatch_GunLosesAgainstPaper() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Gun should lose against Sponge")
    void testEvaluateMatch_GunLosesAgainstSponge() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.SPONGE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Gun should lose against Air")
    void testEvaluateMatch_GunLosesAgainstAir() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.AIR_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Gun should lose against Water")
    void testEvaluateMatch_GunLosesAgainstWater() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.WATER_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Gun should lose against Fire")
    void testEvaluateMatch_GunLosesAgainstFire() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.FIRE_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.LOSES, result);
    }

    @Test
    @DisplayName("Gun should draw with Gun")
    void testEvaluateMatch_GunDrawsWithGun() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.GUN_GESTURE);

        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        assertEquals(Result.DRAWS, result);
    }

    @Test
    @DisplayName("Should contextualize DRAWS result")
    void testContextualizeResult() {
        Hand playerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Hand computerHand = new Hand(RockPaperScissorsNineService.PAPER_GESTURE);
        Result result = rockPaperScissorsNineService.evaluateMatch(playerHand, computerHand);

        String contextualizedResult = rockPaperScissorsNineService.contextualizeResult(
            playerHand, computerHand, result
        );

        assertEquals("Paper draws with Paper", contextualizedResult);
    }
}
