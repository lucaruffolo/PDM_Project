package com.example.pdm_project;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.IccOpenLogicalChannelResponse;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ThirdFragment extends Fragment {

    private int Addominali = 0,Trapezi = 0, Cardio = 0, Pettorali = 0, Bicipiti = 0, Tricipiti = 0,
            Dorsali = 0, Spalle = 0, Deltoidi = 0, Gambe = 0, Circuito = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third, container,         false);

        loadNumber();
        PieChart chart = rootView.findViewById(R.id.chart);
        ArrayList<PieEntry> v = new ArrayList<>();
        addAllenamentiOnPie(v);

        PieDataSet pieDataSet = new PieDataSet(v,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(20f);
        pieDataSet.setSliceSpace(2.0f);

        PieData pied = new PieData(pieDataSet);
        chart.setData(pied);
        chart.setCenterText("%");

        if (HomeActivity.NIGHT_MODE == true) {
            chart.setCenterTextColor(Color.WHITE);
            chart.setHoleColor(Color.BLACK);
        }else {
            chart.setHoleColor(Color.WHITE);
            chart.setCenterTextColor(Color.BLACK);
        }
        chart.setCenterTextSize(40f);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.animate();



        return rootView;
    }

    public void loadNumber(){
        Cursor cursor = MainActivity.db.getDataEsercizi();
        ArrayList<String> array = new ArrayList<>();
        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "Database vuoto", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                array.add(cursor.getString(0));
            }
        }
        countSetValue(array);
    }

    public void countSetValue(ArrayList<String> array){
        for (String x: array) {
            if(x.contains("Addominali")){
                Addominali += 1;
            }
            if(x.contains("Trapezi")){
                Trapezi += 1;
            }
            if(x.contains("Cardio")){
                Cardio += 1;
            }
            if(x.contains("Pettorali")){
                Pettorali += 1;
            }
            if(x.contains("Bicipiti")){
                Bicipiti += 1;
            }
            if(x.contains("Tricipiti")){
                Tricipiti += 1;
            }
            if(x.contains("Dorsali")){
                Dorsali += 1;
            }
            if(x.contains("Spalle")){
                Spalle += 1;
            }
            if(x.contains("Deltoidi")){
                Deltoidi += 1;
            }
            if(x.contains("Gambe")){
                Gambe += 1;
            }
            if(x.contains("Circuito")){
                Circuito += 1;
            }
        }
    }
    public void addAllenamentiOnPie(ArrayList<PieEntry> v){
        if (Addominali > 0) v.add(new PieEntry(Addominali, "Addominali"));
        if (Trapezi > 0)  v.add(new PieEntry(Trapezi, "Trapezi"));
        if (Cardio > 0) v.add(new PieEntry(Cardio, "Cardio"));
        if (Pettorali > 0) v.add(new PieEntry(Pettorali, "Pettorali"));
        if (Bicipiti > 0)v.add(new PieEntry(Bicipiti, "Bicipiti"));
        if (Tricipiti > 0)v.add(new PieEntry(Tricipiti, "Tricipiti"));
        if (Dorsali > 0) v.add(new PieEntry(Dorsali, "Dorsali"));
        if (Spalle > 0)v.add(new PieEntry(Spalle, "Spalle"));
        if (Deltoidi > 0) v.add(new PieEntry(Deltoidi, "Deltoidi"));
        if (Gambe > 0)v.add(new PieEntry(Gambe, "Gambe"));
        if (Circuito > 0) v.add(new PieEntry(Circuito, "Circuito"));
    }
}