package ca.cmpt276.as3.asteroidseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.cmpt276.as3.asteroidseeker.R;
import ca.cmpt276.as3.asteroidseeker.model.GameBoard;

/*
* Options Class allows us to clear the counter for the number of times the game was played or go to
* an activity that will allow you to change settings pertaining to a game
*/

public class Options extends AppCompatActivity {
    public GameBoard gameBoard;
    MediaPlayer space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setUpOptionsActivity();
    }

    public void setUpOptionsActivity(){
        gameBoard = GameBoard.getInstance();
        eraseBtn();
        chooseAsteroidsBtn();
        chooseBoardDimensionsBtn();
        setAsteroidHidden();

        space = MediaPlayer.create(Options.this, R.raw.sound);
        space.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        space.release();
    }

    public void setAsteroidHidden(){
        int chosenAsteroids = ChooseAsteroids.getNumAsteroidsToFind(this);
        gameBoard.setNumOfAsteroids(chosenAsteroids);
    }

    private void chooseAsteroidsBtn(){
        Button chooseAsteroidsBtn = findViewById(R.id.btnNumAsteroids);
        chooseAsteroidsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChooseAsteroids = ChooseAsteroids.makeIntent(Options.this);
                startActivity(goToChooseAsteroids);
            }
        });
    }

    private void chooseBoardDimensionsBtn(){
        Button chooseDimensions = findViewById(R.id.btnBoardSize);
        chooseDimensions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChooseBoardSize = ChooseBoardSize.makeIntent(Options.this);
                startActivity(goToChooseBoardSize);
            }
        });
    }

    private void eraseBtn() {
        Button btnEraseNumPlayed = findViewById(R.id.erasebtn);
        btnEraseNumPlayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoard.removeNumPlayed();
                Toast.makeText(Options.this, "Number of times played set to 0",Toast.LENGTH_SHORT).show();
            }
        });
    }
}