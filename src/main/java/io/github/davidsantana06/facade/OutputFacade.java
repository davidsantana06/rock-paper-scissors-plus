package io.github.davidsantana06.facade;

public class OutputFacade {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    public static void printTable(String[][] table) {
        int[] columnWidths = calculateColumnWidths(table);
        for (String[] row : table) {
            for (int i = 0; i < columnWidths.length; i++) {
                String format = "%s%-" + columnWidths[i] + "s%s";
                System.out.printf(format, WHITE, row[i], RESET);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printBanner(String text) {
        System.out.printf("%s%s%s", CYAN, text, RESET);
        System.out.println();
    }

    public static void printBox(String text, String color) {
        int boxLength = text.length() + 4;
        System.out.println();
        printHorizontalBorder(boxLength);
        System.out.printf("| %s%s%s |\n", color, text, RESET);
        printHorizontalBorder(boxLength);
        System.out.println();
    }

    public static void printLabel(String text) {
        System.out.printf("%s> %s: %s", CYAN, text, RESET);
    }

    private static int calculateMaxColumnLength(String[][] table, int columnIndex) {
        int maxLength = 0;
        for (String[] row : table) {
            int currentLength = row[columnIndex].length();
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    private static int[] calculateColumnWidths(String[][] table) {
        int totalColumns = table[0].length;
        int[] columnWidths = new int[totalColumns];
        for (int i = 0; i < totalColumns; i++) {
            columnWidths[i] = calculateMaxColumnLength(table, i) + 2;
        }
        return columnWidths;
    }

    private static void printHorizontalBorder(int length) {
        System.out.print("+");
        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
