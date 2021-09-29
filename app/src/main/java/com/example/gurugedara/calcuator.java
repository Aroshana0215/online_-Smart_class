package com.example.gurugedara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class calcuator extends AppCompatActivity {

    EditText et_1;
    EditText et_2;
    RadioButton rbutt_1;
    RadioButton rbutt_2;
    RadioButton rbutt_3;
    RadioButton rbutt_4;
    Button butt_1;
    TextView tv_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcuator);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        rbutt_1 = findViewById(R.id.rbutt_1);
        rbutt_2 = findViewById(R.id.rbutt_2);
        rbutt_3 = findViewById(R.id.rbutt_3);
        rbutt_4 = findViewById(R.id.rbutt_4);
        butt_1 = findViewById(R.id.butt_1);
        tv_1 = findViewById(R.id.tv_1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        butt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }

    private void calculateAnswer(){
        calcuation calc = new calcuation();

        String Tempv = et_1.getText().toString();
        String Tempv2 = et_2.getText().toString();


        if(TextUtils.isEmpty(Tempv) && TextUtils.isEmpty(Tempv2)){
            Toast.makeText(this,"PLEASE ENTER A NUMBER",Toast.LENGTH_LONG).show();

            Tempv =  "0.0";
            Tempv2 =  "0.0";
        }
        else{
            Float Temp = Float.parseFloat(Tempv);
            Float Temp2 = Float.parseFloat(Tempv2);
            if(rbutt_1.isChecked()){
                Temp = calc.dividebyten(Temp,Temp2);
            }
            else if(rbutt_2.isChecked()){
                Temp = calc.dividebyfive(Temp,Temp2);
            }
            else if(rbutt_3.isChecked()){
                Temp = calc.dividebyfour(Temp,Temp2);
            }
            else if(rbutt_4.isChecked()){
                Temp = calc.dividebythree(Temp,Temp2);
            }
            else{
                Toast.makeText( this, "Select a radio button!",Toast.LENGTH_LONG).show();

                Temp = 0.0f;
            }
            tv_1.setText(new Float(Temp).toString());

        }

    }
}