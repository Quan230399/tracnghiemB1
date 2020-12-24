package com.example.tracnghiem1.cauhoi.view.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tracnghiem1.Main_App.View.fragment.Fragment_kiemtra;
import com.example.tracnghiem1.R;
import com.example.tracnghiem1.cauhoi.presenter.checkAnswerAdapter;
import com.example.tracnghiem1.cauhoi.view.Fragment.Fragment_Thi_slide;

import java.util.concurrent.TimeUnit;

public class Cauhoi_slidesmain_Activity extends AppCompatActivity {

    //
    public static   int num_ex;
    public int checkAns = 0;
    TextView tvKiemtra, tvTimer, tvXemDiem;

    // dem nguoc thoi gian

    private int timetotal ;
    Timer countDownTimer;

    private static checkAnswerAdapter answerAdapter;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 10;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nhận bundle key từ fragment_kiemtra
        num_ex  = getIntent().getExtras().getInt("num_exam");
        Toast.makeText(this, "nhận key :" +num_ex, Toast.LENGTH_SHORT).show();
        //
        setContentView(R.layout.activity_cauhoi_slidesmain_);
        //
        // add fragment cauhoi
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        //  mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        tvKiemtra = (TextView) findViewById(R.id.tvKiemTra);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvXemDiem = (TextView) findViewById(R.id.tvScore);

        tvKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();

            }
        });
        tvXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.onFinish();
                startActivity(new Intent(getApplicationContext(),ShowDiem_Activity.class));
            }
        });

        // dem nguoc dong ho
        timetotal = 5 ;
        countDownTimer = new Timer(timetotal*60*1000,1000);
        countDownTimer.start();

    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            dialogExit();

        }
        else if(checkAns!=0)
        {
            dialogExit();
        }

        else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            return  Fragment_Thi_slide.create(position,checkAns);


        }

        @Override
        public int getCount() {

            return NUM_PAGES;
        }


    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    // confilm exit
    public void dialogExit(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(Cauhoi_slidesmain_Activity.this);
        // builder.setIcon(R.drawable.exit);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }

    //Dialog hiện thị danh sách những câu trả lời và chưa trả lời...
    public void checkAnswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_ans_dialog);
        dialog.setTitle("Xem lại câu hỏi");

        if(num_ex ==1 || num_ex ==3)
        {
            answerAdapter = new checkAnswerAdapter(Fragment_kiemtra.arr_ques1, this);
        }
        else if(num_ex ==2 || num_ex ==4)
        {
            answerAdapter = new checkAnswerAdapter(Fragment_kiemtra.arr_ques2, this);
        }

        GridView gvLsQuestion = (GridView) dialog.findViewById(R.id.gvLsQuestion);
        gvLsQuestion.setAdapter(answerAdapter);

        gvLsQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });

        Button btnCancle, btnFinish;
        btnCancle = (Button) dialog.findViewById(R.id.btnCancle);
        btnFinish = (Button) dialog.findViewById(R.id.btnFinish);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////
                //  timer.cancel();

                countDownTimer.cancel();
                checkAns = 1;
                result();


                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void result() {

        if (mPager.getCurrentItem() >= 4) mPager.setCurrentItem(mPager.getCurrentItem() - 2);
        else if (mPager.getCurrentItem() <= 4) mPager.setCurrentItem(mPager.getCurrentItem() + 2);
        tvXemDiem.setVisibility(View.VISIBLE);
        tvKiemtra.setVisibility(View.GONE);
    }

    public class Timer extends CountDownTimer
    {

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            String downtimecount = String .format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l)-TimeUnit.MINUTES.toSeconds( TimeUnit.MILLISECONDS.toMinutes(l)));
            tvTimer.setText(downtimecount);


        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");
            // Toast.makeText(Cauhoi_slidesmain_Activity.this, "Bạn đã hết giờ làm bài!", Toast.LENGTH_SHORT).show();
            //  result();

        }
    }



}
