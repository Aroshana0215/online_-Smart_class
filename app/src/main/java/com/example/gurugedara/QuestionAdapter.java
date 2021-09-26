package com.example.gurugedara;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {
   private Context  context;
    Activity activity;
   private ArrayList  st_id, st_registernum,st_title,st_qusetion;
   Animation question_anim_translate;

    QuestionAdapter(Activity activity, Context  context,
                    ArrayList  st_id,
                    ArrayList st_registernum,
                    ArrayList st_title,
                    ArrayList st_qusetion){
        this.activity = activity;
        this.context = context;
        this.st_id = st_id;
        this.st_registernum = st_registernum;
        this.st_title = st_title;
        this.st_qusetion = st_qusetion;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.question_raw,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Question_id_txt.setText(String.valueOf(st_id.get(position)));
        holder.Question_registernum_txt.setText(String.valueOf(st_registernum.get(position)));
        holder.Question_title_txt.setText(String.valueOf(st_title.get(position)));
        holder.Question_Question_txt.setText(String.valueOf(st_qusetion.get(position)));
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context,UpdateQuestion.class);
            intent.putExtra("id",String.valueOf(st_id.get(position)));
            intent.putExtra("registernum",String.valueOf(st_registernum.get(position)));
            intent.putExtra("title",String.valueOf(st_title.get(position)));
            intent.putExtra("question",String.valueOf(st_qusetion.get(position)));
            activity.startActivityForResult(intent,1);

        });

    }

    @Override
    public int getItemCount() {
        return st_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  Question_id_txt,Question_registernum_txt,Question_title_txt,Question_Question_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            Question_id_txt = itemView.findViewById(R.id.Question_id_txt);
            Question_registernum_txt = itemView.findViewById(R.id.Question_registernum_txt);
            Question_title_txt = itemView.findViewById(R.id.Question_title_txt);
            Question_Question_txt = itemView.findViewById(R.id.Question_Question_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            question_anim_translate = AnimationUtils.loadAnimation(context, R.anim.question_anim_translate);
            mainLayout.setAnimation(question_anim_translate);
        }
    }
}
