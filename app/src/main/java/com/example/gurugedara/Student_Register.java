package com.example.gurugedara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Student_Register extends AppCompatActivity {
    EditText student_name , student_email , student_phone , student_user_name , student_password , student_confirm_password;
    Button btn_register_student , btn_login_register_ui_student ;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    boolean valid = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        student_name = findViewById(R.id.et_name_student);
        student_email = findViewById(R.id.et_email_student);
        student_phone = findViewById(R.id.et_phone_sudent);
        student_user_name = findViewById(R.id.et_reg_username_student);
        student_password = findViewById(R.id.et_reg_password_student);
        student_confirm_password = findViewById(R.id.et_reg_confirm_password_student);
        btn_register_student = findViewById(R.id.btn_register_student);
        btn_login_register_ui_student = findViewById(R.id.btn_login_register_ui_student);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        btn_login_register_ui_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Student_Login.class));
                finish();
            }
        });

        btn_register_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(student_name);
                checkField(student_email);
                checkField(student_phone);
                checkField(student_user_name);
                checkField(student_password);
                checkField(student_confirm_password);




                if(valid){
                    //start the registration process
                    fAuth.createUserWithEmailAndPassword(student_email.getText().toString() , student_password
                            .getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(Student_Register.this , "Account Created" , Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Students").document(user.getUid());
                            Map<String,Object> studentInfo = new HashMap<>();
                            studentInfo.put("Student Name" , student_name.getText().toString());
                            studentInfo.put("Student Email" , student_email.getText().toString());
                            studentInfo.put("Student Phone" , student_phone.getText().toString());
                            studentInfo.put("Student UserName" , student_user_name.getText().toString());
                            studentInfo.put("Student Password" , student_password.getText().toString());

                            df.set(studentInfo);

                            // send student to next page
                            startActivity(new Intent(getApplicationContext() , Menu_Student.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            Toast.makeText(Student_Register.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false ;
        } else{
            valid = true;
        }

        return valid;
    }
}