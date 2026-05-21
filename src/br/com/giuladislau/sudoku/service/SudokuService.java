package br.com.giuladislau.sudoku.service;

import br.com.giuladislau.sudoku.model.Board;
import br.com.giuladislau.sudoku.model.Cell;
import br.com.giuladislau.sudoku.model.GameStatus;

public class SudokuService {

    private final Board board;
    private final BoardValidator validator;

    public SudokuService(Board board) {
        this.board = board;
        this.validator = new BoardValidator();
    }

    public Board getBoard() {
        return board;
    }

    public boolean insertNumber(int row, int col, int value) {

        if (!isValidPosition(row, col)) {
            System.out.println("Posição inválida.");
            return false;
        }

        if (value < 1 || value > 9) {
            System.out.println("Valor inválido.");
            return false;
        }

        Cell cell = board.getCell(row, col);

        if (cell.isFixed()) {
            System.out.println("Não é possível alterar uma posição fixa.");
            return false;
        }

        boolean validMove = validator.isValidMove(
                board,
                row,
                col,
                value
        );

        if (!validMove) {
            System.out.println("Movimento inválido.");
            return false;
        }

        cell.setValue(value);

        return true;
    }

    public boolean removeNumber(int row, int col) {

        if (!isValidPosition(row, col)) {
            System.out.println("Posição inválida.");
            return false;
        }

        Cell cell = board.getCell(row, col);

        if (cell.isFixed()) {
            System.out.println("Não é possível remover um número fixo.");
            return false;
        }

        if (cell.getValue() == 0) {
            System.out.println("A posição já está vazia.");
            return false;
        }

        cell.setValue(0);

        return true;
    }

    public GameStatus getGameStatus() {

        boolean hasEmptyCells = false;

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                int value = board
                        .getCell(row, col)
                        .getValue();

                if (value == 0) {
                    hasEmptyCells = true;
                }
            }
        }

        if (hasEmptyCells) {

            boolean started = hasGameStarted();

            if (!started) {
                return GameStatus.NOT_STARTED;
            }

            return GameStatus.INCOMPLETE;
        }

        if (validator.isBoardComplete(board)) {
            return GameStatus.COMPLETE;
        }

        return GameStatus.INCOMPLETE;
    }

    private boolean hasGameStarted() {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                Cell cell = board.getCell(row, col);

                if (!cell.isFixed()
                        && cell.getValue() != 0) {

                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidPosition(int row, int col) {

        return row >= 0
                && row < 9
                && col >= 0
                && col < 9;
    }
}