package com.example.tracnghiem1.cauhoi.model;

public class checkketqua {
    int loca_numpage;
    String resofmechose;


    public checkketqua() {
    }

    public checkketqua(int loca_numpage, String resofmechose) {
        this.loca_numpage = loca_numpage;
        this.resofmechose = resofmechose;
    }

    public int getLoca_numpage() {
        return loca_numpage;
    }

    public void setLoca_numpage(int loca_numpage) {
        this.loca_numpage = loca_numpage;
    }

    public String getResofmechose() {
        return resofmechose;
    }

    public void setResofmechose(String resofmechose) {
        this.resofmechose = resofmechose;
    }

    @Override
    public String toString() {
        return loca_numpage+"-"+resofmechose;
    }
}
