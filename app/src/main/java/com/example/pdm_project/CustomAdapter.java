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
    private ArrayList<String> id, nome, data;

    CustomAdapter(Activity activity,Context context, ArrayList<String> id,ArrayList<String> nome, ArrayList<String> data){
        this.activity = activity;
        this.context = context;
        this.id = id;
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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.idview.setText(String.valueOf(id.get(position)));
        holder.dataview.setText(String.valueOf(data.get(position)));
        holder.nomeview.setText(String.valueOf(nome.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditAllenamento.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("nome", String.valueOf(nome.get(position)));
                intent.putExtra("data", String.valueOf(data.get(position)));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return nome.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idview,dataview,nomeview;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idview =itemView.findViewById(R.id.home_ViewIdAllenamento);
            dataview = itemView.findViewById(R.id.home_ViewDataAllenamento);
            nomeview = itemView.findViewById(R.id.home_ViewNomeAllenamento);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
