package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

public class GamePlay extends AppCompatActivity {
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 5;
    private static final int SET_VALUE = 20;
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];
    private int numOfAsteroids = SET_VALUE;
    private int asteroidsFound = 0;
    private int asteroidsLeft = numOfAsteroids;
    private int numOfScans = 0;

    public static int NumPlayed = 0;
    TextView timesPlayedtxt;
    private GameBoard gameboard;
    private int[][] asteroidChecker = new int[NUM_ROWS][NUM_COLS];
    private int[][] scanChecker = new int[NUM_ROWS][NUM_COLS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        NumPlayed++;
        setUpGameboard();
        //gameboard = new GameBoard();
        //gameboard.addNumPlayed();
        //gameboard.presets();
        initializeCheckers();
        populateButtons();
        //HideAsteroid();
        //UpdateScan();
        NumPlayed();

    }

    private void setUpGameboard(){
        gameboard = new GameBoard();
        gameboard.addNumPlayed();
        gameboard.presets();
    }

    private void NumPlayed() {
        timesPlayedtxt = findViewById(R.id.timesPlayedtxt);
        timesPlayedtxt.setText("Times Played: " + gameboard.getNumPlayed());
    }


    private void HideAsteroid() {
        //hide the number of asteroids chosen
        //update the textview of found = 0/ number of asteroids when start game
    }

    private void updateGameTextViews(int row, int col){
        if(asteroidChecker[row][col] == 0){
            incrementFoundText(row, col);
        }
        if(scanChecker[row][col] == 0){
            incrementScansText(row, col);
        }
    }

    private void initializeCheckers(){
        for(int row = 0; row < NUM_ROWS; row++){
            for (int col = 0; col < NUM_COLS; col++){
                asteroidChecker[row][col] = 0;
                scanChecker[row][col] = 0;
            }
        }
    }

    private void incrementFoundText(int row, int col){
        asteroidsFound++;
        asteroidsLeft--;
        TextView asteroidCount = findViewById(R.id.foundtxt);
        asteroidCount.setText("Found " + asteroidsFound + " Asteroids, " + asteroidsLeft + " Remain");
        asteroidChecker[row][col] = 1;
    }

    private void incrementScansText(int row, int col){
        numOfScans++;
        TextView scanCount = findViewById(R.id.scanstxt);
        scanCount.setText("Scans Taken: " + numOfScans);
        scanChecker[row][col] = 1;
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // Make text not clip on small buttons
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                        updateGameTextViews(FINAL_ROW, FINAL_COL);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        Button button = buttons[row][col];

        // Lock Button Sizes:
        lockButtonSizes();

        // Does not scale image.
//    	button.setBackgroundResource(R.drawable.action_lock_pink);

        // Scale image to button: Only works in JellyBean!
        // Image from Crystal Clear icon set, under LGPL
        // http://commons.wikimedia.org/wiki/Crystal_Clear
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.asteroid);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));

        // Change text on button:
        button.setText("" + col);

    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
}
//Dr: Brian Fraser's amazing video
// https://www.youtube.com/watch?v=4MFzuP1F-xQ