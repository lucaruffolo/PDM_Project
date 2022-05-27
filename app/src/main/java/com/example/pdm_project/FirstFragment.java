package com.example.pdm_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FirstFragment extends Fragment {

    private TableLayout table;
    private TableRow row;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        table = (TableLayout) view.findViewById(R.id.table);
      //  LinearLayout layout = getView().findViewById(R.id.layout);
       // ViewGroup.LayoutParams params = layout.getLayoutParams();

        for (int i = 0; i<=14; i++){
            row = new TableRow(getActivity());
            TextView textData = new TextView(getActivity());
            TextView textAllenamento = new TextView(getActivity());

                /*    android:layout_width="0dp"
                            android:layout_height="wrap_content"
                            android:layout_weight="2"
                            android:gravity="center_horizontal"
                            android:padding="10sp"
                            android:text="DATA"
                            android:textColor="@color/white"
                            android:textSize="14sp" />*/
            textData.setText("01/01/2022");
            textData.setTextColor(Color.BLACK);
            textData.setGravity(Gravity.CENTER_HORIZONTAL);
            textData.setTextSize(14);
            textData.setPadding(10, 10, 10, 10);
      //      textData.setLayoutParams(params);


            textAllenamento.setText("ALLENAMENTO");
            textAllenamento.setTextColor(Color.BLACK);
            textAllenamento.setGravity(Gravity.CENTER_HORIZONTAL);
            textAllenamento.setTextSize(14);
            textAllenamento.setPadding(10, 10, 10, 10);

            row.addView(textData);
            row.addView(textAllenamento);

            table.addView(row);
        }

        return view;
      //  return inflater.inflate(R.layout.fragment_first, container, false);
    }

}
