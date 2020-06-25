package ca.cmpt276.as3.asteroidseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;


import ca.cmpt276.as3.asteroidseeker.R;
import ca.cmpt276.as3.asteroidseeker.model.GameBoard;
/*
* This is the Congratulations Screen Class which displays a message to congratulate the user after they
* complete the game, and shows them how many scans it took them to complete the game. When moved back from this
* screen the user goes back to the main menu
*/

public class CongratsScreen extends AppCompatActivity {
    public static final int X_PARAM = 0;
    public static final int Y_PARAM = -20;
    public GameBoard gameBoard;
    MediaPlayer congratulations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats_screen);
        displayGameInfo();
        congratulations = MediaPlayer.create(CongratsScreen.this, R.raw.congratulations);
        congratulations.start();
        //https://www.youtube.com/watch?v=eX-TdY6bLdg

    }


    @SuppressLint("SetTextI18n")
    private void displayGameInfo(){
        TextView gameInfo = findViewById(R.id.scanAndMinesTxtView);
        Intent fromGameplay = getIntent();
        int numOfScans = fromGameplay.getIntExtra(GamePlay.UI_GAME_PLAY_ACTIVITY_SCANS_USED, 1);
        int asteroidsFound = fromGameplay.getIntExtra(GamePlay.UI_GAME_PLAY_ACTIVITY_ASTEROIDS_FOUND, 10);
        gameInfo.setText("Asteroids Found: " + asteroidsFound + "   Scans Used: " + numOfScans);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.85),(int)(height*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = X_PARAM;
        params.y = Y_PARAM;
        getWindow().setAttributes(params);
    }
}