package com.example.tracnghiem1.Login.Model;

public class checkAcc_model {
    String id;
    String username;
    String pass;

    public checkAcc_model() {
    }

    public checkAcc_model(String id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public String getId(){return id;}
    public void setId(){this.id= id;}
    public String getUsername(){return username;}
    public void setUsername(){this.username= username;}
    public  String getPass(){return pass;}
    public void setPass(){this.pass= pass;}

    @Override
    public String toString(){return id+"-"+username+"-"+pass;}

}
