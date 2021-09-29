package com.example.gurugedara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Teacher_Login extends AppCompatActivity {
    Button btn_register_login_ui_teacher , btn_login_teacher;
    EditText email , password ;
    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        firebaseAuth = FirebaseAuth.getInstance();

        btn_register_login_ui_teacher = findViewById(R.id.btn_register_login_ui_teacher);
        btn_register_login_ui_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Login.this , Teacher_Register.class));
            }
        });
        email = findViewById(R.id.et_login_email_teacher);
        password = findViewById(R.id.et_password_teacher);
        btn_login_teacher = findViewById(R.id.btn_login_teacher);

        btn_login_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()){
                    email.setError("Email is Missing ");
                    return;
                }
                if(password.getText().toString().isEmpty()) {
                    password.setError("Password is Missing");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Teacher_Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext() , Menu_Teacher.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Teacher_Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext() , Menu_Teacher.class));
            finish();
        }
    }
}