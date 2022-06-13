package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        data = findViewById(R.id.input_Data);
        nomeAllenamento = findViewById(R.id.input_NomeAllenamento);
        esercizio = findViewById(R.id.input_Esercizio);
        kgriposo = findViewById(R.id.input_KgRiposo);
        durataAllenamento = findViewById(R.id.input_DurataAllenamento);

        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        data.setInputType(InputType.TYPE_NULL); // disabilita la tastiera al click

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(AddAllenamento.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String sm = "0";
                        String dayz = "0";
                        if (day >0 && day < 10){
                            dayz = dayz + day;
                        }else{
                            dayz = Integer.toString(day);
                        }

                        if (month >= 1 && month < 10) {
                            sm = sm + month;
                        }else{
                            sm = Integer.toString(month);
                        }
                        String date = dayz+"/"+sm+"/"+year;
                        data.setText(date);
                    }
                },year,month,day);
                dpd.show();
            }
        });

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
