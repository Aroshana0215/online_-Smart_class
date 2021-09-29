package com.example.gurugedara;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateAddClass extends AppCompatActivity {

    EditText subject_input, batch_input, classType_input, classFees_input;
    Button update_button, delete_button;

    String id, subject, batch, classType, classFees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_add_class);

        subject_input = findViewById(R.id.subject_input2);
        batch_input = findViewById(R.id.batch_input2);
        classType_input = findViewById(R.id.classType_input2);
        classFees_input = findViewById(R.id.classFees_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(subject);
        }

        //update_button.setOnClickListener((view) -> {

        //});
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDatabaseHelper myDB = new ClassDatabaseHelper(UpdateAddClass.this);

                subject = subject_input.getText().toString().trim();
                batch = batch_input.getText().toString().trim();
                classType = classType_input.getText().toString().trim();
                classFees = classFees_input.getText().toString().trim();
                myDB.updateData( id , subject, batch, classType, classFees);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("subject") && getIntent().hasExtra("batch") &&
                getIntent().hasExtra("classType") && getIntent().hasExtra("classFees")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            subject = getIntent().getStringExtra("subject");
            batch = getIntent().getStringExtra("batch");
            classType = getIntent().getStringExtra("classType");
            classFees = getIntent().getStringExtra("classFees");

            //Setting Intent Data
            subject_input.setText(subject);
            batch_input.setText(batch);
            classType_input.setText(classType);
            classFees_input.setText(classFees);
            Log.d("stev", subject+" "+batch+" "+classType+" "+classFees);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + subject + " ?");
        builder.setMessage("Are you sure you want to delete " + subject + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClassDatabaseHelper myDB = new ClassDatabaseHelper(UpdateAddClass.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}