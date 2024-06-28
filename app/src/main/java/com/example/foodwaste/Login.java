package com.example.foodwaste;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email_login , password_login;
    TextView login , forget_pswd , signup;
    Button login_btn;
    ProgressDialog login_progress;

    // firebase auth
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        login = findViewById(R.id.login);
        forget_pswd = findViewById(R.id.forget_pswd);
        signup = findViewById(R.id.signup);
        login_btn = findViewById(R.id.login_btn);

        login_progress = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        email_login.setText("");
        password_login.setText("");


        login();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Signup.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });

        frgt_pswd();

    }




    private void login(){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_login.getText().toString().trim();
                String password = password_login.getText().toString().trim();



                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Email required", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
                }

                else{
                    login_progress.setMessage("Processing......");
                    login_progress.show();
                    firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                login_progress.dismiss();
                                Intent i  = new Intent(getApplicationContext() , MainActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                login_progress.dismiss();
                            }
                        }
                    });}
            }
        });
    }

    private void frgt_pswd(){
        forget_pswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_login.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "enter email", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setMessage("Reset password link has been sent to email");

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setMessage("error occured while sending email");

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }


            }

        });}


    @Override
    protected void onResume() {
        super.onResume();
        // Clear the text fields when returning to MainActivity
        email_login.setText("");
        password_login.setText("");
    }


}