package com.example.gurugedara;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddQuestion extends AppCompatActivity {

    EditText registernum;
    EditText title;
    EditText question;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);


        registernum = findViewById(R.id.registernum);
        title = findViewById(R.id.title);
        question = findViewById(R.id.question);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question_MyDatabaseHelper myDB = new Question_MyDatabaseHelper(AddQuestion.this);
                myDB.addQusetion(
                        registernum.getText().toString().trim(),
                        title.getText().toString().trim(),
                        question.getText().toString().trim()

                );
            }
        });


    }
}