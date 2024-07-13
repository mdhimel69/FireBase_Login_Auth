package com.android.firebaseloginauth.UserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.firebaseloginauth.MainActivity;
import com.android.firebaseloginauth.R;
import com.android.firebaseloginauth.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    Button signInButton, buttonSignUp;
    EditText signEmail, signPass;
    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(SignInActivity.this, "User SignIn Succesfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton = findViewById(R.id.signInButton);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        signEmail = findViewById(R.id.signEmail);
        signPass = findViewById(R.id.signPass);
        mAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
            finish();
        });

        signInButton.setOnClickListener(view -> {
            String email = signEmail.getText().toString().trim();
            String pass = signPass.getText().toString().trim();

            if (TextUtils.isEmpty(email) || !Validation.isValidEmail(email)) {
                Toast.makeText(SignInActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(pass) || !Validation.isValidPassword(pass)) {
                Toast.makeText(SignInActivity.this, "Invalid password", Toast.LENGTH_SHORT). show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignInActivity.this, "User SignIn Succesfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Please check email/password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });

    }
}