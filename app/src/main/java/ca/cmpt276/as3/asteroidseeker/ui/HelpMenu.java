package ca.cmpt276.as3.asteroidseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.cmpt276.as3.asteroidseeker.R;
/*
* This is the HelpMenuClass that displays Instruction on how to play the game, as well as citations
* for code and images and other things taken from or inspired from other places
*/

public class HelpMenu extends AppCompatActivity {
    Button okBtn;
    TextView helptxt;
    MediaPlayer space;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_menu);
        helptxt = findViewById(R.id.helptxt);
        helptxt.setMovementMethod(new ScrollingMovementMethod());
        space = MediaPlayer.create(HelpMenu.this, R.raw.sound);
        space.start();
        OkBtn();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        space.release();
    }

    private void OkBtn() {
        okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                space.release();
                finish();
            }
        });
    }
}