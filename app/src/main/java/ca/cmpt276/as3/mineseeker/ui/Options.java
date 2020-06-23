package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.R;
import ca.cmpt276.as3.mineseeker.model.GameBoard;

public class Options extends AppCompatActivity {
    Button eraseBtn;
    public GameBoard gameBoard;
    Spinner boardDropdown;
    Spinner asteroidDropdown;
    MediaPlayer space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        gameBoard= GameBoard.getInstance();
        eraseBtn();
        BoardDropdown();
        AsteroidDropdown();
        space = MediaPlayer.create(Options.this, R.raw.sound);
        space.start();
        // Spinner citation: https://developer.android.com/guide/topics/ui/controls/spinner


    }

    private void AsteroidDropdown() {
        asteroidDropdown = findViewById(R.id.asteroidOptions);
        String [] items = new String[]{"Select:","6","10","15","20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        asteroidDropdown.setAdapter(adapter);
    }

    private void BoardDropdown() {
        boardDropdown = findViewById(R.id.boardOptions);
        String [] items = new String[] {"Select:","4 x 6","5 x 10", "6 x 15"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        boardDropdown.setAdapter(adapter);
    }

    private void eraseBtn() {
        eraseBtn = findViewById(R.id.erasebtn);
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoard.removeNumPlayed();
                Toast.makeText(Options.this, "Number of times played set to 0!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}