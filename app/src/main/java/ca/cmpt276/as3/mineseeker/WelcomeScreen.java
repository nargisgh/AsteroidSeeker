package ca.cmpt276.as3.mineseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeScreen extends AppCompatActivity {
    Animation ranimation;
    ImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        myImage = findViewById(R.id.anim);
        myImage.setImageResource(R.drawable.asteroidfalling);

        rotateAnimation();

    }

    private void rotateAnimation() {
        ranimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        myImage.startAnimation(ranimation);
        //https://www.youtube.com/watch?v=goVoYf2qie0
    }
}