package io.github.davidsantana06;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

import io.github.davidsantana06.base.GameEngineTest;
import io.github.davidsantana06.service.RockPaperScissorsLizardSpockService;

@DisplayName("RockPaperScissorsLizardSpockService Tests")
class RockPaperScissorsLizardSpockServiceTest extends GameEngineTest {

    {
        game = new RockPaperScissorsLizardSpockService();
        expectedGestures = List.of(
            RockPaperScissorsLizardSpockService.ROCK_GESTURE,
            RockPaperScissorsLizardSpockService.PAPER_GESTURE,
            RockPaperScissorsLizardSpockService.SCISSORS_GESTURE,
            RockPaperScissorsLizardSpockService.LIZARD_GESTURE,
            RockPaperScissorsLizardSpockService.SPOCK_GESTURE
        );
    }
}
