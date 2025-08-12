package io.github.davidsantana06.service;

import java.util.List;

import io.github.davidsantana06.model.Gesture;
import io.github.davidsantana06.service.base.GameEngine;

public class TableService {

    private static final String[] GAME_HEADER = { "Nº", "TITLE" };
    private static final String[] GESTURE_HEADER = { "Nº", "GESTURE", "WINS AGAINST", "LOSES AGAINST" };

    public String[][] tabulateGames(List<GameEngine> games) {
        String[][] tabulatedGames = createTable(GAME_HEADER, games);

        int currentRow = 1;
        for (GameEngine game : games) {
            tabulatedGames[currentRow][0] = String.valueOf(currentRow);
            tabulatedGames[currentRow][1] = game.getTitle();
            currentRow++;
        }

        return tabulatedGames;
    }

    public String[][] tabulateGestures(List<Gesture> gestures) {
        String[][] tabulatedGestures = createTable(GESTURE_HEADER, gestures);

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

    private static String[][] createTable(String[] header, List<?> entries) {
        int totalRows = entries.size() + 1;
        int totalColumns = header.length;
        String[][] table = new String[totalRows][totalColumns];
        table[0] = header;
        return table;
    }
}
