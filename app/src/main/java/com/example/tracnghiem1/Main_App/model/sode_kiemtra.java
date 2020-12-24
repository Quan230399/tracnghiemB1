package com.example.tracnghiem1.Main_App.model;

public class sode_kiemtra {
    public  String de;

    public  sode_kiemtra (String de){
        this.de=de;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    @Override
    public String toString() {
        return "sode_kiemtra{" +
                "de='" + de + '\'' +
                '}';
    }
}
