package com.example.gurugedara;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;


public class Menu_Student extends AppCompatActivity implements View.OnClickListener{
    private CardView btn_payment_menu , btn_question_menu , btn_student_material_menu , btn_notes_menu , btn_announcement_menu , btn_user_profile_menu ,btn_support_service ,  btn_logout_menu;
    Button btn_delete_profile_student ;
    FirebaseAuth auth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_student);
        btn_logout_menu = (CardView) findViewById(R.id.btn_logout_menu);
        btn_logout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext() , Student_Login.class));
                finish();
            }
        });

        //defining Cards
        btn_payment_menu = (CardView) findViewById(R.id.btn_payment_menu);
        btn_question_menu = (CardView) findViewById(R.id.btn_question_menu);
        btn_student_material_menu = (CardView) findViewById(R.id.btn_student_material_menu);
        btn_notes_menu = (CardView) findViewById(R.id.btn_notes_menu);
        btn_announcement_menu = (CardView) findViewById(R.id.btn_announcement_menu);
        btn_user_profile_menu = (CardView) findViewById(R.id.btn_user_profile_menu);
        btn_support_service = (CardView) findViewById(R.id.btn_support_service_menu) ;
        //btn_logout_menu = (CardView) findViewById(R.id.btn_logout_menu);

        //Add Click listener to the cards
        btn_payment_menu.setOnClickListener(this);
        btn_question_menu.setOnClickListener(this);
        btn_student_material_menu.setOnClickListener(this);
        btn_notes_menu.setOnClickListener(this);
        btn_announcement_menu.setOnClickListener(this);
        btn_user_profile_menu.setOnClickListener(this);
        //btn_logout_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;
        switch(v.getId()){
            //case R.id.btn_payment_menu: i = new Intent(this , Bank.class) ; startActivity(i) ; break ;
            //case R.id.btn_question_menu: i = new Intent(this , Bank.class) ; startActivity(i) ; break ;
            //case R.id.btn_student_material_menu: i = new Intent(this , Bank.class) ; startActivity(i) ; break ;
            //case R.id.btn_notes_menu: i = new Intent(this , Bank.class) ; startActivity(i) ; break ;
            case R.id.btn_announcement_menu: i = new Intent(this , Announcement.class) ; startActivity(i) ; break ;
            case R.id.btn_user_profile_menu: i = new Intent(this , UserProfile.class) ; startActivity(i) ; break ;
            //case R.id.btn_support_service_menu: i = new Intent(this , UserProfile.class) ; startActivity(i) ; break ;
            //case R.id.btn_logout_menu: i = new Intent(this , Bank.class) ; startActivity(i) ; break ;

        }

    }
}