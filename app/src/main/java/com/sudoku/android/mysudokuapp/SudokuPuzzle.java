package com.sudoku.android.mysudokuapp;


import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


/**
 * TODO: Potential features : when clicking on a number in the numpad, highlight all the same numbers on the board
 * Save states or some sort of saving
 * Notes functionality
 */


public class SudokuPuzzle extends AppCompatActivity implements SudokuPuzzleDialogFragment.SudokuPuzzleDialogListener{

    private static final String TAG = "SudokuPuzzle";

    private static final int ROW_AND_COL_SIZE = 9;
    private static final int NUM_ROWS = ROW_AND_COL_SIZE;
    private static final int NUM_COLS = ROW_AND_COL_SIZE;

    private int mCurrentNumber = 1;

    private SudokuBoard mSudokuBoard;
    private SudokuTimer mSudokuTimer;
    private TextView mTimerText;
    private Button mPrevNumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_puzzle);

        // Sets the number pad buttons' functionality
        setNumberButtons();

        Button[][] sudokuButtons = getSudokuButtons();

        // Get Puzzle Extra and split the extra into puzzle and solution
        String puzzleExtra;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            puzzleExtra = extras.getString("EXTRA_PUZZLE");
            if (puzzleExtra != null) {
                mSudokuBoard = new SudokuBoard(this, sudokuButtons, puzzleExtra);
            }
            else {
                Toast.makeText(this, "Error occurred, please select another puzzle.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        // Display the puzzle onto the Sudoku Board
        mSudokuBoard.displayPuzzleOnBoard();

        // Button btnNotes = findViewById(R.id.btn_sel_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sudoku_puzzle_menu, menu);

        // Initiate the timer
        mTimerText = (TextView) menu.findItem(R.id.menu_sudoku_timer).getActionView();

        mSudokuTimer = new SudokuTimer(mTimerText, (Chronometer) findViewById(R.id.chrono_sudoku));
        mSudokuTimer.startTimer();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_puzzle_undo:
                mSudokuBoard.undoAction();
                return super.onOptionsItemSelected(item);

             // Resets the puzzle
            case R.id.menu_puzzle_reset:
                showNoticeDialog();
                return super.onOptionsItemSelected(item);

            case R.id.menu_puzzle_options:
                Intent intent = new Intent(this, OptionsMenu.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Gets all the buttons for the Sudoku Board and puts in a 2d array
     * @return A 9x9 array of buttons representing the Sudoku board
     */
    private Button[][] getSudokuButtons() {
        Button[][] SudokuButtons = new Button[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < ROW_AND_COL_SIZE; row++) {
            for (int col = 0; col < ROW_AND_COL_SIZE; col++) {
                int id = getResources().getIdentifier("button_r" + row + "_c" + col, "id", getPackageName());
                SudokuButtons[row][col] = (Button) findViewById(id);

                final Button btn = SudokuButtons[row][col];
                final int x = col;
                final int y = row;
                // Highlight and set the text of the button to the current number
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int btnNum;

                        // Get the current number from the Sudoku cell
                        if (btn.getText() == null || btn.getText() == "") {
                            btnNum = 0;
                        }
                        else {
                            btnNum = Integer.parseInt(btn.getText().toString());
                        }

                        // Check if the current text is the same as the current number, if so set to empty string
                        // i.e. clears the cell
                        if (mCurrentNumber == btnNum) {
                            btn.setText("");
                            btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sudoku_board_default_border));
                            mSudokuBoard.addNumbertoState(x, y, '.');
                        }
                        //
                        else {
                            mSudokuBoard.highlightRowAndCol(y, x, mCurrentNumber);
                            btn.setText(Integer.toString(mCurrentNumber));
                            btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sudoku_board_filled_border));
                            mSudokuBoard.addNumbertoState(x, y, (char) (mCurrentNumber + '0'));
                        }
                        if (mSudokuBoard.checkForValidSolution()) {
                            solvedPuzzle();
                        }
                    }
                });
            }
        }
        return SudokuButtons;
    }

    /**
     * Sets the onClickListener of the number pad, sets the current number to be placed into the Sudoku
     * Board, to be the current button selected on the number pad
     */
    void setNumberButtons() {
        for (int i = 1; i < 10; ++i) {
            int id = getResources().getIdentifier("btn_sel_" + i, "id", getPackageName());
            final int num = i;
            final Button btn = findViewById(id);
            if (i == 1) {
                btn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.sudoku_board_filled_border));
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPrevNumButton != null) {
                        mPrevNumButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sudoku_board_unclickable_border));
                    }
                    mPrevNumButton = btn;
                    mCurrentNumber = num;
                    btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sudoku_board_filled_border));
                }
            });
        }
    }

    /**
     * When the puzzle is successfully solved, the puzzle is no longer clickable and a toast message appears
     */
    private void solvedPuzzle(){
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT);
        mSudokuBoard.solvedPuzzle();
    }

    /**
     * Show the reset puzzle confirmation dialog box
     */
    public void showNoticeDialog() {
        SudokuPuzzleDialogFragment dialogFragment = new SudokuPuzzleDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "SudokuPuzzleDialogFragment");
    }

    /**
     * Resets the Sudoku Board to its original state
     * @param dialog The dialog fragment to confirm whether to reset the puzzle
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mSudokuBoard.resetPuzzle();
        mSudokuTimer.resetTime();
    }

    // Cancels the reset action, nothing happens
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        return;
    }

}
