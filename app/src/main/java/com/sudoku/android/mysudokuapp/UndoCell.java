package com.sudoku.android.mysudokuapp;

// TODO: Delete
public class UndoCell {
    public int row;
    public int col;
    public char num;

    public UndoCell() {
        row = -1;
        col = -1;
        num = ' ';
    }

    public UndoCell(int row, int col, char num) {
        this.row = row;
        this.col = col;
        this.num = num;
    }

    public boolean isEmpty() {
        if (row <= -1) {
            return true;
        }
        return false;
    }
}
