package ca.cmpt276.as3.mineseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.ui.ChooseAsteroids;
import ca.cmpt276.as3.mineseeker.ui.Options;

public class ChooseBoardSize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_board_size);
        createBoardSizeRadioBtns();
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
                }
            });

            //add to radioGroup
            group.addView(boardSizeBtn);
        }
    }


}