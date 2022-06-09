package com.example.pdm_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class EditAllenamento extends AppCompatActivity {

    private EditText nome_input, data_input, esercizi_input, durata_input,kgrip_input;
    private Button btnEdit, btnDelete;
    private String id,nome, data, esercizi, durata,kgrip, strAllenamento;
    private CheckBox cAddominali, cTrapezi, cCardio, cPettorali, cBicipiti, cTricipiti,
            cDorsali, cSpalle, cDeltoidi, cGambe, cCircuito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_allenamento);

        // load checkbox
        cAddominali = findViewById(R.id.cEAddominali);
        cTrapezi = findViewById(R.id.cETrapezi);
        cCardio = findViewById(R.id.cECardio);
        cPettorali = findViewById(R.id.cEPettorali);
        cBicipiti = findViewById(R.id.cEBicipiti);
        cTricipiti = findViewById(R.id.cETricipiti);
        cDorsali = findViewById(R.id.cEDorsali);
        cSpalle = findViewById(R.id.cESpalle);
        cDeltoidi = findViewById(R.id.cEDeltoidi);
        cGambe = findViewById(R.id.cEGambe);
        cCircuito = findViewById(R.id.cECircuito);

        //

        nome_input = findViewById(R.id.input_NomeAllenamento2);
        data_input = findViewById(R.id.input_Data2);
        esercizi_input = findViewById(R.id.input_Esercizio2);
        kgrip_input = findViewById(R.id.input_KgRiposo2);
        durata_input = findViewById(R.id.input_DurataAllenamento2);
        btnEdit = findViewById(R.id.btn_updateAllenamento);
        btnDelete = findViewById(R.id.btn_deleteAllenamento);

        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        data_input.setInputType(InputType.TYPE_NULL); // disabilita la tastiera al click

        data_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(EditAllenamento.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        data_input.setText(date);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = nome_input.getText().toString().trim();
                data = data_input.getText().toString().trim();
                //esercizi = esercizi_input.getText().toString().trim();
                esercizi = setNewStrAllenamento();
                kgrip = kgrip_input.getText().toString().trim();
                durata = durata_input.getText().toString().trim();
                MainActivity.db.updateData(id,nome,data,esercizi,kgrip,durata);
                finish();
                Intent actvAdd = new Intent(EditAllenamento.this, HomeActivity.class);
                startActivity(actvAdd);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
            kgrip = getIntent().getStringExtra("kgrip");

            //set

            nome_input.setText(nome);
            data_input.setText(data);
            esercizi_input.setText(esercizi);
            durata_input.setText(durata);
            kgrip_input.setText(kgrip);


            loadStrAllenamentoFromDB(esercizi);


        }else{
            Toast.makeText(this, "Errore edit", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Eliminare "+ nome + " in data " + data+ " ?");
        b.setMessage("Sei sicuro di voler eliminare l'allenamento?");
        b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.db.deleteOneRow(id);
                finish();
                Intent actvAdd = new Intent(EditAllenamento.this, HomeActivity.class);
                startActivity(actvAdd);
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        b.create().show();
    }

    public void loadStrAllenamentoFromDB(String strAllenamento){

        if(strAllenamento.contains("Addominali")){
            cAddominali.setChecked(true);
        }
        if(strAllenamento.contains("Trapezi")){
            cTrapezi.setChecked(true);
        }
        if(strAllenamento.contains("Cardio")){
            cCardio.setChecked(true);
        }
        if(strAllenamento.contains("Pettorali")){
            cPettorali.setChecked(true);
        }
        if(strAllenamento.contains("Bicipiti")){
            cBicipiti.setChecked(true);
        }
        if(strAllenamento.contains("Tricipiti")){
            cTricipiti.setChecked(true);
        }
        if(strAllenamento.contains("Dorsali")){
            cDorsali.setChecked(true);
        }
        if(strAllenamento.contains("Spalle")){
            cSpalle.setChecked(true);
        }
        if(strAllenamento.contains("Deltoidi")){
            cDeltoidi.setChecked(true);
        }
        if(strAllenamento.contains("Gambe")){
            cGambe.setChecked(true);
        }
        if(strAllenamento.contains("Circuito")){
            cCircuito.setChecked(true);
        }

    }


    public String setNewStrAllenamento(){
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
