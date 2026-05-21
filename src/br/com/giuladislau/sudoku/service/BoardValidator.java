package br.com.giuladislau.sudoku.service;

import br.com.giuladislau.sudoku.model.Board;

public class BoardValidator {

    public boolean isValidMove(Board board, int row, int col, int value) {

        return isRowValid(board, row, value)
                && isColumnValid(board, col, value)
                && isBlockValid(board, row, col, value);
    }

    private boolean isRowValid(Board board, int row, int value) {

        for (int col = 0; col < 9; col++) {

            if (board.getCell(row, col).getValue() == value) {
                return false;
            }
        }

        return true;
    }

    private boolean isColumnValid(Board board, int col, int value) {

        for (int row = 0; row < 9; row++) {

            if (board.getCell(row, col).getValue() == value) {
                return false;
            }
        }

        return true;
    }

    private boolean isBlockValid(Board board, int row, int col, int value) {

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int currentRow = startRow; currentRow < startRow + 3; currentRow++) {

            for (int currentCol = startCol; currentCol < startCol + 3; currentCol++) {

                if (board.getCell(currentRow, currentCol).getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isBoardComplete(Board board) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                int value = board.getCell(row, col).getValue();

                if (value == 0) {
                    return false;
                }

                board.getCell(row, col).setValue(0);

                boolean valid = isValidMove(board, row, col, value);

                board.getCell(row, col).setValue(value);

                if (!valid) {
                    return false;
                }
            }
        }

        return true;
    }
}