package com.example.gurugedara;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QestioBox extends AppCompatActivity {

    RecyclerView recycleView;
    FloatingActionButton add_button;

    Question_MyDatabaseHelper myDB;
    ArrayList<String> st_id,st_registernum,st_title,st_qusetion;

    QuestionAdapter  questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qestio_box);

        recycleView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QestioBox.this,AddQuestion.class);
                startActivity(intent);

            }
        });

        myDB  = new Question_MyDatabaseHelper(QestioBox.this);
        st_id = new ArrayList<>();
        st_registernum = new ArrayList<>();
        st_title = new ArrayList<>();
        st_qusetion = new ArrayList<>();

        storeDataInArray();

        questionAdapter = new QuestionAdapter(QestioBox.this,this,st_id,st_registernum,st_title,
                st_qusetion );
        recycleView.setAdapter(questionAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(QestioBox.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
           recreate();
        }
    }

    void storeDataInArray(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){

            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){

                st_id.add(cursor.getString(0));
                st_registernum.add(cursor.getString(1));
                st_title.add(cursor.getString(2));
                st_qusetion.add(cursor.getString(3));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            ConfirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void ConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All"+ " ?");
        builder.setMessage("Are sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int ii) {

                Question_MyDatabaseHelper myDB = new Question_MyDatabaseHelper(QestioBox.this);
                myDB.DeleteAllData();
                Intent intent = new Intent(QestioBox.this,QestioBox.class);
                startActivity(intent);
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