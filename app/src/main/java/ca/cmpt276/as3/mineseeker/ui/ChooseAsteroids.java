package ca.cmpt276.as3.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.mineseeker.R;

public class ChooseAsteroids extends AppCompatActivity {

    public static final String NUM_ASTEROIDS_TO_FIND = "Num Asteroids";
    public static final String APP_PREFERENCES = "AppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_asteroids);

        createAsteroidsToFindButtons();

        int savedValue = getNumAsteroidsToFind(this);
        Toast.makeText(this, "Saved Value: " + savedValue, Toast.LENGTH_SHORT).show();

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
            numAsteroidsButton.setTextSize(24);

            //set callbacks
            numAsteroidsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ChooseAsteroids.this, "You Clicked me " + numAsteroid, Toast.LENGTH_SHORT).show();
                    //new
                    saveNumAsteroidsToFind(numAsteroid);
                }
            });

            //add to radioGroup
            group.addView(numAsteroidsButton);

            if(numAsteroid == getNumAsteroidsToFind(this)){
                numAsteroidsButton.setChecked(true);
            }

        }
    }

    //new
    private void saveNumAsteroidsToFind(int numAsteroids) {
        SharedPreferences preferences = this.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(NUM_ASTEROIDS_TO_FIND, numAsteroids);
        editor.apply();
    }

    static public int getNumAsteroidsToFind(Context context){
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        int defaultAsteroids = context.getResources().getInteger(R.integer.default_num_asteroids_chosen);
        return preferences.getInt(NUM_ASTEROIDS_TO_FIND, defaultAsteroids);
    }

}