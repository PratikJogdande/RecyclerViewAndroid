package com.example.app25;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList user_id, user_name, user_address, user_dob;

    CustomAdapter(Activity activity, Context context,
                  ArrayList user_id,
                  ArrayList user_name,
                  ArrayList user_address,
                  ArrayList user_dob){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_address = user_address;
        this.user_dob = user_dob;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_address_txt.setText(String.valueOf(user_address.get(position)));
        holder.user_dob_txt.setText(String.valueOf(user_dob.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("name", String.valueOf(user_name.get(position)));
                intent.putExtra("address", String.valueOf(user_address.get(position)));
                intent.putExtra("dob", String.valueOf(user_dob.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_name_txt, user_address_txt, user_dob_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            user_name_txt = itemView.findViewById(R.id.user_name_txt);
            user_address_txt = itemView.findViewById(R.id.user_address_txt);
            user_dob_txt = itemView.findViewById(R.id.user_dob_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
