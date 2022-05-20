package com.SenzaNome0;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
    private static final String carattereBaseTerminale = "> ";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_CLEAR = "\033[H\033[2J";

    public static void pulisciSchermo() {
        System.out.print(ANSI_CLEAR);
        System.out.flush();
    }

    public static void chiudiProgramma() {
        System.out.println("\nChiudendo il programma...");
        System.exit(0);
    }

    private static boolean validatoreInput(String[] inputAccettato, String inputUtente) {
        for (String s : inputAccettato)
            if (s.equals(inputUtente)) return true;

        return false;
    }

    public static String stringInput() {
        ricaricaScanner();

        System.out.print(carattereBaseTerminale);

        String input;
        do {
            input = scanner.nextLine();
        } while (input.equals(""));

        return input;
    }

    public static String stringInput(String[] inputAccettato) {
        ricaricaScanner();

        boolean inputErrato = false;

        String input;
        do {
            if (inputErrato) {
                stampaErrore("L'input inserito è invalido...");
                inputErrato = false;
            }

            System.out.print(carattereBaseTerminale);
            input = scanner.nextLine();

            inputErrato = true;
        } while (!validatoreInput(inputAccettato, input) || input.equals(""));

        return input;
    }

    public static int intInput() {
        ricaricaScanner();

        int input = 0;
        boolean inputErrato;

        do {
            try {
                System.out.print(carattereBaseTerminale);
                input = Integer.parseInt(scanner.next());
                inputErrato = false;
            } catch (Exception e) {
                stampaErrore("L'input inserito non è un numero intero...");
                inputErrato = true;
            }
        } while (inputErrato);

        return input;
    }

    public static double doubleInput() {
        ricaricaScanner();

        double input = 0;
        boolean inputErrato;

        do {
            try {
                System.out.print(carattereBaseTerminale);
                input = scanner.nextDouble();
                inputErrato = false;
            } catch (Exception e) {
                stampaErrore("L'input inserito non è un numero...");
                inputErrato = true;
            }
        } while (inputErrato);

        return input;
    }

    public static void stampaErrore(String errorMessage) {
        System.out.println(ANSI_RED + "ERRORE" + ANSI_RESET + ": " + errorMessage);
    }

    public static void stampaSuccesso(String successMessage) {
        System.out.println(ANSI_GREEN  + successMessage + ANSI_RESET);
    }

    private static void ricaricaScanner() {
        scanner = new Scanner(System.in);
    }
}
