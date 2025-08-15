package io.github.davidsantana06;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        gameRegistryService = new GameRegistryService();
    }

    @Test
    @DisplayName("Should load exactly three game services")
    void testGetAvailableGames() {
        List<GameEngine> expectedGames = List.of(
            new RockPaperScissorsService(),
            new RockPaperScissorsNineService(),
            new RockPaperScissorsLizardSpockService()
        );
        List<GameEngine> actualGames = gameRegistryService.getAvailableGames();

        assertEquals(expectedGames.size(), actualGames.size());

        assertTrue(
            actualGames.stream().anyMatch(game -> game instanceof RockPaperScissorsService)
        );
        assertTrue(
            actualGames.stream().anyMatch(game -> game instanceof RockPaperScissorsNineService)
        );
        assertTrue(
            actualGames.stream().anyMatch(
                game -> game instanceof RockPaperScissorsLizardSpockService
            )
        );
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
