package com.example.tracnghiem1.Main_App.model;

public class topbxh {
    String name;
    String score;
    String email;

    public topbxh(){}

    public topbxh(String name, String score, String email){
        this.name=name;
        this.score=score;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "topbxh{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
