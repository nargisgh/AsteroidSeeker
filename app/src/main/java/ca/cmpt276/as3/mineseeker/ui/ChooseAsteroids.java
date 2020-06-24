package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.R;

public class ChooseAsteroids extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_asteroids);
        createAsteroidsToFindButtons();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, ChooseAsteroids.class);
    }

    private void createAsteroidsToFindButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);

        int[] numAsteroids = getResources().getIntArray(R.array.num_asteroids_to_find);

        //create buttons:
        for(int i = 0; i < numAsteroids.length; i++){
            final int numAsteroid = numAsteroids[i];

            RadioButton numAsteroidsButton = new RadioButton(this);
            numAsteroidsButton.setText(getString(R.string.asteroids_tofind, numAsteroid));
            numAsteroidsButton.setTextColor(Color.WHITE);
            //numAsteroidsButton.setTextAppearance();
            numAsteroidsButton.setTextSize(24);

            //set callbacks
            numAsteroidsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ChooseAsteroids.this, "You Clicked me " + numAsteroid, Toast.LENGTH_SHORT).show();
                }
            });

            //add to radioGroup
            group.addView(numAsteroidsButton);
        }
    }
}