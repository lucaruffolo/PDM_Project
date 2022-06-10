package com.example.pdm_project;

import static com.example.pdm_project.R.id.btnAddAllenamento;
import static com.example.pdm_project.R.id.btnAllenamentoDue;
import static com.example.pdm_project.R.id.btnAllenamentoTre;
import static com.example.pdm_project.R.id.btnAllenamentoUno;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SecondFragment extends Fragment implements View.OnClickListener {

    private Button btnUno;
    private Button btnDue;
    private Button btnTre;
    private Button btnAdd;
    private String currentDate;


    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container,         false);
        currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        btnUno = (Button) rootView.findViewById(btnAllenamentoUno);
        btnDue = (Button) rootView.findViewById(btnAllenamentoDue);
        btnTre = (Button) rootView.findViewById(btnAllenamentoTre);
        btnAdd = (Button) rootView.findViewById(btnAddAllenamento);
        btnUno.setOnClickListener(this);
        btnDue.setOnClickListener(this);
        btnTre.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case btnAllenamentoUno:
                MainActivity.db.addAllenamento(currentDate,
                        "Giorno 1",
                        "Cardio-Dorsali-Tricipiti",
                        "0",
                        40);
                break;
            case btnAllenamentoDue:
                MainActivity.db.addAllenamento(currentDate,
                        "Giorno 2",
                        "Bicipiti-Pettorali",
                        "0",
                        40);
                break;
            case btnAllenamentoTre:
                MainActivity.db.addAllenamento(currentDate,
                        "Giorno 3",
                        "Gambe-Spalle-Addominali",
                        "0",
                        40);
                break;
            case btnAddAllenamento:
                Intent actvAdd = new Intent(getActivity(), AddAllenamento.class);
                startActivity(actvAdd);
                break;
            default:
                break;
        }
    }
}