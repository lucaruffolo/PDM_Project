package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddAllenamento extends AppCompatActivity {

    private EditText data, nomeAllenamento, esercizio, kgriposo, durataAllenamento;
    private Button btn_addAllenamento;
    private CheckBox cAddominali, cTrapezi, cCardio, cPettorali, cBicipiti, cTricipiti,
            cDorsali, cSpalle, cDeltoidi, cGambe, cCircuito;
    private String strAllenamento = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_allenamento);
        // load checkbox
        cAddominali = findViewById(R.id.cAddominali);
        cTrapezi = findViewById(R.id.cTrapezi);
        cCardio = findViewById(R.id.cCardio);
        cPettorali = findViewById(R.id.cPettorali);
        cBicipiti = findViewById(R.id.cBicipiti);
        cTricipiti = findViewById(R.id.cTricipiti);
        cDorsali = findViewById(R.id.cDorsali);
        cSpalle = findViewById(R.id.cSpalle);
        cDeltoidi = findViewById(R.id.cDeltoidi);
        cGambe = findViewById(R.id.cGambe);
        cCircuito = findViewById(R.id.cCircuito);
        //
        data = findViewById(R.id.input_Data);
        nomeAllenamento = findViewById(R.id.input_NomeAllenamento);
        esercizio = findViewById(R.id.input_Esercizio);
        kgriposo = findViewById(R.id.input_KgRiposo);
        durataAllenamento = findViewById(R.id.input_DurataAllenamento);

        btn_addAllenamento = findViewById(R.id.btn_AddAllenamento);
        btn_addAllenamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strAllenamento = loadStrAllenamento();

                if(!data.getText().toString().isEmpty() && !nomeAllenamento.getText().toString().isEmpty()){
                    try {
                        MainActivity.db.addAllenamento(data.getText().toString().trim(),
                                nomeAllenamento.getText().toString().trim(),
                                strAllenamento.trim(),
                                kgriposo.getText().toString().trim(),
                                0);
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
    public String loadStrAllenamento(){
        strAllenamento = "";

        if (cAddominali.isChecked()){
            strAllenamento += "-Addominali-";
        }
        if (cTrapezi.isChecked()){
            strAllenamento += "-Trapezi-";
        }
        if (cCardio.isChecked()){
            strAllenamento += "-Cardio-";
        }
        if (cPettorali.isChecked()){
            strAllenamento += "-Pettorali-";
        }
        if (cBicipiti.isChecked()){
            strAllenamento += "-Bicipiti-";
        }
        if (cTricipiti.isChecked()){
            strAllenamento += "-Tricipiti-";
        }
        if (cDorsali.isChecked()){
            strAllenamento += "-Dorsali-";
        }
        if (cSpalle.isChecked()){
            strAllenamento += "-Spalle-";
        }
        if (cDeltoidi.isChecked()){
            strAllenamento += "-Deltoidi-";
        }
        if (cGambe.isChecked()){
            strAllenamento += "-Gambe-";
        }
        if (cCircuito.isChecked()){
            strAllenamento += "-Circuito-";
        }

        return strAllenamento;
    }
}
