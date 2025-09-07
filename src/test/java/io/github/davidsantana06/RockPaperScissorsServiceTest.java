package io.github.davidsantana06;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

import io.github.davidsantana06.base.GameEngineTest;
import io.github.davidsantana06.service.RockPaperScissorsService;

@DisplayName("RockPaperScissorsService Tests")
class RockPaperScissorsServiceTest extends GameEngineTest {

    {
        game = new RockPaperScissorsService();
        expectedGestures = List.of(
            RockPaperScissorsService.ROCK_GESTURE,
            RockPaperScissorsService.PAPER_GESTURE,
            RockPaperScissorsService.SCISSORS_GESTURE
        );
    }
}
