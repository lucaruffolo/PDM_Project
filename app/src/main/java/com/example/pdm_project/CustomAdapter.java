package com.example.pdm_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> id, nome, data,esercizio,kgrip,durata;

    CustomAdapter(Activity activity,Context context, ArrayList<String> id,ArrayList<String> nome, ArrayList<String> data, ArrayList<String> esercizio, ArrayList<String> kgrip, ArrayList<String> durata){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.esercizio = esercizio;
        this.kgrip = kgrip;
        this.durata = durata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.idview.setText(String.valueOf(id.get(position)));
        holder.dataview.setText(String.valueOf(data.get(position)));
        holder.nomeview.setText(String.valueOf(nome.get(position)));
        holder.eserciziview.setText(String.valueOf(esercizio.get(position)));
        holder.kgripview.setText(String.valueOf(kgrip.get(position)));
        holder.durataview.setText(String.valueOf(durata.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditAllenamento.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("nome", String.valueOf(nome.get(position)));
                intent.putExtra("data", String.valueOf(data.get(position)));
                intent.putExtra("esercizi", String.valueOf(esercizio.get(position)));
                intent.putExtra("kgrip", String.valueOf(kgrip.get(position)));
                intent.putExtra("durata", String.valueOf(durata.get(position)));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return nome.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idview,dataview,nomeview,eserciziview,kgripview,durataview;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idview =itemView.findViewById(R.id.home_ViewIdAllenamento);
            dataview = itemView.findViewById(R.id.home_ViewDataAllenamento);
            nomeview = itemView.findViewById(R.id.home_ViewNomeAllenamento);
            eserciziview =itemView.findViewById(R.id.home_ViewEserciziAllenamento);
            kgripview = itemView.findViewById(R.id.home_ViewkgripAllenamento);
            durataview = itemView.findViewById(R.id.home_ViewDurataAllenamento);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
