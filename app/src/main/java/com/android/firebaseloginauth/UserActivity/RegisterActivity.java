package com.android.firebaseloginauth.UserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.firebaseloginauth.R;
import com.android.firebaseloginauth.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.android.firebaseloginauth.Model.UserModel;

public class RegisterActivity extends AppCompatActivity {

    EditText editName, editGender, editEmail, editPass;
    Button signUpButton, buttonSignIn;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editGender = findViewById(R.id.editGender);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        signUpButton = findViewById(R.id.signUpButton);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        buttonSignIn.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
            finish();
        });


        signUpButton.setOnClickListener(view -> {
            String name = editName.getText().toString().trim();
            String gen = editGender.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String pass = editPass.getText().toString().trim();

            if (TextUtils.isEmpty(email) || !Validation.isValidEmail(email)) {
                Toast.makeText(RegisterActivity.this, "invalid email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pass) || !Validation.isValidPassword(pass)) {
                Toast.makeText(RegisterActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                String userId = firebaseUser.getUid();
                                UserModel user = new UserModel(name, gen, email, pass);
                                mDatabase.child("users").child(userId).setValue(user);


                                Toast.makeText(RegisterActivity.this, "User created Succesfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Please check email/password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        });

    }
}