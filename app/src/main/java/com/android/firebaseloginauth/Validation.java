package com.android.firebaseloginauth;

import android.util.Patterns;

public class Validation {

    public static boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        // Example: password must be at least 6 characters long
        return password != null && password.length() >= 6;
    }
}
