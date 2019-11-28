package com.sudoku.android.mysudokuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PuzzleSelect extends AppCompatActivity {

    private static final String TAG = "PuzzleSelect";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mPuzzles = new ArrayList<>();
    private PuzzleReader puzzleReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_select);

        // TODO: temporary, should be moved to puzzle select to give the file name to look open
        // Get puzzle list
        puzzleReader = new PuzzleReader(this);
        mPuzzles = puzzleReader.getPuzzleList();

        // Get recyclerView
        recyclerView = findViewById(R.id.recyclerview_PuzzleList);
        recyclerView.setHasFixedSize(true);

        // Uses a default linear layout manager
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PuzzleListAdapter(this, mPuzzles);
        recyclerView.setAdapter(mAdapter);
    }
}
