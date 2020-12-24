package com.example.tracnghiem1.Main_App.View.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem1.Login.Model.url_sever;
import com.example.tracnghiem1.Main_App.model.sode_kiemtra;
import com.example.tracnghiem1.Main_App.presenter.kiemtra_grid_adapter;
import com.example.tracnghiem1.R;
import com.example.tracnghiem1.cauhoi.model.question;
import com.example.tracnghiem1.cauhoi.view.Activity.Cauhoi_slidesmain_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_kiemtra extends Fragment implements AdapterView.OnItemClickListener {
    com.example.tracnghiem1.Main_App.presenter.kiemtra_grid_adapter kiemtra_grid_adapter;
    GridView gvExam;
    ArrayList<sode_kiemtra> arr_sode;
    View view;
    int made;

    //
    private static RequestQueue requestQueue;
    // Array mang cau hoi num_ =1
    public static ArrayList<question> arr_ques1;
    //    // Array mang cau hoi num_ =1
    public static ArrayList<question> arr_ques2;


    public Fragment_kiemtra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_kiemtra, container, false);
        // Inflate the layout for this fragment
        //   ((Mainc_Activity) getActivity()).getSupportActionBar().setTitle("Test title");

        arr_sode = new ArrayList<>();

        arr_ques1 = new ArrayList<question>();
        arr_ques2 = new ArrayList<question>();
        // set Gridview
        gvExam = (GridView) view.findViewById(R.id.gridv_kiemtra);
        arr_sode.add(new sode_kiemtra("Đề thi 1"));
        arr_sode.add(new sode_kiemtra("Đề thi 2"));
        arr_sode.add(new sode_kiemtra("Đề thi 3"));
        arr_sode.add(new sode_kiemtra("Đề thi 4"));

        kiemtra_grid_adapter = new kiemtra_grid_adapter(getActivity(), arr_sode);
        gvExam.setAdapter(kiemtra_grid_adapter);


        // chay ngam ques 1
        GetQuestion1(url_sever.loadques1);

        // chay ngam ques 2
        GetQuestion2(url_sever.loadques2);


        //setClick

        gvExam.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        made = i + 1;
        cfdialogkiemtra();
    }


    private void cfdialogkiemtra() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        builder
                .setTitle("Thông báo")
                .setMessage("Bài kiểm tra này có 10 câu hỏi và 5 phút làm bài" + "\n" +
                        "Bạn muốn bắt đầu?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes-code
                        Intent intent1 = new Intent(getActivity(), Cauhoi_slidesmain_Activity.class);
                        intent1.putExtra("num_exam", made);
                        startActivity(intent1);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    // getQuestion1

    private void GetQuestion1(String url) {

        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(getActivity());
        // Casts results into the TextView found within the main layout XML with id jsonData
        JsonObjectRequest obreg = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());
                        try {

                            JSONArray jsarray = response.getJSONArray("cauhoi");


                            for (int i = 0; i < jsarray.length(); i++) {
                                JSONObject objitem = jsarray.getJSONObject(i);

                                String id = objitem.getString("id");
                                String question = objitem.getString("question");
                                String ansA = objitem.getString("ans_A");
                                String ansB = objitem.getString("ans_B");
                                String ansC = objitem.getString("ans_C");
                                String result = objitem.getString("result");
                                String num_exam = objitem.getString("num_exam");

                                arr_ques1.add(new question(id, question, ansA, ansB, ansC, result, num_exam, ""));

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
        );
        requestQueue.add(obreg);
    }

    // getQuestion2

    private void GetQuestion2(String url) {
        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(getActivity());
        // Casts results into the TextView found within the main layout XML with id jsonData

        JsonObjectRequest obreg = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response2", response.toString());
                        try {

                            JSONArray jsarray = response.getJSONArray("cauhoi");


                            for (int i = 0; i < jsarray.length(); i++) {
                                JSONObject objitem = jsarray.getJSONObject(i);

                                String id = objitem.getString("id");
                                String question = objitem.getString("question");
                                String ansA = objitem.getString("ans_A");
                                String ansB = objitem.getString("ans_B");
                                String ansC = objitem.getString("ans_C");
                                String result = objitem.getString("result");
                                String num_exam = objitem.getString("num_exam");

                                arr_ques2.add(new question(id, question, ansA, ansB, ansC, result, num_exam, ""));

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
        );

        requestQueue.add(obreg);

    }
}
