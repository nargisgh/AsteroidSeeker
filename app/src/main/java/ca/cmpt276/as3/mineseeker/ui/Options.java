package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.ChooseBoardSize;
import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

public class Options extends AppCompatActivity {
    public GameBoard gameBoard;
    MediaPlayer space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        gameBoard = GameBoard.getInstance();
        eraseBtn();
        chooseAsteroidsBtn();
        chooseBoardDimensionsBtn();

        space = MediaPlayer.create(Options.this, R.raw.sound);
        space.start();

        // Spinner citation: https://developer.android.com/guide/topics/ui/controls/spinner
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
                Toast.makeText(Options.this, "Number of times played set to 0!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}