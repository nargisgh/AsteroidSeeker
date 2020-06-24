package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

public class ChooseBoardSize extends AppCompatActivity {

    public static final String PREFERENCES = "App Preferences";
    public static final String CHOSEN_NUMBER_OF_ROWS = "chosen number of Rows";
    public static final String CHOSEN_NUMBER_OF_COLS = "chosen number of Cols";
    public GameBoard gameBoard = GameBoard.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_board_size);
        createBoardSizeRadioBtns();
        Toast.makeText(ChooseBoardSize.this, "Num of rows is: " + gameBoard.getNumBoardRows() +
                " Num of Cols is: " + gameBoard.getNumBoardColumns(), Toast.LENGTH_SHORT).show();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ChooseBoardSize.class);
    }

    private void createBoardSizeRadioBtns() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);

        int[] numRows = getResources().getIntArray(R.array.num_board_rows);
        int[] numCols = getResources().getIntArray(R.array.num_board_columns);

        //create buttons:
        for(int i = 0; i < numRows.length; i++){
            final int numberOfRows = numRows[i];
            final int numberOfCols = numCols[i];

            RadioButton boardSizeBtn = new RadioButton(this);
            final String currentDimensions = "" + numberOfRows + " rows by " + numberOfCols + " columns";
            boardSizeBtn.setText(currentDimensions);
            boardSizeBtn.setTextColor(Color.WHITE);
            boardSizeBtn.setTextSize(24);

            // on click callbacks
            boardSizeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ChooseBoardSize.this, "You Clicked me: " + currentDimensions, Toast.LENGTH_SHORT).show();

                    saveNumRowsAndCols(numberOfRows, numberOfCols);
                }
            });

            //add to radioGroup
            group.addView(boardSizeBtn);
            boolean isSelectedRow = (numberOfRows == getSavedRows(this));
            boolean isSelectedColumn = (numberOfCols == getSavedCols(this));

            if(isSelectedRow && isSelectedColumn){
                boardSizeBtn.setChecked(true);
            }
        }
    }

    private void saveNumRowsAndCols(int numRows, int numCols){
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CHOSEN_NUMBER_OF_ROWS, numRows);
        editor.putInt(CHOSEN_NUMBER_OF_COLS, numCols);
        editor.apply();
    }

    static public int getSavedRows(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        int defaultRows = context.getResources().getInteger(R.integer.default_num_rows);
        return preferences.getInt(CHOSEN_NUMBER_OF_ROWS, defaultRows);
    }

    static public int getSavedCols(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        int defaultCols = context.getResources().getInteger(R.integer.default_num_columns);
        return preferences.getInt(CHOSEN_NUMBER_OF_COLS, defaultCols);
    }


}