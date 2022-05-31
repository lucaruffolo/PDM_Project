package com.example.pdm_project;

import static com.example.pdm_project.R.id.btnAddAllenamento;
import static com.example.pdm_project.R.id.btnAllenamentoDue;
import static com.example.pdm_project.R.id.btnAllenamentoTre;
import static com.example.pdm_project.R.id.btnAllenamentoUno;
import static com.example.pdm_project.R.id.btnCronoReset;
import static com.example.pdm_project.R.id.btnCronoStart;
import static com.example.pdm_project.R.id.btnTimerReset;
import static com.example.pdm_project.R.id.btnTimerStart;
import static com.example.pdm_project.R.id.inputTimer;
import static com.example.pdm_project.R.id.textCrono;
import static com.example.pdm_project.R.id.textImpostaTimer;
import static com.example.pdm_project.R.id.textTimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class fourthFragment extends Fragment implements View.OnClickListener {

    // === Timer
    private TextView timerTextView;
    private Button btnStartTimer;
    private Button resetTimer;
    private boolean timerStart = false;
    private boolean oneRun = false;
    private boolean timerRunning = false;
    private TextView textImpostaTimerz;
    private CountDownTimer countDownTimer;
    private long timeLeftInMs = 60000;
    private TextInputLayout inputTimers;

    // === Cronometro
    private TextView cronoTextView;
    private Button btnStartCrono;
    private Button resetCrono;
    private boolean cronoStart = false;
    private Chronometer cronometro;
    private long timeWhenStopped = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {  super.onCreate(savedInstanceState);  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container,         false);

        timerTextView = (TextView) rootView.findViewById(textTimer);
        btnStartTimer = (Button) rootView.findViewById(btnTimerStart);
        resetTimer = (Button) rootView.findViewById(btnTimerReset);
        textImpostaTimerz = (TextView) rootView.findViewById(textImpostaTimer);

        cronometro = (Chronometer) rootView.findViewById(textCrono);
        btnStartCrono = (Button) rootView.findViewById(btnCronoStart);
        resetCrono = (Button) rootView.findViewById(btnCronoReset);


        btnStartTimer.setOnClickListener(this);
        resetTimer.setOnClickListener(this);
        btnStartCrono.setOnClickListener(this);
        resetCrono.setOnClickListener(this);

        inputTimers = (TextInputLayout) rootView.findViewById(inputTimer);




      //  return inflater.inflate(R.layout.fragment_fourth, container, false);
        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case btnCronoReset:
                resetCrono();
                break;
            case btnCronoStart:
                startCrono();
                break;
            case btnTimerStart:
                if (timerStart == false){
                    timerStart = true;
                    startTimer();
                }else {
                    stopTimer();
                    timerStart = false;
                }
                break;
            case btnTimerReset:
                resetTimer();
                timerStart = false;
                break;
            default:
                break;
        }
    }

    public void startTimer(){

        oneRun = true;
        countDownTimer = new CountDownTimer(timeLeftInMs, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMs = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        btnStartTimer.setText("PAUSA");
        timerRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        btnStartTimer.setText("RIPRENDI");
    }
    public void resetTimer(){
        btnStartTimer.setText("START");

        if (!inputTimers.getEditText().getText().toString().isEmpty()) {
            if (oneRun) {
                countDownTimer.cancel();
                timerRunning = false;
            }
            String myDate = inputTimers.getEditText().getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
            Date date = null;
            try {
                date = sdf.parse(myDate);
                long millis = date.getTime();
                timerTextView.setText(inputTimers.getEditText().getText());
                timeLeftInMs = millis;
                textImpostaTimerz.setText("Timer impostato");
                textImpostaTimerz.setTextColor(Color.BLUE);
            } catch (ParseException e) {
                Toast.makeText(getActivity(), "Errore orario, usa il formato MINUTI:SECONDI", Toast.LENGTH_SHORT).show();
                textImpostaTimerz.setTextColor(Color.RED);
                textImpostaTimerz.setText("ERRORE TIMER");
                //e.printStackTrace();
            }

        } else{
            if (oneRun) {
                countDownTimer.cancel();
                timerRunning = false;
            }
            timerTextView.setText("01:00");
            timeLeftInMs = 60000;
        }
    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMs / 60000;
        int seconds = (int) timeLeftInMs % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        timerTextView.setText(timeLeftText);

    }



    public void startCrono(){
        if (cronoStart == false) {
            cronoStart = true;
            cronometro.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            cronometro.start();
            btnStartCrono.setText("PAUSA");
        }else {
            cronometro.stop();
            timeWhenStopped = cronometro.getBase() - SystemClock.elapsedRealtime();
            btnStartCrono.setText("RIPRENDI");
            cronoStart = false;
        }
    }
    public void resetCrono(){
        cronoStart = false;
        cronometro.stop();
        btnStartCrono.setText("START");
        timeWhenStopped = 0;
        cronometro.setBase(SystemClock.elapsedRealtime());


    }
}