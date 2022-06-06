package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewAllenamento extends AppCompatActivity {

    private TextView nomeAllenamento;
    private TextView dataAllenamento;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nomeAllenamento = findViewById(R.id.textNomeAllenamento);
        dataAllenamento = findViewById(R.id.textDataAllenamento);
        ll = findViewById(R.id.viewAll_linearlayout);
    }
}