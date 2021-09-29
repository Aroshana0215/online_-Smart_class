package com.example.gurugedara;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddClass extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    ClassDatabaseHelper myDB;
    ArrayList<String> class_id, class_subject, class_batch, class_type,  class_fees;
    ClassAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener ((view) -> {
                Intent intent = new Intent(AddClass.this, AddActivity.class);
                startActivity(intent);
        });

        myDB = new ClassDatabaseHelper(AddClass.this);
        class_id = new ArrayList<>();
        class_subject = new ArrayList<>();
        class_batch = new ArrayList<>();
        class_type = new ArrayList<>();
        class_fees = new ArrayList<>();

        storeDataArrays();

        customAdapter = new ClassAdapter(AddClass.this,this, class_id, class_subject, class_batch, class_type,
                class_fees);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddClass.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void storeDataArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()) {
                class_id.add(cursor.getString(0));
                class_subject.add(cursor.getString(1));
                class_batch.add(cursor.getString(2));
                class_type.add(cursor.getString(3));
                class_fees.add(cursor.getString(4));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClassDatabaseHelper myDB = new ClassDatabaseHelper(AddClass.this);
                myDB.deleteAllData();
                Intent intent = new Intent(AddClass.this, AddClass.class);
                startActivity(intent);
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