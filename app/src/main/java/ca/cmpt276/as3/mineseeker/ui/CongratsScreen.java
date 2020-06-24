package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

import static ca.cmpt276.as3.mineseeker.ui.GamePlay.UI_GAME_PLAY_ACTIVITY_SCANS_USED;

public class CongratsScreen extends AppCompatActivity {
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
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
    }
}