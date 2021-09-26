package com.example.gurugedara;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText subject_input, batch_input, classType_input, classFees_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        subject_input = findViewById(R.id.subject_input);
        batch_input = findViewById(R.id.batch_input);
        classType_input = findViewById(R.id.classType_input);
        classFees_input = findViewById(R.id.classFees_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDatabaseHelper myDB = new ClassDatabaseHelper(AddActivity.this);
                myDB.addClass(subject_input.getText().toString().trim(),
                        batch_input.getText().toString().trim(),
                        classType_input.getText().toString().trim(),
                        classFees_input.getText().toString().trim());
            }
        });
    }
}