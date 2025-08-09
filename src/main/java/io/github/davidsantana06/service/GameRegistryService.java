package io.github.davidsantana06.service;

import java.util.List;
import java.util.ArrayList;
import java.util.ServiceLoader;

import io.github.davidsantana06.service.base.GameEngine;

public class GameRegistryService {

    private List<GameEngine> availableGames;
    private GameEngine currentGame;

    public GameRegistryService() {
        availableGames = new ArrayList<>();
        ServiceLoader.load(GameEngine.class).forEach(availableGames::add);
    }
    
    public List<GameEngine> getAvailableGames() { return availableGames; }

    public void setCurrentGame(GameEngine currentGame) { 
        this.currentGame = currentGame; 
    }

    public GameEngine getCurrentGame() { return currentGame; }
}
