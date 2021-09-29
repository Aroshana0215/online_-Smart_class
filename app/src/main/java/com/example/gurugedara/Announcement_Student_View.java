package com.example.gurugedara;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Announcement_Student_View extends AppCompatActivity {

    RecyclerView announcement_student_view_recyclerView ;

    AnnouncementDatabaseHelper myDB ;
    ArrayList<String> announcement_id , announcement_topic , announcement_content ;
    AnnouncementStudentViewCustomAdapter announcementStudentViewCustomAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_student_view);

        announcement_student_view_recyclerView = findViewById(R.id.announcement_student_view_recyclerView);

        myDB = new AnnouncementDatabaseHelper(Announcement_Student_View.this);
        announcement_id = new ArrayList<>();
        announcement_topic = new ArrayList<>();
        announcement_content= new ArrayList<>();

        storeDataInArrays();

        announcementStudentViewCustomAdapter = new AnnouncementStudentViewCustomAdapter(Announcement_Student_View.this , this , announcement_id , announcement_topic , announcement_content);
        announcement_student_view_recyclerView.setAdapter(announcementStudentViewCustomAdapter);
        announcement_student_view_recyclerView.setLayoutManager(new LinearLayoutManager(Announcement_Student_View.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();

        }    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "No Announcements" , Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                announcement_id.add(cursor.getString(0));
                announcement_topic.add(cursor.getString(1));
                announcement_content.add(cursor.getString(2));

            }
        }
    }
}