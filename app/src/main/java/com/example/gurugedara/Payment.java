package com.example.gurugedara;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Payment extends AppCompatActivity {

    RecyclerView recycleViewPayment;
    FloatingActionButton add_buttonPayment;

    Payment_MyDataBaseHelper myDB;
    ArrayList<String> id,registernum, name,slip,bank,month;
    Payment_Adapter   payment_Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        recycleViewPayment = findViewById(R.id.recycleViewPayment);
        add_buttonPayment = findViewById(R.id.add_buttonPayment);
        add_buttonPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Payment.this, AddPayment.class);
                startActivity(intent);
            }
        });


        myDB  = new Payment_MyDataBaseHelper(Payment.this);
        registernum = new ArrayList<>();
        id = new ArrayList<>();
        name = new ArrayList<>();
        slip = new ArrayList<>();
        bank = new ArrayList<>();
        month = new ArrayList<>();

        storeDataInArray();
        payment_Adapter = new Payment_Adapter(Payment.this,this,id,registernum, name,slip,bank,month);
        recycleViewPayment.setAdapter(payment_Adapter);
        recycleViewPayment.setLayoutManager(new LinearLayoutManager(Payment.this));

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

                id.add(cursor.getString(0));
                registernum.add(cursor.getString(1));
                name.add(cursor.getString(2));
                slip.add(cursor.getString(3));
                bank.add(cursor.getString(4));
                month.add(cursor.getString(5));

            }
        }
    }

}