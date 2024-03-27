package com.example.doan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {


    private EditText txtUser;
    private EditText txtMail;
    private EditText txtPassword;
    private EditText txtPassword2;

    private Button btnRegister;
    private TextView lblLogin;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        txtUser = findViewById(R.id.txtUser);
        txtMail = findViewById(R.id.txtMail);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);
        lblLogin = findViewById(R.id.lblLogin);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(view -> {
            createuser();
        });


        lblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

    }//End onCreate



    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }// End openLoginActivity

    public void createuser(){

        String name = txtUser.getText().toString();
        String mail = txtMail.getText().toString();
        String password2 = txtPassword2.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            txtUser.setError("Nhập Tên");
            txtUser.requestFocus();
        }else if (TextUtils.isEmpty(mail)){
            txtMail.setError("Nhập Email");
            txtMail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            txtPassword.setError("Nhập Mật Khẩu");
            txtPassword.requestFocus();
        }else if (TextUtils.isEmpty(password2)){txtPassword2.setError("Xác Nhận Mật Khẩu");
            txtPassword2.requestFocus();
        } else if (!password.equals(password2)) {
            txtPassword.setError("Mật Khẩu không khớp");
            txtPassword.requestFocus();
            txtPassword2.setError("Mật Khẩu không khớp");
            txtPassword2.requestFocus();
        }else {

            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userID);

                        Map<String,Object> user=new HashMap<>();
                        user.put("Name", name);
                        user.put("Email", mail);
                        user.put("Password", password);
                        user.put("Confirm Password", password2);
                        user.put("role","user");
//

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSuccess: Dữ liệu được ghi"+userID);
                            }
                        });
                        Toast.makeText(SignupActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }else {
                        Toast.makeText(SignupActivity.this, "Dang ki that bai"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

}// End RegisterActivity