package ca.cmpt276.as3.asteroidseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.asteroidseeker.R;
/*
* This is a ChooseAsteroids class that makes gives us a selection of asteroids numbers to hide in our
* GameBoard instance and allows data pertaining to the class to be passed to other activities
*/
public class ChooseAsteroids extends AppCompatActivity {

    public static final String NUM_ASTEROIDS_TO_FIND = "The Number of Asteroids to find";
    public static final String APP_PREFERENCES = "App Preferences";
    public static final int TEXT_SIZE = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_asteroids);

        createAsteroidsToFindButtons();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, ChooseAsteroids.class);
    }

    // From Dr. Frasers Video https://www.youtube.com/watch?v=m_ZiP0U_zRA&feature=youtu.be
    private void createAsteroidsToFindButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);

        int[] numAsteroids = getResources().getIntArray(R.array.num_asteroids_to_find);

        //create buttons
        for(int i = 0; i < numAsteroids.length; i++){
            final int numAsteroid = numAsteroids[i];

            RadioButton numAsteroidsButton = new RadioButton(this);
            numAsteroidsButton.setText(getString(R.string.asteroids_tofind, numAsteroid));
            setNumAsteroidBtnAppearance(numAsteroidsButton);

            //set callbacks
            numAsteroidsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

    private void setNumAsteroidBtnAppearance(Button numAsteroidsButton){
        numAsteroidsButton.setTextColor(Color.WHITE);
        numAsteroidsButton.setTextSize(TEXT_SIZE);
    }

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