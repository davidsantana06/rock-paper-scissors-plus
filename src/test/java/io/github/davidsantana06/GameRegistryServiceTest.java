package io.github.davidsantana06;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import io.github.davidsantana06.service.GameRegistryService;
import io.github.davidsantana06.service.RockPaperScissorsLizardSpockService;
import io.github.davidsantana06.service.RockPaperScissorsNineService;
import io.github.davidsantana06.service.RockPaperScissorsService;
import io.github.davidsantana06.service.base.GameEngine;

@DisplayName("GameRegistryService Tests")
class GameRegistryServiceTest {

    private GameRegistryService gameRegistryService;

    {
        gameRegistryService = new GameRegistryService();
    }

    @Test
    @DisplayName("Should load exactly three game services")
    void testGetAvailableGames() {
        List<Class<? extends GameEngine>> expectedGameClasses = List.of(
            RockPaperScissorsService.class,
            RockPaperScissorsNineService.class,
            RockPaperScissorsLizardSpockService.class
        );
        List<GameEngine> actualGames = gameRegistryService.getAvailableGames();

        assertEquals(expectedGameClasses.size(), actualGames.size());

        for (Class<? extends GameEngine> gameClass : expectedGameClasses) {
            assertTrue(
                actualGames.stream().anyMatch(game -> gameClass.isInstance(game))
            );
        }
    }

    @Test
    @DisplayName("Should have current game undefined initially")
    void testGetCurrentGame_InitiallyNull() {
        GameEngine currentGame = gameRegistryService.getCurrentGame();
        assertNull(currentGame);
    }

    @Test
    @DisplayName("Should have current game defined after setting")
    void testGetCurrentGame_AfterSetting() {
        GameEngine expectedGame = new RockPaperScissorsService();
        gameRegistryService.setCurrentGame(expectedGame);
        GameEngine actualGame = gameRegistryService.getCurrentGame();
        assertNotNull(actualGame);
        assertEquals(expectedGame, actualGame);
    }
}
