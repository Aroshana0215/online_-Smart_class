package com.example.gurugedara;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateQuestion extends AppCompatActivity {

    EditText registernum;
    EditText title;
    EditText question;
    Button update_button , delete_button;

    String i, rg , ti, qu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        registernum = findViewById(R.id.registernum2);
        title = findViewById(R.id.title2);
        question = findViewById(R.id.question2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(rg);
        }


        update_button.setOnClickListener(view -> {

            Question_MyDatabaseHelper myDB = new Question_MyDatabaseHelper(UpdateQuestion.this);
                   rg  =  registernum.getText().toString().trim();
                   ti =  title.getText().toString().trim();
                   qu =  question.getText().toString().trim();
            myDB.updateData(i, rg , ti, qu);

        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog();
            }
        });



    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("registernum") &&
                getIntent().hasExtra("title")  && getIntent().hasExtra("question")){

            i = getIntent().getStringExtra("id");
            rg = getIntent().getStringExtra("registernum");
            ti = getIntent().getStringExtra("title");
            qu = getIntent().getStringExtra("question");


            registernum.setText(rg);
            title.setText(ti);
            question.setText(qu);

        }else{
            Toast.makeText(this,"NO DATA.", Toast.LENGTH_SHORT).show();
        }

    }
    void ConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ " ?");
        builder.setMessage("Are sure you want to delete? "+ qu +" question");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int ii) {

                Question_MyDatabaseHelper myDB = new Question_MyDatabaseHelper(UpdateQuestion.this);
                myDB.DeleteOneRaw(i);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int ii) {


            }
        });
        builder.create().show();

    }

}