package com.example.pdm_project;

import static com.example.pdm_project.R.id.btnAddAllenamento;
import static com.example.pdm_project.R.id.btnAllenamentoDue;
import static com.example.pdm_project.R.id.btnAllenamentoTre;
import static com.example.pdm_project.R.id.btnAllenamentoUno;
import static com.example.pdm_project.R.id.secondFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private Button btnUno;
    private Button btnDue;
    private Button btnTre;
    private Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false);

        View rootView = inflater.inflate(R.layout.fragment_second, container,         false);

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
                Intent actvUno = new Intent(getActivity(), AllenamentoActivity.class);
                startActivity(actvUno);
                break;
            case btnAllenamentoDue:
                Intent actvDue = new Intent(getActivity(), AllenamentoActivity.class);
                startActivity(actvDue);
                break;
            case btnAllenamentoTre:
                Intent actvTre = new Intent(getActivity(), AllenamentoActivity.class);
                startActivity(actvTre);
                break;
            case btnAddAllenamento:
                Intent actvAdd = new Intent(getActivity(), AllenamentoActivity.class);
                startActivity(actvAdd);
                break;
            default:
                break;
        }
    }
}