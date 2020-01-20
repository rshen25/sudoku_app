package com.sudoku.android.mysudokuapp;

/**
 * Class to hold Sudoku board states for the purpose of undoing actions
 */
public class UndoState {
    public char[] undoState;
    public boolean isEmpty;

    public UndoState() {
        undoState = new char[0];
        isEmpty = true;
    }

    public UndoState(char[] state) {
        undoState = state;
        isEmpty = false;
    }
}
