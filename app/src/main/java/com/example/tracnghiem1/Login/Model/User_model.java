package com.example.tracnghiem1.Login.Model;

public class User_model {

    String id;
    String user;
    String email;
    String phone;
    String pass;

    public  User_model(){}
    public User_model(String id, String user, String email, String phone, String pass){
        this.id=id;
        this.user= user;
        this.email=email;
        this.phone=phone;
        this.pass=pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user+"-"+pass;
    }
}

