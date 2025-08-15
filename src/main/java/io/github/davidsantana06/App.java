package io.github.davidsantana06;

import io.github.davidsantana06.model.*;
import io.github.davidsantana06.service.GameRegistryService;
import io.github.davidsantana06.service.TerminalService;
import io.github.davidsantana06.service.base.GameEngine;

public class App {

    public static void main(String[] args) {
        GameRegistryService gameRegistryService = new GameRegistryService();
        TerminalService terminalService = new TerminalService();

        terminalService.displayEntryBanner();
        terminalService.displayGames(gameRegistryService.getAvailableGames());

        try {
            GameEngine currentGame = terminalService.selectGame(
                gameRegistryService.getAvailableGames()
            );
            gameRegistryService.setCurrentGame(currentGame);

            while (true) { 
                terminalService.displayGestures(currentGame.getGestures());

                Gesture playerGesture = terminalService.selectGesture(
                    currentGame.getGestures()
                );
                Hand playerHand = new Hand(playerGesture);
                Hand computerHand = new Hand(currentGame.getRandomGesture());
                Result result = currentGame.evaluateMatch(playerHand, computerHand);
                String contextualizedResult = currentGame.contextualizeResult(
                    playerHand, computerHand, result
                );

                terminalService.displayResult(contextualizedResult, result.getColor());
            }
        } catch (RuntimeException e) {
            terminalService.displayExitBanner();
        }
    }
}
