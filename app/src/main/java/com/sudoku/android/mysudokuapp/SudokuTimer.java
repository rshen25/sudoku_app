package com.sudoku.android.mysudokuapp;

import android.widget.Chronometer;
import android.widget.TextView;

public class SudokuTimer {

    private boolean mResetTime = false;

    TextView mTimerView;
    Chronometer mChronometer;

    SudokuTimer(TextView view, Chronometer chrono) {
        mTimerView = view;
        mChronometer = chrono;
    }

    /**
     * Starts the timer to begin ticking
     */
    public void startTimer(){
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            public int seconds = 0;
            public int minutes = 0;

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                seconds++;

                if (mResetTime) {
                    seconds = 0;
                    minutes = 0;
                    mResetTime = false;
                }

                if (seconds >= 60) {
                    minutes++;
                }
                seconds = seconds % 60;
                mTimerView.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }
        });
        mChronometer.start();
    }

    /**
     * Resets the timer
     */
    public void resetTime() {
        mResetTime = true;
    }
}
