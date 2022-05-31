package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAllenamento extends AppCompatActivity {

    private EditText data, nomeAllenamento, esercizio, kgriposo, durataAllenamento;
    private Button btn_addAllenamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_allenamento);

        data = findViewById(R.id.input_Data);
        nomeAllenamento = findViewById(R.id.input_NomeAllenamento);
        esercizio = findViewById(R.id.input_Esercizio);
        kgriposo = findViewById(R.id.input_KgRiposo);
        durataAllenamento = findViewById(R.id.input_DurataAllenamento);

        btn_addAllenamento = findViewById(R.id.btn_AddAllenamento);
        btn_addAllenamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(AddAllenamento.this);
                db.addAllenamento(data.getText().toString().trim(),
                        nomeAllenamento.getText().toString().trim(),
                        esercizio.getText().toString().trim(),
                        kgriposo.getText().toString().trim(),
                        Integer.valueOf(durataAllenamento.getText().toString().trim()));
            }
        });
    }
}