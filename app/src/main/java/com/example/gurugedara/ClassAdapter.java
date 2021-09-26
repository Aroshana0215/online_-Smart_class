package com.example.gurugedara;


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

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList class_id, class_subject, class_batch, class_type, class_fees;



    ClassAdapter(Activity activity, Context context, ArrayList class_id, ArrayList class_subject, ArrayList class_batch, ArrayList class_type,
                 ArrayList class_fees) {
        this.activity = activity;
        this.context = context;
        this.class_id = class_id;
        this.class_subject = class_subject;
        this.class_batch = class_batch;
        this.class_type = class_type;
        this.class_fees = class_fees;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.class_id_txt.setText(String.valueOf(class_id.get(position)));
        holder.class_subject_txt.setText(String.valueOf(class_subject.get(position)));
        holder.class_batch_txt.setText(String.valueOf(class_batch.get(position)));
        holder.class_type_txt.setText(String.valueOf(class_type.get(position)));
        holder.class_fees_txt.setText(String.valueOf(class_fees.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateAddClass.class);
                intent.putExtra("id", String.valueOf(class_id.get(position)));
                intent.putExtra("subject", String.valueOf(class_subject.get(position)));
                intent.putExtra("batch", String.valueOf(class_batch.get(position)));
                intent.putExtra("classType", String.valueOf(class_type.get(position)));
                intent.putExtra("classFees", String.valueOf(class_fees.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return class_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView class_id_txt, class_subject_txt, class_batch_txt, class_type_txt, class_fees_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            class_id_txt = itemView.findViewById(R.id.class_id_txt);
            class_subject_txt = itemView.findViewById(R.id.class_subject_txt);
            class_batch_txt = itemView.findViewById(R.id.class_batch_txt);
            class_type_txt = itemView.findViewById(R.id.class_type_txt);
            class_fees_txt = itemView.findViewById(R.id.class_fees_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
