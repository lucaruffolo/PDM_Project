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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Database db;
    public static ArrayList<String> array_id;
    public static ArrayList<String> array_nomeAllenamento;
    public static ArrayList<String> array_dataAllenamento;
    public static ArrayList<String> array_esercizioAllenamento;
    public static ArrayList<String> array_kgRiposoAllenamento;
    public static ArrayList<String> array_DurataAllenamento;

    private Animation logoAnim, textAnim, downtextAnim;
    private ImageView image;
    private TextView text, all, my, gym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(this);
        array_id = new ArrayList<>();
        array_nomeAllenamento = new ArrayList<>();
        array_dataAllenamento = new ArrayList<>();
        //array_esercizioAllenamento = new ArrayList<>();
        //array_kgRiposoAllenamento = new ArrayList<>();
        //array_DurataAllenamento = new ArrayList<>();
        loadDataFromDB();


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

    public static void loadDataFromDB(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(MainActivity.this, "Database vuoto", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                array_id.add(cursor.getString(0));
                array_dataAllenamento.add(cursor.getString(1));
                array_nomeAllenamento.add(cursor.getString(2));

                //Toast.makeText(this, array_id.get(0), Toast.LENGTH_SHORT).show();
                //     array_esercizioAllenamento.add(cursor.getString(3));
            }
        }
    }
}
