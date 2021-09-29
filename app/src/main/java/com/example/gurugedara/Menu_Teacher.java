package com.example.gurugedara;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.firebase.auth.FirebaseAuth;

public class Menu_Teacher extends AppCompatActivity implements View.OnClickListener {
    private CardView  btn_classes_menu , btn_question_teacher_menu , btn_student_material_teacher_menu , btn_notes_teacher_menu , btn_announcement_teacher_menu ,   btn_logout_teacher_menu;

    FirebaseAuth auth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_teacher);


        btn_logout_teacher_menu = (CardView) findViewById(R.id.btn_logout_teacher_menu);
        btn_logout_teacher_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Teacher_Login.class));
                finish();
            }
        });
            //defining Cards
            btn_classes_menu = (CardView) findViewById(R.id.btn_classes_menu);
            btn_question_teacher_menu = (CardView) findViewById(R.id.btn_question_teacher_menu);
            btn_student_material_teacher_menu = (CardView) findViewById(R.id.btn_student_material_teacher_menu);
            btn_notes_teacher_menu = (CardView) findViewById(R.id.btn_notes_teacher_menu);
            btn_announcement_teacher_menu = (CardView) findViewById(R.id.btn_announcement_teacher_menu);
            //btn_user_profile_teacher_menu = (CardView) findViewById(R.id.btn_user_profile_teacher_menu);
            //btn_support_service_menu = (CardView) findViewById(R.id.btn_support_service_menu) ;
            //btn_logout_menu = (CardView) findViewById(R.id.btn_logout_menu);

            //Add Click listener to the cards
            btn_classes_menu.setOnClickListener( this);
            btn_question_teacher_menu.setOnClickListener( this);
            btn_student_material_teacher_menu.setOnClickListener( this);
            btn_notes_teacher_menu.setOnClickListener( this);
            btn_announcement_teacher_menu.setOnClickListener( this);
            //btn_user_profile_teacher_menu.setOnClickListener(this);
            //btn_support_service_menu.setOnClickListener(this);
            //btn_logout_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i1 ;
        switch(v.getId()){
            case R.id.btn_classes_menu: i1 = new Intent(this , AddClass.class) ; startActivity(i1) ; break ;
            case R.id.btn_question_teacher_menu: i1 = new Intent(this , QestioBox.class) ; startActivity(i1) ; break ;
            case R.id.btn_student_material_teacher_menu: i1 = new Intent(this , StudyMaterials.class) ; startActivity(i1) ; break ;
            case R.id.btn_notes_teacher_menu: i1 = new Intent(this , ActivityNote.class) ; startActivity(i1) ; break ;
            case R.id.btn_announcement_teacher_menu: i1 = new Intent(this , Announcement.class) ; startActivity(i1) ; break ;
            //case R.id.btn_user_profile_teacher_menu: i1 = new Intent(this , UserProfile.class) ; startActivity(i1) ; break ;
            //case R.id.btn_support_service_menu: i1 = new Intent(this , ActivitySupport.class) ; startActivity(i1) ; break ;
            //case R.id.btn_logout_menu: i = new Intent(this , Bank.class) ; startActivity(i) ; break ;

        }

    }
}