package com.example.foodwaste;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {


    TextView signin , registration;
    EditText email_reg , password_reg;
    Button reg_btn;

    ProgressDialog process_dialog;

    //Firebase Auth
    FirebaseAuth firebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signin = findViewById(R.id.signin);
        registration = findViewById(R.id.registration);
        email_reg = findViewById(R.id.email_reg);
        password_reg = findViewById(R.id.password_reg);
        reg_btn = findViewById(R.id.reg_btn);

        process_dialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        registration();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , Login.class);
                startActivity(i);
                finish();
            }
        });





    }


    private void registration() {
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_reg.getText().toString().trim();
                String password = password_reg.getText().toString().trim();

                process_dialog.setMessage("Processing");
                process_dialog.show();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email Required", Toast.LENGTH_SHORT).show();
                    process_dialog.dismiss();  // Dismiss the dialog if email is empty
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
                    process_dialog.dismiss();  // Dismiss the dialog if password is empty
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        process_dialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration COMPLETED", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                            finish();
                        } else {
                            if (task.getException() != null && task.getException().getMessage().contains("already in use")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                builder.setTitle("Alert !!!");
                                builder.setMessage("User already exists");

                                // Set positive button
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Handle positive button click
                                        Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
        finish();
    }
}