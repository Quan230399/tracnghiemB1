package com.example.tracnghiem1.cauhoi.view.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem1.Login.Model.url_sever;
import com.example.tracnghiem1.Main_App.View.Activity.Mainc_Activity;
import com.example.tracnghiem1.Main_App.View.fragment.Fragment_kiemtra;
import com.example.tracnghiem1.R;

import java.util.HashMap;
import java.util.Map;

public class ShowDiem_Activity extends AppCompatActivity {

    private Fragment_kiemtra fragment_kiemtra;
    private Cauhoi_slidesmain_Activity activitys;

    int numNoAns=0;
    private static     int numTrue=0;
    int numFalse=0;
    private static  int totalScore=0;

    TextView tvTrue, tvTotalScore ,tvxetloai;
    Button btnSaveScore, btnAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diem_);
        // ánh xạ

        innit();

        // checkkq
        checkResult();
        tvTrue.setText(""+numTrue);
        totalScore = numTrue *10;
        tvTotalScore.setText(""+totalScore+"/100");

        if(totalScore >=50)
        {
            tvxetloai.setTextColor(0xFF00FF5D);
            tvxetloai.setText("PASSED");


        }
        else if(totalScore <50)
        {    tvxetloai.setTextColor(0xFF690F16);
            tvxetloai.setText("FAILED");


        }

        // làm lại
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Mainc_Activity.class));
            }
        });

        // chia sẻ điểm
        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareScore();

            }
        });
    }


    @Override
    public void onBackPressed() {
        dialogExit();
    }


    // dialog lưu điểm


    private void ShareScore()
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(ShowDiem_Activity.this,R.style.AlertDialogStyle);
        LayoutInflater inflater=ShowDiem_Activity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.save_score_dialog,null);
        builder.setView(view);

        final EditText edtName= (EditText) view.findViewById(R.id.edtName);
        final EditText edtMail= (EditText) view.findViewById(R.id.edtmail);
        edtName.setText(Mainc_Activity.arrbundelget.get(0).getUser());
        edtMail.setText(Mainc_Activity.arrbundelget.get(0).getEmail());
        TextView tvScore= (TextView) view.findViewById(R.id.tvScore);

        tvScore.setText(totalScore+" điểm");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // lưu điểm vào database onl
                addscoretosever(url_sever.addscoretosevr);


                // Toast.makeText(ShowDiem_Activity.this, "Lưu điểm thành công!",Toast.LENGTH_LONG).show();
                finish();
                dialog.dismiss();

                startActivity(new Intent(getApplicationContext(),Mainc_Activity.class));
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog b= builder.create();
        b.show();
    }

    private void addscoretosever(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success"))
                {
                    Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Lỗi lưu", Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("lỗi thêm" , error.toString());
                        Toast.makeText(getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();

                    }
                })
                //
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();

                params.put("nameuu",Mainc_Activity.arrbundelget.get(0).getUser().trim());
                params.put("scoreu",String.valueOf(totalScore).trim());
                params.put("mail",Mainc_Activity.arrbundelget.get(0).getEmail().trim());


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }




    // confilm exit
    public void dialogExit(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(ShowDiem_Activity.this,R.style.AlertDialogStyle);
        // builder.setIcon(R.drawable.exit);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), Mainc_Activity.class));

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }

    private void innit() {
        tvTrue= (TextView)findViewById(R.id.tvTrue);
        tvTotalScore= (TextView)findViewById(R.id.tvTotalPoint);
        btnAgain=(Button)findViewById(R.id.btnAgain);
        btnSaveScore=(Button)findViewById(R.id.btnSaveScore);
        tvxetloai = (TextView) findViewById(R.id.tvrotdau);
    }


    //PT Check kết quả
    public void checkResult(){

        if(activitys.num_ex ==1 || activitys.num_ex==3)
        {
            for(int i=0; i< fragment_kiemtra.arr_ques1.size(); i++){
                if(fragment_kiemtra.arr_ques1.get(i).getResult().equals(fragment_kiemtra.arr_ques1.get(i).getTraloi())==true){
                    numTrue++;

                }
            }
        }

        else   if(activitys.num_ex ==2 || activitys.num_ex==4)
        {
            for(int i=0; i< fragment_kiemtra.arr_ques2.size(); i++){
                if(fragment_kiemtra.arr_ques2.get(i).getResult().equals(fragment_kiemtra.arr_ques2.get(i).getTraloi())==true){
                    numTrue++;

                }
            }
        }





    }
}
