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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Teacher_Register extends AppCompatActivity {
    EditText teacher_name , teacher_email , teacher_phone , teacher_user_name , teacher_password , teacher_confirm_password;
    Button btn_register_teacher , btn_login_register_ui_teacher ;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    boolean valid = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        teacher_name = findViewById(R.id.et_name_teacher);
        teacher_email = findViewById(R.id.et_email_teacher);
        teacher_phone = findViewById(R.id.et_phone_teacher);
        teacher_user_name = findViewById(R.id.et_reg_username_teacher);
        teacher_password = findViewById(R.id.et_reg_password_teacher);
        teacher_confirm_password = findViewById(R.id.et_reg_confirm_password_teacher);
        btn_register_teacher = findViewById(R.id.btn_register_teacher);
        btn_login_register_ui_teacher = findViewById(R.id.btn_login_register_ui_teacher);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btn_login_register_ui_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Teacher_Login.class));
                finish();
            }
        });

        btn_register_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(teacher_name);
                checkField(teacher_email);
                checkField(teacher_phone);
                checkField(teacher_user_name);
                checkField(teacher_password);
                checkField(teacher_confirm_password);




                if(valid){
                    //start the registration process
                    fAuth.createUserWithEmailAndPassword(teacher_email.getText().toString() , teacher_password
                            .getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user2 = fAuth.getCurrentUser();
                            Toast.makeText(Teacher_Register.this , "Account Created" , Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Teachers").document(user2.getUid());
                            Map<String,Object> teacherInfo = new HashMap<>();
                            teacherInfo.put("Teacher Name" , teacher_name.getText().toString());
                            teacherInfo.put("Teacher Email" , teacher_email.getText().toString());
                            teacherInfo.put("Teacher Phone" , teacher_phone.getText().toString());
                            teacherInfo.put("Teacher UserName" , teacher_user_name.getText().toString());
                            teacherInfo.put("Teacher Password" , teacher_password.getText().toString());

                            df.set(teacherInfo);

                            // send student to next page
                            startActivity(new Intent(getApplicationContext() , Menu_Teacher.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Teacher_Register.this , e.getMessage() , Toast.LENGTH_SHORT).show();
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