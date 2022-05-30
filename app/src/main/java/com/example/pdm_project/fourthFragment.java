package com.example.pdm_project;

import static com.example.pdm_project.R.id.btnAddAllenamento;
import static com.example.pdm_project.R.id.btnAllenamentoDue;
import static com.example.pdm_project.R.id.btnAllenamentoTre;
import static com.example.pdm_project.R.id.btnAllenamentoUno;
import static com.example.pdm_project.R.id.btnCronoReset;
import static com.example.pdm_project.R.id.btnCronoStart;
import static com.example.pdm_project.R.id.btnTimerReset;
import static com.example.pdm_project.R.id.btnTimerStart;
import static com.example.pdm_project.R.id.textCrono;
import static com.example.pdm_project.R.id.textTimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class fourthFragment extends Fragment implements View.OnClickListener {
    private int seconds;

    private TextView timerTextView;
    private Button stopStartBtnTimer;
    private Button resetTimer;

    private TextView cronoTextView;
    private Button stopStartBtnCrono;
    private Button resetCrono;
    private boolean cronoStart = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container,         false);

        timerTextView = (TextView) rootView.findViewById(textTimer);
        stopStartBtnTimer = (Button) rootView.findViewById(btnTimerStart);
        resetTimer = (Button) rootView.findViewById(btnTimerReset);


        cronoTextView = (TextView) rootView.findViewById(textCrono);
        stopStartBtnCrono = (Button) rootView.findViewById(btnCronoStart);
        resetCrono = (Button) rootView.findViewById(btnCronoReset);
        stopStartBtnCrono.setOnClickListener(this);

      //  return inflater.inflate(R.layout.fragment_fourth, container, false);
        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case btnCronoStart:
                if (cronoStart == false){
                    cronoStart = true;
                }
                break;
            default:
                break;
        }
    }
}