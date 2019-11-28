package com.sudoku.android.mysudokuapp;

import android.util.Log;

public class SudokuPuzzleUndo {

    private static final String TAG = "SudokuPuzzleUndo";

    private static final int STATE_ARRAY_SIZE = 40;

    private UndoState[] mUndoStates;

    private int mIndex;


    SudokuPuzzleUndo(char[] state) {
        mUndoStates = new UndoState[STATE_ARRAY_SIZE];
        for (int i = 0; i < STATE_ARRAY_SIZE; i++) {
            mUndoStates[i] = new UndoState();
        }
        mUndoStates[0] = new UndoState(state.clone());
        mIndex = 0;
//        String test = new String(mUndoStates[0].undoState);
//        Log.d(TAG, test);
    }

    // Adds a state of the Sudoku board to the array
    public void addUndoState(char[] state) {
        incrementIndex();
        mUndoStates[mIndex] = new UndoState(state.clone());
//        String test = new String(mUndoStates[mIndex].undoState);
//        Log.d(TAG, "Added State: " + test);
    }

    // Returns a state of the Sudoku board and removes it
    // Mainly used for undoing an action
    public char[] getUndoState(){
        mUndoStates[mIndex] = new UndoState();
        decrementIndex();
        if (mUndoStates[mIndex].isEmpty) {
            return null;
        }

        UndoState state = mUndoStates[mIndex];
//        String test = new String(state.undoState);
//        Log.d(TAG, "State Returned: " + test);
        return state.undoState.clone();
    }

    public void resetUndoStates() {
        for (int i = 0; i < STATE_ARRAY_SIZE; i++) {
            mUndoStates[i] = new UndoState();
        }
        mIndex = 0;
    }

    private void incrementIndex() {
        mIndex++;
        mIndex = mIndex % (STATE_ARRAY_SIZE - 1);
//        Log.d(TAG, "Index: " + mIndex);
    }

    private void decrementIndex() {
        mIndex--;
        if (mIndex < 0) {
            mIndex += (STATE_ARRAY_SIZE - 1);
        }
        mIndex = mIndex % (STATE_ARRAY_SIZE - 1);
//        Log.d(TAG, "Index: " + mIndex);
    }
}
