package io.github.davidsantana06;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

import io.github.davidsantana06.base.GameEngineTest;
import io.github.davidsantana06.service.RockPaperScissorsNineService;

@DisplayName("RockPaperScissorsNineService Tests")
class RockPaperScissorsNineServiceTest extends GameEngineTest {

    {
        game = new RockPaperScissorsNineService();
        expectedGestures = List.of(
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
    }
}
