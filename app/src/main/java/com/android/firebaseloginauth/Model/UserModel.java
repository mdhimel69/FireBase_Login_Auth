package com.android.firebaseloginauth.Model;

public class UserModel {
    private String name;
    private String gender;
    private String email;
    private String pass;

    public UserModel() {
    }

    public UserModel(String name, String gender, String email, String pass) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
