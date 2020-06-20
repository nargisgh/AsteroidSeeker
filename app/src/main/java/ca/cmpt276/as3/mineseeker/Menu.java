package ca.cmpt276.as3.mineseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button btnHelp;
    Button btnPlay;
    Button btnOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BtnHelp();
    }

    private void BtnHelp() {
        btnHelp = findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent =new Intent(Menu.this, HelpMenu.class);
                //startActivity(intent);
            }
        });
    }
}