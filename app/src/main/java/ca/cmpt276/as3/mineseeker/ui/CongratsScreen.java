package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

import static ca.cmpt276.as3.mineseeker.ui.GamePlay.UI_GAME_PLAY_ACTIVITY_SCANS_USED;

public class CongratsScreen extends AppCompatActivity {
    public GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats_screen);
        displayGameInfo();
    }

    @SuppressLint("SetTextI18n")
    private void displayGameInfo(){
        TextView gameInfo = findViewById(R.id.scanAndMinesTxtView);
        Intent fromGameplay = getIntent();
        int numOfScans = fromGameplay.getIntExtra(GamePlay.UI_GAME_PLAY_ACTIVITY_SCANS_USED, 1);
        int asteroidsFound = fromGameplay.getIntExtra(GamePlay.UI_GAME_PLAY_ACTIVITY_ASTEROIDS_FOUND, 10);
        gameInfo.setText("Asteroids Found: " + asteroidsFound + "   Scans Used: " + numOfScans);
    }
}