package br.com.giuladislau.sudoku.ui;

import br.com.giuladislau.sudoku.factory.BoardFactory;
import br.com.giuladislau.sudoku.model.Board;
import br.com.giuladislau.sudoku.model.Difficulty;
import br.com.giuladislau.sudoku.model.GameStatus;
import br.com.giuladislau.sudoku.service.SudokuService;
import br.com.giuladislau.sudoku.util.InputReader;
import br.com.giuladislau.sudoku.util.TerminalUtils;

public class Menu {

    private SudokuService service;

    private final BoardPrinter printer;

    private final InputReader input;

    public Menu() {

        this.printer = new BoardPrinter();

        this.input = new InputReader();
    }

    public void start() {

        Difficulty difficulty = chooseDifficulty();

        Board board = BoardFactory.createBoard(difficulty);

        this.service = new SudokuService(board);

        boolean running = true;

        while (running) {

            TerminalUtils.clearScreen();

            showOptions();

            int option = input.nextInt("Escolha uma opção: ");

            switch (option) {

                case 1 -> showBoard();

                case 2 -> insertNumber();

                case 3 -> removeNumber();

                case 4 -> showGameStatus();

                case 0 -> {

                    running = false;

                    System.out.println("Encerrando jogo...");
                }

                default -> System.out.println("Opção inválida.");
            }

            pause();
        }
    }

    private Difficulty chooseDifficulty() {

        TerminalUtils.clearScreen();

        System.out.println("""
                
                ===== SELECIONE A DIFICULDADE =====
                
                1 - EASY
                2 - MEDIUM
                3 - HARD
                
                """);

        int option = input.nextIntInRange(
                "Escolha uma dificuldade: ",
                1,
                3
        );

        return switch (option) {

            case 1 -> Difficulty.EASY;

            case 2 -> Difficulty.MEDIUM;

            case 3 -> Difficulty.HARD;

            default -> throw new IllegalStateException(
                    "Valor inesperado."
            );
        };
    }

    private void showOptions() {

        System.out.println("""
                
                ===== MENU =====
                
                1 - Mostrar tabuleiro
                2 - Inserir número
                3 - Remover número
                4 - Ver status do jogo
                0 - Sair
                
                """);
    }

    private void showBoard() {

        printer.print(service.getBoard());
    }

    private void insertNumber() {

        int row = input.nextIntInRange(
                "Linha (0-8): ",
                0,
                8
        );

        int col = input.nextIntInRange(
                "Coluna (0-8): ",
                0,
                8
        );

        int value = input.nextIntInRange(
                "Valor (1-9): ",
                1,
                9
        );

        boolean success = service.insertNumber(
                row,
                col,
                value
        );

        if (success) {

            System.out.println(
                    "Número inserido com sucesso."
            );
        }
    }

    private void removeNumber() {

        int row = input.nextIntInRange(
                "Linha (0-8): ",
                0,
                8
        );

        int col = input.nextIntInRange(
                "Coluna (0-8): ",
                0,
                8
        );

        boolean success = service.removeNumber(
                row,
                col
        );

        if (success) {

            System.out.println(
                    "Número removido com sucesso."
            );
        }
    }

    private void showGameStatus() {

        GameStatus status = service.getGameStatus();

        System.out.println(
                "Status do jogo: " + status
        );

        if (status == GameStatus.COMPLETE) {

            System.out.println(
                    "Parabéns! Sudoku concluído."
            );
        }
    }

    private void pause() {

        System.out.println(
                "\nPressione ENTER para continuar..."
        );

        input.nextLine("");
    }
}