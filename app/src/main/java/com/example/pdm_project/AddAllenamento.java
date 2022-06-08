package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                int duraAll;
                try{
                    duraAll = Integer.valueOf(durataAllenamento.getText().toString());
                } catch (NumberFormatException e){
                    duraAll = 0;
                }

                if(!data.getText().toString().isEmpty() && !nomeAllenamento.getText().toString().isEmpty() && !esercizio.getText().toString().isEmpty()){
                    try {
                        MainActivity.db.addAllenamento(data.getText().toString().trim(),
                                nomeAllenamento.getText().toString().trim(),
                                esercizio.getText().toString().trim(),
                                kgriposo.getText().toString().trim(),
                                duraAll);
                        finish();


                    } catch (NumberFormatException e) {
                        Toast.makeText(AddAllenamento.this, "ERRORE NEL COMPILARE I CAMPI", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(AddAllenamento.this, "ERRORE NEL COMPILARE I CAMPI", Toast.LENGTH_SHORT).show();
                }
        });
    }
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}