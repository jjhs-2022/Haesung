package com.jhspt.recycleproj.activity.schedule;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jhspt.recycleproj.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class plan extends AppCompatActivity {
    public GregorianCalendar cal_month, cal_month_copy;
    private HwAdapter hwAdapter;
    private TextView tv_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);


        HomeCollection.date_collection_arr=new ArrayList<HomeCollection>();
        /*
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-08-21" ,"","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-08-15" ,"Holi","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-07-08" ,"Statehood Day","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-08-08" ,"Republic Unian","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-07-09" ,"ABC","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-06-15" ,"demo","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-09-26" ,"weekly off","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-08" ,"Events","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-16" ,"Dasahara","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-02-09" ,"Christmas","Holiday","this is holiday"));
*/
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-08-21", "1?????????(3???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-08-22", "1?????????(3???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-08-23", "????????????(3???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-08-24", "1?????????(3???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-08-27", "????????? ?????? ??????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-05", "??????????????????(1,2,3???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-18", "??????????????????(1???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-19", "??????????????????(2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-20", "??????????????????(3???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-24", "?????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-25", "????????? ??????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-09-26", "????????? ????????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-03", "?????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-08", "???????????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-09", "?????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-10", "1?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-11", "1?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-12", "1?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-16", "??????????????????(1???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-17", "??????????????????(1???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-18", "??????????????????(1???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-10-19", "??????????????????(1???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-11-09", "?????? ?????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-11-15", "??????????????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-11-21", "??????????????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-11-23", "????????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-12-11", "2?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-12-12", "2?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-12-13", "2?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-12-14", "2?????????(1,2???)"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-12-25", "???????????????"));
        HomeCollection.date_collection_arr.add(new HomeCollection("2018-12-28", "??????"));








        Toolbar mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        hwAdapter = new HwAdapter(this, cal_month,HomeCollection.date_collection_arr);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));

        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setPreviousMonth();
                refreshCalendar();
                /*
                if (cal_month.get(GregorianCalendar.MONTH) == 4&&cal_month.get(GregorianCalendar.YEAR)==2017) {
                    cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                    // Toast.makeText(plan.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setPreviousMonth();
                    refreshCalendar();
                }*/


            }
        });
        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNextMonth();
                refreshCalendar();
                /*
                if (cal_month.get(GregorianCalendar.MONTH) == 5&&cal_month.get(GregorianCalendar.YEAR)==2018) {
                    // cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                    // Toast.makeText(plan.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setNextMonth();
                    refreshCalendar();
                }
                */
            }

        });
        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(hwAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedGridDate = HwAdapter.day_string.get(position);
                ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, plan.this);
            }

        });
    }
    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        hwAdapter.refreshDays();
        hwAdapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
    }
}
