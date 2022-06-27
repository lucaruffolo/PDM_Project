package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Database db;

    private Animation logoAnim, textAnim, downtextAnim;
    private ImageView image;
    private TextView text, all, my, gym;
    public static CustomAdapter ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(this);


        setContentView(R.layout.activity_splash_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        logoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        textAnim = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        downtextAnim = AnimationUtils.loadAnimation(this,R.anim.downtext_animation);
        image = findViewById(R.id.logo);
        text = findViewById(R.id.textAmg);
        all = findViewById(R.id.textA);
        my = findViewById(R.id.textM);
        gym = findViewById(R.id.textG);
        image.setAnimation(logoAnim);
        text.setAnimation(textAnim);
        all.setAnimation(downtextAnim);
        my.setAnimation(downtextAnim);
        gym.setAnimation(downtextAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        },2000); //2000
    }



}
