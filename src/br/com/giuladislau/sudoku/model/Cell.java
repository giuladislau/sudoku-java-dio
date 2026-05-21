package br.com.giuladislau.sudoku.model;

public class Cell {

    private final int row;
    private final int col;
    private int value;
    private final boolean fixed;

    public Cell(int row, int col, int value, boolean fixed) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.fixed = fixed;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setValue(int value) {

        if (fixed) {
            System.out.println("Não é possível alterar um valor fixo.");
            return;
        }

        this.value = value;
    }
}