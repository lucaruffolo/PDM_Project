package com.example.pdm_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FirstFragment extends Fragment {

    private TableLayout table;
    private TableRow row;
    private LinearLayout mll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        table = (TableLayout) view.findViewById(R.id.table);
        mll = (LinearLayout) view.findViewById(R.id.lllayout);

        int childCount = table.getChildCount();
        Toast.makeText(getContext(), "Caricato " + MainActivity.array_id.size() + " Allenamenti", Toast.LENGTH_SHORT).show();
        // Remove all rows except the first one

        if (childCount > 1) {
            table.removeViews(1, childCount - 1);
        }
        MainActivity.loadDataFromDB();


        int textColor, backgroundColorOne, backgroundColorTwo;

        if (HomeActivity.NIGHT_MODE){
            textColor = Color.LTGRAY;
            backgroundColorOne = Color.BLACK;
            backgroundColorTwo = Color.rgb(23,0,77);
        }else{
            textColor = Color.BLACK;
            backgroundColorOne = Color.WHITE;
            backgroundColorTwo = Color.LTGRAY;
        }

        //load data in table

        for (int i = 0; i< MainActivity.array_id.size() ; i++){
            row = new TableRow(getActivity());
            TextView textData = new TextView(getActivity());
            TextView textAllenamento = new TextView(getActivity());
            textData.setText(MainActivity.array_dataAllenamento.get(i));
            textData.setTextColor(textColor);
            textData.setGravity(Gravity.CENTER_HORIZONTAL);
            textData.setTextSize(14);
            textData.setPadding(100, 20, 0, 20);

            textAllenamento.setText(MainActivity.array_nomeAllenamento.get(i));
            textAllenamento.setTextColor(textColor);
            textAllenamento.setGravity(Gravity.CENTER_HORIZONTAL);
            textAllenamento.setTextSize(14);
            textAllenamento.setPadding(300, 20, 0, 20);

            if (i %2!= 0)
                row.setBackgroundColor(backgroundColorOne);
            else
                row.setBackgroundColor(backgroundColorTwo);

            row.addView(textData);
            row.addView(textAllenamento);
            table.addView(row);
        }

        return view;
      //  return inflater.inflate(R.layout.fragment_first, container, false);
    }

}
