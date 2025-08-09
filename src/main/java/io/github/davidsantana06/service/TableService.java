package io.github.davidsantana06.service;

import java.util.List;

import io.github.davidsantana06.service.base.GameEngine;
import io.github.davidsantana06.model.Gesture;

public class TableService {

    public String[][] tabulateGames(List<GameEngine> games) {
        int totalRows = games.size() + 1;
        int totalColumns = 2;

        String[][] tabulatedGames = new String[totalRows][totalColumns];
        tabulatedGames[0] = new String[] {"Nº", "TITLE"};

        int currentRow = 1;
        for (GameEngine game : games) {
            tabulatedGames[currentRow][0] = String.valueOf(currentRow);
            tabulatedGames[currentRow][1] = game.getTitle();
            currentRow++;
        }

        return tabulatedGames;
    }

    public String[][] tabulateGestures(List<Gesture> gestures) {
        int totalRows = gestures.size() + 1;
        int totalColumns = 4;

        String[][] tabulatedGestures = new String[totalRows][totalColumns];
        tabulatedGestures[0] = new String[] {
            "Nº", "GESTURE", "WINS AGAINST", "LOSES AGAINST"
        };

        int currentRow = 1;
        for (Gesture gesture : gestures) {
            tabulatedGestures[currentRow][0] = String.valueOf(currentRow);
            tabulatedGestures[currentRow][1] = gesture.name();
            tabulatedGestures[currentRow][2] = String.join(", ", gesture.winsAgainst());
            tabulatedGestures[currentRow][3] = String.join(", ", gesture.losesAgainst());
            currentRow++;
        }

        return tabulatedGestures;
    }
}
