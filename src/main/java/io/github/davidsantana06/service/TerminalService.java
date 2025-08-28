package io.github.davidsantana06.service;

import java.util.List;
import java.util.Scanner;

import io.github.davidsantana06.facade.OutputFacade;
import io.github.davidsantana06.model.*;
import io.github.davidsantana06.service.base.GameEngine;

public class TerminalService {

    private static final String ENTRY_BANNER = (
        "    _______                 _______                  ______      \n" +
        "---'   ____)           ---'    ____)___         ---'   ____)____ \n" +
        "      (_____)                    ______)                  ______)\n" +
        "      (_____)                   _______)              __________)\n" +
        "      (____)                   _______)              (____)      \n" +
        "---.__(___)           ---.__________)           ---.__(___)      \n" +
        "                                                                 \n" +
        "                                                                 \n" +
        "                     ROCK PAPER SCISSORS ✚                       \n" +
        "                                                                 \n" +
        "  →  https://github.com/davidsantana06/rock-paper-scissors-plus  \n" +
        "                                                                 \n" +
        "              𝐢 See README.md for details and rules              \n"
    );

    private static final String EXIT_BANNER = (
        "           \n" +
        "   .-.  _  \n" +
        "   | | / ) \n" +
        "   | |/ /  \n" +
        "  _|__ /_  \n" +
        " / __)-' ) \n" +
        " \\  `(.-')\n" +
        "  > ._>-'  \n" +
        " / \\/     \n" +
        "-----------\n" +
        "THX4PLAYING"
    );

    private Scanner scanner;
    private TableService tableService;

    public TerminalService() {
        scanner = new Scanner(System.in);
        tableService = new TableService();
    }

    public void displayEntryBanner() {
        OutputFacade.printBanner(ENTRY_BANNER);
    }

    public void displayGames(List<GameEngine> games) {
        String[][] tabulatedGames = tableService.tabulateGames(games);
        OutputFacade.printTable(tabulatedGames);
    }

    public GameEngine selectGame(List<GameEngine> games) {
        OutputFacade.printLabel("SELECT GAME (or type 0 to exit)");
        int index = readSelectionIndex(games.size());
        OutputFacade.printBox("Game selected", OutputFacade.GREEN);
        return games.get(index);
    }

    public void displayGestures(List<Gesture> gestures) {
        String[][] tabulatedGestures = tableService.tabulateGestures(gestures);
        OutputFacade.printTable(tabulatedGestures);
    }

    public Gesture selectGesture(List<Gesture> gestures) {
        OutputFacade.printLabel("SELECT GESTURE (or type 0 to exit)");
        int index = readSelectionIndex(gestures.size());
        return gestures.get(index);
    }

    public void displayResult(String contextualizedResult, String color) {
        OutputFacade.printBox(contextualizedResult, color);
    }

    public void displayExitBanner() {
        OutputFacade.printBanner(EXIT_BANNER);
    }

    private int readSelectionIndex(int maxValue) throws RuntimeException {
        String input;
        int number = -1;
        boolean isValid = false;

        while (!isValid) {
            input = scanner.nextLine().trim();

            boolean isZero = "0".equals(input);
            if (isZero) throw new RuntimeException("User requested exit");

            boolean isNumber = input.matches("\\d+");
            if (!isNumber) {
                OutputFacade.printBox("Enter a valid number", OutputFacade.RED);
                continue;
            }

            number = Integer.parseInt(input);

            boolean isBetweenInterval = number >= 1 && number <= maxValue;
            if (!isBetweenInterval) {
                OutputFacade.printBox(
                    "Enter a number between 1 and " + maxValue,
                    OutputFacade.RED
                );
                continue;
            }

            isValid = true;
        }
    
        return number - 1;
    }
}
