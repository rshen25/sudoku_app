package com.sudoku.android.mysudokuapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Read the puzzle list file and stores them in a list
public class PuzzleReader {

    private int difficulty;
    private Context mContext;

    public PuzzleReader(Context context) {
        mContext = context;
    }

    // TODO: add constructor for difficulty selection (i.e. load in file based on difficulty)

    public ArrayList<String> getPuzzleList() {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader( new InputStreamReader(mContext.getAssets().open("puzzles.txt")));
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
