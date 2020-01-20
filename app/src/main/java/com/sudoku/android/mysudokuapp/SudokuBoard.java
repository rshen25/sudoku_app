package com.sudoku.android.mysudokuapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;

public class SudokuBoard {

    private static final String TAG = "SudokuBoard";

    private static final int PUZZLE_SIZE = 81;
    private static final int ROW_AND_COL_SIZE = 9;

    private Button[] mButtonRow = new Button[ROW_AND_COL_SIZE];
    private Button[] mButtonCol = new Button[ROW_AND_COL_SIZE];
    private Button[][] mSudokuBoard;

    private char[] mPuzzle;
    private char[] mSolution;
    private char[] mCurrentState;

    private SudokuPuzzleUndo mSudokuPuzzleUndo;

    private Context mContext;

    public SudokuBoard(Context context, Button[][] board, String puzzle) {
        mContext = context;
        mSudokuBoard = board;
        splitPuzzle(puzzle);
        Log.d(TAG, new String(mCurrentState));
        mSudokuPuzzleUndo = new SudokuPuzzleUndo(mCurrentState);
    }

    // Displays the Sudoku Puzzle onto the board
    // assigns each button to be the corresponding number for the puzzle
    public void displayPuzzleOnBoard() {
        int x;
        int y;
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            x = i % ROW_AND_COL_SIZE;
            y = i / ROW_AND_COL_SIZE;
            if (mPuzzle[i] != '.') {
                //Log.d(TAG, "X: " + x + " Y: " + y);
                mSudokuBoard[x][y].setText("" + mPuzzle[i]);
                mSudokuBoard[x][y].setClickable(false);
                mSudokuBoard[x][y].setBackground(ContextCompat.getDrawable(mContext, R.drawable.sudoku_board_unclickable_border));
            }
            else {
                mSudokuBoard[x][y].setText("");
            }
        }
    }

    // Splits the full puzzle string into the solution and the puzzle, assigns it to the member variables
    private void splitPuzzle(String puzzle) {
        mSolution = puzzle.substring(0, PUZZLE_SIZE).toCharArray();
        mPuzzle = puzzle.substring(PUZZLE_SIZE).toCharArray();
        mCurrentState = puzzle.substring(PUZZLE_SIZE).toCharArray();
    }

    // Highlights the column of the selected sudoku button
    public void highlightRowAndCol(int row, int col, int num) {
        // reset previous highlighted column and row
        if (mButtonCol[0] != null && mButtonRow[0] != null) {
            for (int i = 0; i < mButtonCol.length; i++) {
                mButtonCol[i].setAlpha(1.0f);
                mButtonRow[i].setAlpha(1.0f);
            }
        }

        // get each button in the column and highlight it
        // add the buttons to the column variable
        for (int i = 0; i < ROW_AND_COL_SIZE; i++){
            // Check column for validity
            if (checkForValidCell(mSudokuBoard[i][col], Integer.toString(num))){
                mSudokuBoard[i][col].setTextColor(Color.RED);

            }
            else {
                mSudokuBoard[i][col].setTextColor(Color.BLACK);
            }
            // Check row for validity
            if (checkForValidCell(mSudokuBoard[row][i], Integer.toString(num))){
                mSudokuBoard[row][i].setTextColor(Color.RED);
            }
            else {
                mSudokuBoard[row][i].setTextColor(Color.BLACK);
            }
            // Highlight the row and column
            mButtonCol[i] = mSudokuBoard[i][col];
            mSudokuBoard[i][col].setAlpha(0.65f);
            mButtonRow[i] = mSudokuBoard[row][i];
            mSudokuBoard[row][i].setAlpha(0.65f);
        }
    }

    // Checks if the current text of the button matches the current number being set to the button,
    // it is used to check for any duplicate numbers
    private boolean checkForValidCell(Button cell, String num) {
        return num == cell.getText();
    }

    // Checks if the current state of the Sudoku board is correct
    public boolean checkForValidSolution() {
        if (mSolution.equals(mCurrentState)){
            return true;
        }
        return false;
    }

    // Resets the Sudoku Board to its original state
    public void resetPuzzle(){
        displayPuzzleOnBoard();
        mSudokuPuzzleUndo.resetUndoStates();
    }

    public void solvedPuzzle() {
        for (int i = 0; i < ROW_AND_COL_SIZE; i++) {
            for (int j = 0; j < ROW_AND_COL_SIZE; j++) {
                mSudokuBoard[i][j].setClickable(false);
            }
        }
        //mSudokuPuzzleUndo.resetList();
    }

    public void addNumbertoState(int row, int col, char num) { // char num, int num
        int index = row * 9 + col;
        mCurrentState[index] = num;
        String test = new String(mCurrentState);
        Log.d(TAG, "Add: " + test);

        // addStateToUndoList(mCurrentState);
        // mSudokuPuzzleUndo.addUndoAction(row, col, num);
        mSudokuPuzzleUndo.addUndoState(mCurrentState);
    }

    public Button[][] getSudokuBoard() {
        return mSudokuBoard;
    }

    public void undoAction() {
        char[] undoState = mSudokuPuzzleUndo.getUndoState();
        if (undoState != null) {
            // Sets the current Sudoku Board state to the undo state
            mCurrentState = undoState;
            updateSudokuBoard();
        }
        else {
            return;
        }
    }

    private void updateSudokuBoard() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            x = i % ROW_AND_COL_SIZE;
            y = i / ROW_AND_COL_SIZE;
            // mSudokuBoard[x][y].setText("" + mCurrentState[i]);
            if (mCurrentState[i] != '.') {
                //Log.d(TAG, "X: " + x + " Y: " + y);
                mSudokuBoard[x][y].setText("" + mCurrentState[i]);
            } else {
                mSudokuBoard[x][y].setText("");
            }
        }
    }
}
