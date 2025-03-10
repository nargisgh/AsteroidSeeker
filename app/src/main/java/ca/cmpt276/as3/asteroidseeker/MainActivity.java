package ca.cmpt276.as3.asteroidseeker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import ca.cmpt276.as3.asteroidseeker.ui.Menu;
/*
* This is the MainActivity Class that displays the welcome screen and the Developers names
*/

public class MainActivity extends AppCompatActivity {
    Animation spinAnimation;
    ImageView myImage;
    ImageView astImage;
    Button skip;
    MediaPlayer space;
    private static int SPLASH_TIME = 6000;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = findViewById(R.id.anim);
        myImage.setImageResource(R.drawable.rocketship);
        space = MediaPlayer.create(MainActivity.this, R.raw.sound);
        space.start();
        fadeAnimation();
        rotateAnimation();
        skipBtn();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                space.release();
                startActivity(intent);

            }
        },SPLASH_TIME);
    }

    private void fadeAnimation() {
        astImage = findViewById(R.id.ast);
        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade);
        astImage.startAnimation(fade);
        //https://stackoverflow.com/questions/2597329/how-to-do-a-fadein-
        // of-an-image-on-an-android-activity-screen
    }

    private void skipBtn() {
        skip = findViewById(R.id.skipbtn);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, Menu.class);
                space.release();
                startActivity(intent);
            }
        });
    }

    private void rotateAnimation() {
        spinAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        myImage.startAnimation(spinAnimation);
        //https://www.youtube.com/watch?v=goVoYf2qie0
        //For rounding edges of an image:
        // https://www.youtube.com/watch?v=oeQyEtY6a-M
    }

    //Images: https://www.freepik.com/
    //https://depositphotos.com/vector-images/space-ship.html
    //Audio: https://freesound.org/people/melissapons/sounds/171588/
    //MediaPlayer citation : https://developer.android.com/guide/topics/media/mediaplayer
}
