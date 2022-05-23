package com.example.pdm_project;

import static com.example.pdm_project.R.id.btnAllenamentoUno;

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
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false);

        View rootView = inflater.inflate(R.layout.fragment_second, container,         false);

        btn = (Button) rootView.findViewById(btnAllenamentoUno);
        btn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAllenamentoUno:
                Log.i("we","we");
               break;
            default:
                break;
        }
    }
}