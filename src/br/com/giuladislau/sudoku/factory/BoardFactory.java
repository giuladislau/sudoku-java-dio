package br.com.giuladislau.sudoku.factory;

import br.com.giuladislau.sudoku.model.Board;
import br.com.giuladislau.sudoku.model.Cell;
import br.com.giuladislau.sudoku.model.Difficulty;

public class BoardFactory {

    public static Board createBoard(Difficulty difficulty) {

        int[][] initialBoard;

        switch (difficulty) {

            case EASY -> initialBoard = getEasyBoard();

            case MEDIUM -> initialBoard = getMediumBoard();

            case HARD -> initialBoard = getHardBoard();

            default -> throw new IllegalArgumentException(
                    "Dificuldade inválida."
            );
        }

        return buildBoard(initialBoard);
    }

    private static Board buildBoard(int[][] initialBoard) {

        Cell[][] cells = new Cell[9][9];

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                int value = initialBoard[row][col];

                boolean fixed = value != 0;

                cells[row][col] = new Cell(
                        row,
                        col,
                        value,
                        fixed
                );
            }
        }

        return new Board(cells);
    }

    private static int[][] getEasyBoard() {

        return new int[][] {

                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},

                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},

                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
    }

    private static int[][] getMediumBoard() {

        return new int[][] {

                {0, 0, 0, 6, 0, 0, 4, 0, 0},
                {7, 0, 0, 0, 0, 3, 6, 0, 0},
                {0, 0, 0, 0, 9, 1, 0, 8, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 0, 1, 8, 0, 0, 0, 3},
                {0, 0, 0, 3, 0, 6, 0, 4, 5},

                {0, 4, 0, 2, 0, 0, 0, 6, 0},
                {9, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 1, 0, 0}
        };
    }

    private static int[][] getHardBoard() {

        return new int[][] {

                {0, 0, 0, 0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 1, 0, 9, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 3, 0, 0},
                {0, 5, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }
}