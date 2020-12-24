package com.example.tracnghiem1.Login.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tracnghiem1.Login.Model.getIdBundle;
import com.example.tracnghiem1.Login.Presenter.Untils;
import com.example.tracnghiem1.Login.View.Fragment.Fragment_login_main;
import com.example.tracnghiem1.Main_App.View.Activity.Mainc_Activity;
import com.example.tracnghiem1.R;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements Fragment_login_main.Login {

    private static FragmentManager fragmentManager;
    private static View view;
    public static ArrayList<getIdBundle> string_id_sendall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();

        // commit fragment login (nếu rỗng thì trả về fragment main login)
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Fragment_login_main(),
                            Untils.Login_Fragment).commit();
        }
    }

    // trạại lại login
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Fragment_login_main(),
                        Untils.Login_Fragment).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment SignUp_Fragment = fragmentManager
                .findFragmentByTag(Untils.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(Untils.ForgotPassword_Fragment);
        Fragment Login_Fragment = fragmentManager
                .findFragmentByTag(Untils.Login_Fragment);
        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else {

            //Khoi tao lai Activity main
//            Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
//            startActivity(intent);
            // kết thúc app
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();
        }
    }


    @Override
    public void success(String id) {
        //   startActivity(new Intent(this,Main2Activity.class));
        Toast.makeText(this,"xin chào :" +id, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), Mainc_Activity.class);
//        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void fail(String user, String pass) {
        Toast.makeText(this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
    }
}