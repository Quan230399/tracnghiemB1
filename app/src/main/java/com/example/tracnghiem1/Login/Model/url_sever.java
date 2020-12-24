package com.example.tracnghiem1.Login.Model;

public class url_sever {

    public static final String httpt = "http://192.168.1.185:8080/";
    public static final String Signup_acc = httpt+"sever/Assigntment_/Login/addAcc.php";
    public  static final String GetAll_acc = httpt+"sever/Assigntment_/Login/getallAcc.php";
    public  static final String Get1_acc = httpt+"sever/Assigntment_/Login/get1Acc.php?id=";

    // changepass
    public static final String updatepass = httpt+"sever/Assigntment_/Login/updatepass.php";


    // load question
    public  static final String loadques1 = httpt+"sever/Assigntment_/Question/GetQs1.php";
    public  static final String loadques2 = httpt+"sever/Assigntment_/Question/GetQs2.php";


    // add score to sever
    public static final String addscoretosevr = httpt+"sever/Assigntment_/Top/addtop.php";
    public static final String getalltop = httpt+"sever/Assigntment_/Top/getalltop.php";

    // delete acc

    public static final String deleteacc =httpt+"sever/Assigntment_/Login/deleteacc.php";
}
