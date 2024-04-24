package com.example.pdm_project;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private ViewFlipper vflipper;
    private RecyclerView rv;
    private CustomAdapter ca;
    public static ArrayList<String> array_id;
    public static ArrayList<String> array_nomeAllenamento;
    public static ArrayList<String> array_dataAllenamento;
    public static ArrayList<String> array_esercizioAllenamento;
    public static ArrayList<String> array_kgRiposoAllenamento;
    public static ArrayList<String> array_DurataAllenamento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        vflipper = view.findViewById(R.id.flipper);
        int images[] = {R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6};
        for (int i :images){
            loadImages(i);
        }

        loadData(view);
        return view;
    }

    public void loadImages(int image){
        ImageView imgv = new ImageView(getContext());
        imgv.setBackgroundResource(image);

        vflipper.addView(imgv);
        vflipper.setFlipInterval(4000);
        vflipper.setAutoStart(true);

        vflipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        vflipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }

    public void loadData(View view){
        rv = (RecyclerView) view.findViewById(R.id.RecyclerView);
        array_id = new ArrayList<>();
        array_nomeAllenamento = new ArrayList<>();
        array_dataAllenamento = new ArrayList<>();
        array_esercizioAllenamento = new ArrayList<>();
        array_kgRiposoAllenamento = new ArrayList<>();
        array_DurataAllenamento = new ArrayList<>();

        storeDataInArrays();
        //Toast.makeText(view.getContext(), "Caricato " + array_id.size() + " Allenamenti", Toast.LENGTH_SHORT).show();

        ca = new CustomAdapter(getContext(),array_id, array_nomeAllenamento,array_dataAllenamento,array_esercizioAllenamento,array_kgRiposoAllenamento,array_DurataAllenamento);
        rv.setAdapter(ca);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void storeDataInArrays(){
        Cursor cursor = MainActivity.db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "Database vuoto", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                array_id.add(cursor.getString(0));
                String outputDate = convertDateFormat(cursor.getString(1), "yyyyMMdd", "dd/MM/yyyy");
                array_dataAllenamento.add(outputDate);
                array_nomeAllenamento.add(cursor.getString(2));
                array_esercizioAllenamento.add(cursor.getString(3));
                array_kgRiposoAllenamento.add(cursor.getString(4));
                array_DurataAllenamento.add(cursor.getString(5));
            }
        }
    }
    public static String convertDateFormat(String inputDate, String inputFormat, String outputFormat) {
        String outputDate = "";

        try {
            // Definisci il formato di input e di output
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

            // Parsa la data di input
            Date date = inputDateFormat.parse(inputDate);

            // Formatta la data nel nuovo formato
            outputDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }
}
