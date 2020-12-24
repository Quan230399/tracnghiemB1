package com.example.tracnghiem1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.tracnghiem1.Login.View.Activity.Login;
import com.example.tracnghiem1.Main_App.View.Activity.Mainc_Activity;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}