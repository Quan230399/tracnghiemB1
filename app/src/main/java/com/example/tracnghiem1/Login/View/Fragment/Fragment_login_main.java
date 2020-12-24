package com.example.tracnghiem1.Login.View.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem1.Login.Model.checkAcc_model;
import com.example.tracnghiem1.Login.Model.url_sever;
import com.example.tracnghiem1.Login.Presenter.CustomToast;
import com.example.tracnghiem1.Login.Presenter.Untils;
import com.example.tracnghiem1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_login_main extends Fragment implements View.OnClickListener {
    private static View view;

    private static EditText userlogin, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    ArrayList<checkAcc_model> checkAcc_array;
    //
    String data = "";
    private RequestQueue requestQueue;
    // get id của acc check để truyền qua màn hình khác
    public static String id_user;

    // interface
    public interface Login {
        void success(String user);

        void fail(String user, String pass);
    }

    Login loginListener;

    public Fragment_login_main() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_main, container, false);
        Anhxa();
        // load acc
        GetAccFromJson(url_sever.GetAll_acc);
        Action();
        return view;
    }

    private void Action() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {
                        if (isChecked) {
                            show_hide_password.setText("Ẩn mật khẩu");
                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());
                        } else {
                            show_hide_password.setText("Hiện mật khẩu");
                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());
                        }
                    }
                });
    }

    private void Anhxa() {
        checkAcc_array = new ArrayList<checkAcc_model>();
        fragmentManager = getActivity().getSupportFragmentManager();

        userlogin = (EditText) view.findViewById(R.id.login_user);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);

        } catch (Exception e) {
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;
            case R.id.forgot_password:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Fragment_forgotpass(),
                                Untils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new Fragment_signup(),
                                Untils.SignUp_Fragment).commit();
                break;

        }
    }

    private void checkValidation() {
        String getuserlId = userlogin.getText().toString();
        String getPassword = password.getText().toString();

        if (getuserlId.equals("") || getuserlId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "bạn chưa nhập email và mật khẩu");
        }

        // nếu đúng mật khẩu
        int dem = 0;
        for (int i = 0; i < checkAcc_array.size(); i++) {
            if (getuserlId.equals(checkAcc_array.get(i).getUsername()) && getPassword.equals(checkAcc_array.get(i).getPass())) {
                dem = i;
            }
        }
        if (dem != 0) {
            loginListener.success(checkAcc_array.get(dem).getId());
            id_user = checkAcc_array.get(dem).getId();
            Toast.makeText(getActivity(), "hello" + id_user, Toast.LENGTH_SHORT).show();
        } else {
            loginListener.fail(getuserlId, getPassword);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        loginListener = (Login) activity;
    }

    private void GetAccFromJson(String url) {
        requestQueue = Volley.newRequestQueue(getActivity());
        // Casts results into the TextView found within the main layout XML with id jsonData
        JsonObjectRequest obreg = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.d("response", response.toString());

                        try {
                            JSONArray jsarray = response.getJSONArray("taikhoan");
                            for (int i = 0; i < jsarray.length(); i++) {
                                JSONObject objitem = jsarray.getJSONObject(i);

                                String id = objitem.getString("id");
                                String user = objitem.getString("user");
                                String pass = objitem.getString("pass");
                                checkAcc_array.add(new checkAcc_model(id, user, pass));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userlg", userlogin.getText().toString().trim());
                params.put("passlg", password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(obreg);
    }
}
