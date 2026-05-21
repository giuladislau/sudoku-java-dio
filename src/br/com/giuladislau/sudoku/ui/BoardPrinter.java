package br.com.giuladislau.sudoku.ui;

import br.com.giuladislau.sudoku.model.Board;
import br.com.giuladislau.sudoku.model.Cell;

public class BoardPrinter {

    public void print(Board board) {

        System.out.println("\n=== SUDOKU ===\n");

        for (int row = 0; row < 9; row++) {

            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }

            for (int col = 0; col < 9; col++) {

                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }

                Cell cell = board.getCell(row, col);

                int value = cell.getValue();

                if (value == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(value + " ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }
}