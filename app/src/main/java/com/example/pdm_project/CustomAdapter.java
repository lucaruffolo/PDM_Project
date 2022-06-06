package com.example.pdm_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;

    private ArrayList<String> nome, data;


    CustomAdapter(Activity activity,Context context, ArrayList<String> nome, ArrayList<String> data){
        this.activity = activity;
        this.context = context;
        this.nome = nome;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.dataview.setText(String.valueOf(data.get(position)));
        holder.nomeview.setText(String.valueOf(nome.get(position)));
    }

    @Override
    public int getItemCount() {

        return nome.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dataview,nomeview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dataview = itemView.findViewById(R.id.home_ViewDataAllenamento);
            nomeview = itemView.findViewById(R.id.home_ViewNomeAllenamento);
        }
    }
}
