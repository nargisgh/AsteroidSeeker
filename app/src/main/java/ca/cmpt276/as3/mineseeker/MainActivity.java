package ca.cmpt276.as3.mineseeker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import ca.cmpt276.as3.mineseeker.ui.Menu;

public class MainActivity extends AppCompatActivity {
    Animation ranimation;
    ImageView myImage;
    Button skip;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = findViewById(R.id.anim);
        myImage.setImageResource(R.drawable.rocketship);
        rotateAnimation();

        skipBtn();
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
    }
}
