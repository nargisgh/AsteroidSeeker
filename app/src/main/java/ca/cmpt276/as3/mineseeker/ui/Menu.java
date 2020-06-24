package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

public class Menu extends AppCompatActivity {
    Button btnHelp;
    Button btnPlay;
    Button btnOptions;
    GameBoard gameBoard = GameBoard.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BtnHelp();
        BtnOptions();
        BtnGamePlay();
        setAsteroid();
        setDimensions();
    }

    public void setAsteroid(){
        int chosenAsteroids = ChooseAsteroids.getNumAsteroidsToFind(this);
        gameBoard.setNumOfAsteroids(chosenAsteroids);
    }

    public void setDimensions(){
        int rowLength = ChooseBoardSize.getSavedRows(this);
        int colsLength = ChooseBoardSize.getSavedCols(this);
        gameBoard.setNumBoardColumns(colsLength);
        gameBoard.setNumBoardRows(rowLength);
        Toast.makeText(Menu.this, "Num of rows is: " + gameBoard.getNumBoardRows() +
                " Num of Cols is: " + gameBoard.getNumBoardColumns(), Toast.LENGTH_SHORT).show();
    }

    private void BtnGamePlay() {
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, GamePlay.class);
                startActivity(intent);
            }
        });
    }

    private void BtnOptions() {
        btnOptions = findViewById(R.id.btnOptions);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Options.class);
                startActivity(intent);
            }
        });
    }

    private void BtnHelp() {
        btnHelp = findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Menu.this, HelpMenu.class);
                startActivity(intent);
            }
        });
    }
}