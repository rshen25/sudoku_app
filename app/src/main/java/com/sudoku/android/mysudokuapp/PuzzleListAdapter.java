package com.sudoku.android.mysudokuapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PuzzleListAdapter extends RecyclerView.Adapter<PuzzleListAdapter.PuzzleListHolder> {

    private static final String TAG = "PuzzleListAdapter";
    private static final String EXTRA_PUZZLE = "EXTRA_PUZZLE";

    private ArrayList<String> mPuzzleDataset;
    private Context mContext;

    /**
     * BEGIN - RecyclerView Holder
     * provides a function to get the position of the
     */
    public static class PuzzleListHolder extends RecyclerView.ViewHolder {
        public TextView puzzleIndex;
        public ConstraintLayout constraintLayout;
        public PuzzleListHolder(@NonNull View itemView) {
            super(itemView);
            puzzleIndex = itemView.findViewById(R.id.tv_puzzle_index);
            constraintLayout = itemView.findViewById(R.id.puzzle_select_layout);
        }
    }
    /**
     * END - https://github.com/android/views-widgets-samples/blob/master/RecyclerView/Application/src/main/java/com/example/android/recyclerview/CustomAdapter.java
     * https://github.com/android/views-widgets-samples/blob/master/RecyclerView/Application/src/main/java/com/example/android/recyclerview/RecyclerViewFragment.java
     */

    public PuzzleListAdapter(Context context, ArrayList<String> dataSet) {
        mPuzzleDataset = dataSet;
        mContext = context;
    }

    @NonNull
    @Override
    public PuzzleListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.puzzle_select_text_view, viewGroup, false);
        PuzzleListHolder puzzleListHolder = new PuzzleListHolder(view);
        return puzzleListHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PuzzleListHolder puzzleListHolder, final int i) {
        puzzleListHolder.puzzleIndex.setText("Puzzle " + i);

        puzzleListHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), SudokuPuzzle.class);
                intent.putExtra(EXTRA_PUZZLE, mPuzzleDataset.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPuzzleDataset.size();
    }

}
