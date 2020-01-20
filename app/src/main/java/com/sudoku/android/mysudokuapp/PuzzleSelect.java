package com.sudoku.android.mysudokuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

        // Get Extras
        int difficulty;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            difficulty = extras.getInt("EXTRA_DIFFICULTY");
            // Get puzzle list
            puzzleReader = new PuzzleReader(this, difficulty);
            mPuzzles = puzzleReader.getPuzzleList();
        }
        else {
            Toast.makeText(this, "Error occurred, please select another puzzle.", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Get recyclerView and set up the recyclerView to display the Sudoku Puzzles
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
