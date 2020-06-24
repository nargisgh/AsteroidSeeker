package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.BoardSquare;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

public class GamePlay extends AppCompatActivity {
    public GameBoard gameboard = GameBoard.getInstance();
    private int numOfRows = gameboard.getNumBoardRows();
    private int numOfCols = gameboard.getNumBoardColumns();
    private int numOfScans = INITIAL_SCAN_COUNT;
    public static final String UI_GAME_PLAY_ACTIVITY_ASTEROIDS_FOUND = ".ui.GamePlayActivity - asteroidsFound";
    public static final String UI_GAME_PLAY_ACTIVITY_SCANS_USED = ".ui.GamePlayActivity - scansUsed";
    public static final int INITIAL_SCAN_COUNT = 0;
    Button[][] buttons = new Button[numOfRows][numOfCols];
    private int asteroidsLeft;
    private int asteroidsFound;


    public static int NumPlayed = 0;
    TextView timesPlayedtxt;
    MediaPlayer space;
    private int[][] asteroidChecker = new int[numOfRows][numOfCols];
    private int[][] scanChecker = new int[numOfRows][numOfCols];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        space = MediaPlayer.create(GamePlay.this, R.raw.sound);
        space.start();
        //NumPlayed++;
        setUpGamePlay();
        Toast.makeText(GamePlay.this, "Num of rows is: " + gameboard.getNumBoardRows() +
                " Num of Cols is: " + gameboard.getNumBoardColumns(), Toast.LENGTH_SHORT).show();

        //NumPlayed++;

        //HideAsteroid();
        //UpdateScan();
        NumPlayed();
    }

    private void setUpGamePlay(){
        gameboard.addNumPlayed();
        setUpDependentValues();
        gameboard.presets(findNumOfAsteroids(), getNumRows(), getNumCols());
        refreshFoundTxt();
        initializeCheckers();
        populateButtons();
    }

    public int findNumOfAsteroids(){
        int chosenAsteroids = ChooseAsteroids.getNumAsteroidsToFind(this);
        return chosenAsteroids;
    }

    public int getNumRows(){
        int chosenRows = ChooseBoardSize.getSavedRows(this);
        return chosenRows;
    }

    public int getNumCols(){
        int chosenCols = ChooseBoardSize.getSavedCols(this);
        return chosenCols;
    }

    public void setAsteroidsFound(int asteroidsFound) {
        this.asteroidsFound = asteroidsFound;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void setNumOfCols(int numOfCols) {
        this.numOfCols = numOfCols;
    }

    public void setAsteroidsLeft(int asteroidsLeft) {
        this.asteroidsLeft = asteroidsLeft;
    }

    public void setNumOfScans(int numOfScans) {
        this.numOfScans = numOfScans;
    }


    private void setUpDependentValues() {
        setAsteroidsFound(INITIAL_SCAN_COUNT);
        setAsteroidsLeft(gameboard.getNumOfAsteroids());
        setNumOfScans(INITIAL_SCAN_COUNT);
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
        BoardSquare boardSquare = gameboard.getSpecificSquare(row, col);
        if(asteroidChecker[row][col] == 0 && boardSquare.getIsAsteroid()){
            incrementFoundText(row, col);

        }
        if(scanChecker[row][col] == 0){
            incrementScansText(row, col);
        }
    }

    private void initializeCheckers(){
        for(int row = 0; row < numOfRows; row++){
            for (int col = 0; col < numOfCols; col++){
                asteroidChecker[row][col] = 0;
                scanChecker[row][col] = 0;
            }
        }
    }

    private void refreshFoundTxt(){
        TextView asteroidCount = findViewById(R.id.foundtxt);
        //int numAsteroids = ChooseAsteroids.getNumAsteroidsToFind(this);
        asteroidCount.setText("Found " + asteroidsFound + " Asteroids, " + asteroidsLeft + " Remain");
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

        for (int row = 0; row < numOfRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < numOfCols; col++){
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
                        gameOver();
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
        Toast.makeText(GamePlay.this, "Num of Asteroids is: " + gameboard.getNumOfAsteroids(), Toast.LENGTH_SHORT).show();
    }

    private void gameOver(){
        if(asteroidsFound == gameboard.getNumOfAsteroids()){
            Intent CongratsScreen = passDataToCongratsScreen(GamePlay.this, asteroidsFound, numOfScans);
            startActivity(CongratsScreen);
            finish();
        }
    }

    public static Intent passDataToCongratsScreen(Context context, int asteroidsFound, int scansUsed){
        Intent gameInfo = new Intent(context, CongratsScreen.class);
        gameInfo.putExtra(UI_GAME_PLAY_ACTIVITY_ASTEROIDS_FOUND, asteroidsFound);
        gameInfo.putExtra(UI_GAME_PLAY_ACTIVITY_SCANS_USED, scansUsed);
        return gameInfo;
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
        changeButtonPicture(button, row, col, newWidth, newHeight);

    }

    public void changeButtonPicture(Button button, int row, int col, int newWidth, int newHeight){
        if (isAsteroidHidden(row, col)){
            populateAsteroidButton(button, newWidth, newHeight);
            showNearbyAsteroids(button, row, col);
        }
        else{
            showNearbyAsteroids(button, row, col);
        }
    }

    private boolean isAsteroidHidden(int row, int col){
        BoardSquare currentSquare = gameboard.getSpecificSquare(row, col);
        return currentSquare.getIsAsteroid();

    }

    private void populateAsteroidButton(Button button, int newWidth, int newHeight){
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.asteroid);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        lockButtonSizes();

    }

    public void showNearbyAsteroids(Button button, int row, int col){
        BoardSquare currentSquare = gameboard.getSpecificSquare(row, col);
        int nearbyAsteroids = currentSquare.getAsteroidsNearby();
        button.setText("" + nearbyAsteroids);
    }

    private void lockButtonSizes() {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col++) {
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