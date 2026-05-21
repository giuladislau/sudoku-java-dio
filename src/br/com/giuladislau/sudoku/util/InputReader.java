package br.com.giuladislau.sudoku.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public int nextInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value = scanner.nextInt();

                scanner.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println("Entrada inválida. Digite apenas números.");

                scanner.nextLine();
            }
        }
    }

    public String nextLine(String message) {

        System.out.print(message);

        return scanner.nextLine();
    }
    
    public int nextIntInRange(String message, int min, int max) {

        while (true) {

            int value = nextInt(message);

            if (value >= min && value <= max) {
                return value;
            }

            System.out.printf(
                    "Digite um valor entre %d e %d.%n",
                    min,
                    max
            );
        }
    }
}