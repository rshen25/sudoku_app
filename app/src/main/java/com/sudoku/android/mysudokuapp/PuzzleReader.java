package com.sudoku.android.mysudokuapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


// Read the puzzle list file and stores them in a list
public class PuzzleReader {

    private static final String TAG ="PuzzleReader";

    private int mDifficulty;
    private Context mContext;

    public PuzzleReader(Context context, int difficulty) {
        mContext = context;
        mDifficulty = difficulty;
    }

    /**
     * Reads in the Sudoku puzzle file based on the difficulty the user has selected and returns the file as an array of seeds
     * @return An array list of Sudoku puzzle seeds taken from a puzzle data file, each consisting of 162 characters representing a Sudoku puzzle
     */
    public ArrayList<String> getPuzzleList() {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            switch (mDifficulty) {
                case 0:
                    reader = new BufferedReader( new InputStreamReader(mContext.getAssets().open("puzzles_easy.txt")));
                    break;
                case 1:
                    reader = new BufferedReader( new InputStreamReader(mContext.getAssets().open("puzzles_medium.txt")));
                    break;
                case 2:
                    reader = new BufferedReader( new InputStreamReader(mContext.getAssets().open("puzzles_hard.txt")));
                default:
                    Log.d(TAG, "ERROR: No Difficulty Selected.");
            }
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                list.add(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }




}
