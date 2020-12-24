package com.example.tracnghiem1.cauhoi.view.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tracnghiem1.Main_App.View.fragment.Fragment_kiemtra;
import com.example.tracnghiem1.R;
import com.example.tracnghiem1.cauhoi.model.checkketqua;
import com.example.tracnghiem1.cauhoi.view.Activity.Cauhoi_slidesmain_Activity;

import java.util.ArrayList;

public class Fragment_Thi_slide extends Fragment {

    private View view;
    private Fragment_kiemtra fragment_kiemtra;
    public static final String KEY_PAGE = "page";
    public static final String KEY_CHECKANSWER = "checkAnswer";

    private static int checkAns;   // biến kiểm tra ...
    private static int numpage;


    private static Cauhoi_slidesmain_Activity activitys;
    private static String ketquacau;
    public static ArrayList<checkketqua> arr_ketqualam;


    // textview , radbutton
    TextView tvNum, tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC;

    // int dem ket qua dung
    private static int demkq = 0;


    public Fragment_Thi_slide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__thi_slide, container, false);
        // Inflate the layout for this fragment


        // getnumpage
        final Cauhoi_slidesmain_Activity activitys = (Cauhoi_slidesmain_Activity) getActivity();
        numpage = getArguments().getInt(KEY_PAGE);
        checkAns = getArguments().getInt(KEY_CHECKANSWER);

        // initt();


        inits();

        //

        // This overrides the radiogroup onCheckListener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
//                // This will get the radiobutton that has changed in its check state
//               if(checkedId == 2131558555)
//               {
//                   ketquacau = "A";
//                 //  Toast.makeText(getActivity(), ketquacau, Toast.LENGTH_SHORT).show();
//               }
//               else if(checkedId == 2131558556)
//               {
//                   ketquacau = "B";
//                  // Toast.makeText(getActivity(), ketquacau, Toast.LENGTH_SHORT).show();
//               }
//               else if(checkedId == 2131558557)
//               {
//                   ketquacau = "C";
//                  // Toast.makeText(getActivity(), ketquacau, Toast.LENGTH_SHORT).show();
//               }

                if(activitys.num_ex ==1 || activitys.num_ex == 3)
                {
                    fragment_kiemtra.arr_ques1.get(numpage).choiceID = checkedId;
                    fragment_kiemtra.arr_ques1.get(numpage).setTraloi(getChoiceFromID(checkedId));

                }
                else
                if(activitys.num_ex ==2 || activitys.num_ex == 4)
                {
                    fragment_kiemtra.arr_ques2.get(numpage).choiceID = checkedId;
                    fragment_kiemtra.arr_ques2.get(numpage).setTraloi(getChoiceFromID(checkedId));

                }







            }

        });
        //   Log.d("mã đề" , "s :" +activitys.num_ex);



        return view;
    }

    // anhs xa
    private void inits()
    {
        // new Arr ket qua
        arr_ketqualam = new ArrayList<checkketqua>();

        tvNum = (TextView) view.findViewById(R.id.tvNum);
        tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        radA = (RadioButton) view.findViewById(R.id.radA);
        radB = (RadioButton) view.findViewById(R.id.radB);
        radC = (RadioButton) view.findViewById(R.id.radC);
        radioGroup = (RadioGroup) view.findViewById(R.id.radGroup);

    }


//    public question getItem1(int posotion){
//        return fragment_kiemtra.arr_ques1.get(posotion);
//    }
//    public question getItem2(int posotion){
//        return fragment_kiemtra.arr_ques1.get(posotion);
//    }



    //Lấy giá trị (vị trí) radiogroup chuyển thành đáp án A/B/C/D
    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";

        } else return "";
    }



    public static Fragment_Thi_slide create(int numPage, int checkAnswer)
    {
        Fragment_Thi_slide fragment = new Fragment_Thi_slide();
        Bundle args = new Bundle();
        args.putInt(KEY_PAGE, numPage);
        args.putInt(KEY_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(activitys.num_ex ==1 || activitys.num_ex==3)
        {
            tvNum.setText("Đề "+activitys.num_ex+"-Câu " + (numpage + 1));
            tvQuestion.setText(fragment_kiemtra.arr_ques1.get(numpage).getQuestion());

            radA.setText(fragment_kiemtra.arr_ques1.get(numpage).getAnsA());
            radB.setText(fragment_kiemtra.arr_ques1.get(numpage).getAnsB());
            radC.setText(fragment_kiemtra.arr_ques1.get(numpage).getAnsC());



        }
        else
        if(activitys.num_ex ==2 || activitys.num_ex==4)
        {
            tvNum.setText("Đề "+activitys.num_ex+"-Câu " + (numpage + 1));
            tvQuestion.setText(fragment_kiemtra.arr_ques2.get(numpage).getQuestion());

            radA.setText(fragment_kiemtra.arr_ques2.get(numpage).getAnsA());
            radB.setText(fragment_kiemtra.arr_ques2.get(numpage).getAnsB());
            radC.setText(fragment_kiemtra.arr_ques2.get(numpage).getAnsC());
        }

// check đáp án

        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            getCheckAns(fragment_kiemtra.arr_ques1.get(numpage).getResult().toString());
        }




    }

    //Hàm kiểm tra câu đúng, nếu câu đúng thì đổi màu background radiobutton tương ứng
    private void getCheckAns(String ans){
        if(ans.equals("A")==true){
            radA.setBackgroundColor(Color.GREEN);
        }
        else if(ans.equals("B")==true){
            radB.setBackgroundColor(Color.GREEN);
        }else if(ans.equals("C")==true){
            radC.setBackgroundColor(Color.GREEN);

        }else ;
    }








}

