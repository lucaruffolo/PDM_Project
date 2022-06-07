package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAllenamento extends AppCompatActivity {

    EditText nome_input, data_input, esercizi_input, durata_input,kgrip_input;
    Button btnEdit;
    String id,nome, data, esercizi, durata,kgrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_allenamento);
        nome_input = findViewById(R.id.input_NomeAllenamento2);
        data_input = findViewById(R.id.input_Data2);
        esercizi_input = findViewById(R.id.input_Esercizio2);
        kgrip_input = findViewById(R.id.input_KgRiposo2);
        durata_input = findViewById(R.id.input_DurataAllenamento2);
        btnEdit = findViewById(R.id.btn_updateAllenamento);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = nome_input.getText().toString().trim();
                data = data_input.getText().toString().trim();
                esercizi = esercizi_input.getText().toString().trim();
                kgrip = kgrip_input.getText().toString().trim();
                durata = durata_input.getText().toString().trim();
                MainActivity.db.updateData(id,nome,data,esercizi,durata,kgrip);


            }
        });
        getSetIntentData();
    }

    void getSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nome") &&getIntent().hasExtra("data") ){
            id = getIntent().getStringExtra("id");
            nome = getIntent().getStringExtra("nome");
            data = getIntent().getStringExtra("data");
            esercizi = getIntent().getStringExtra("esercizi");
            durata = getIntent().getStringExtra("durata");
            kgrip = getIntent().getStringExtra("kgrp");

            //set

            nome_input.setText(nome);
            data_input.setText(data);
            esercizi_input.setText(esercizi);
            durata_input.setText(durata);
            kgrip_input.setText(kgrip);
            //reload db on firstfragment

        }else{
            Toast.makeText(this, "Errore edit", Toast.LENGTH_SHORT).show();
        }
    }
}
