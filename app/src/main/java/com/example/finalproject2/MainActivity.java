package com.example.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    /* this is for Firebase.*/
    private FirebaseAuth mAuth;
    /* this is for the gameactivity.*/
    private Intent intent;
    /* this is for the edittext.*/
    EditText foremail;
    /* this is for another edittext.*/
    EditText forpassword;
    /* this is for the checkbox.*/
    CheckBox showpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foremail = findViewById(R.id.email);
        forpassword = findViewById(R.id.password);
        showpassword = findViewById(R.id.showpassword);
        intent = new Intent(this, NextActivity.class);
        Button signup = findViewById(R.id.signup);
        Button forlogin = findViewById(R.id.login);
        signup.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTosignup();
            }
        });
        forlogin.setOnClickListener(v -> {
            mAuth.signInWithEmailAndPassword(foremail.getText().toString(), forpassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                intent.putExtra("email", mAuth.getCurrentUser().getEmail());
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        });

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    forpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    forpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
    private void startTosignup() {
        foremail = findViewById(R.id.email);
        forpassword = findViewById(R.id.password);
        showpassword = findViewById(R.id.showpassword);
        mAuth.createUserWithEmailAndPassword(foremail.getText().toString(),
                forpassword.getText().toString()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().sendEmailVerification().
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                if(mAuth.getCurrentUser().isEmailVerified()) {
                                                    Toast.makeText(MainActivity.this,
                                                            "Current email has already signed up!",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                Toast.makeText(MainActivity.this,
                                                        "The link has been sent to your email!",
                                                        Toast.LENGTH_LONG).show();
                                                foremail.setText("");
                                                forpassword.setText("");
                                            } else {
                                                Toast.makeText(MainActivity.this,
                                                        task.getException().getMessage(),
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "AAA sorry!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}




