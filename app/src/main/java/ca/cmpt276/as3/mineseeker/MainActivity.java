package ca.cmpt276.as3.mineseeker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import ca.cmpt276.as3.mineseeker.ui.Menu;

public class MainActivity extends AppCompatActivity {
    Animation ranimation;
    ImageView myImage;
    ImageView astImage;
    Button skip;
    private static int SPLASH_TIME = 6000;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = findViewById(R.id.anim);
        myImage.setImageResource(R.drawable.rocketship);
        fade();
        rotateAnimation();
        skipBtn();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this, Menu.class);
                startActivity(intent);

            }
        },SPLASH_TIME);

    }

    private void fade() {
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
                startActivity(intent);
            }
        });
    }

    private void rotateAnimation() {
        ranimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        myImage.startAnimation(ranimation);
        //https://www.youtube.com/watch?v=goVoYf2qie0
        //For rounding edges of an image:
        // https://www.youtube.com/watch?v=oeQyEtY6a-M
    }
}
