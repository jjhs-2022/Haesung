package com.jhspt.recycleproj.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jhspt.recycleproj.activity.schedule.plan;
import com.jhspt.recycleproj.activity.suggestion.SuggestionActivity;
import com.jhspt.recycleproj.tool.TimeCount;
import com.jhspt.recycleproj.tool.BapTool;
import com.jhspt.recycleproj.tool.Preference;
import com.jhspt.recycleproj.tool.TimeTableTool;
import com.jhspt.recycleproj.R;
import com.jhspt.recycleproj.activity.bap.BapActivity;
import com.jhspt.recycleproj.activity.exam.ExamTimeActivity;
import com.jhspt.recycleproj.activity.notice.NoticeActivity;
import com.jhspt.recycleproj.activity.timetable.TimeTableActivity;
import com.jhspt.recycleproj.tool.RecyclerItemClickListener;

import static com.jhspt.recycleproj.tool.TimeCount.countdday;

/**
 * Created by whdghks913 on 2015-11-30.
 */
public class MainFragment extends Fragment {


    public static Fragment getInstance(int code) {
        MainFragment mFragment = new MainFragment();

        Bundle args = new Bundle();
        args.putInt("code", code);
        mFragment.setArguments(args);

        return mFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        final MainAdapter mAdapter = new MainAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);

        // Fragment를 클릭했을 때
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View mView, int position) {
                //boolean isSimple = mAdapter.getItemData(position).isSimple;

                Bundle args = getArguments();
                Preference mPref = new Preference(getActivity());
                int code = args.getInt("code");


                if (code == 1) {
                    if (mPref.getBoolean("simpleShowDDay", true)) {
                        switch (position) {
                            case 1:
                                startActivity(new Intent(getActivity(), BapActivity.class));
                                break;
                            case 2:
                                startActivity(new Intent(getActivity(), TimeTableActivity.class));
                                break;
                        }
                    } else {
                        switch (position) {


                            case 0:
                                startActivity(new Intent(getActivity(), BapActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(getActivity(), TimeTableActivity.class));
                                break;
                        }
                    }

                } else if (code == 2) {
                    switch (position) {

                        case 0:
                            //Toast.makeText(getActivity(), "2016년 일정 준비중..", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getActivity(), plan.class));
                            break;
                        case 1:
                            Toast.makeText(getActivity(), "시험 시간표 준비중...", Toast.LENGTH_SHORT).show();
                            // startActivity(new Intent(getActivity(), ExamTimeActivity.class));
                            break;

                    }
                } else if (code == 3) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getActivity(), NoticeActivity.class));
                            break;
                    }
                } else {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getActivity(), SuggestionActivity.class));
                            break;
                    }
                }
            }
        }));

        Preference mPref = new Preference(getActivity());
        Bundle args = getArguments();
        int code = args.getInt("code");

        // 어댑터 아이템 추가하기
        if (code == 1) {
            TimeCount.Counter counter = countdday(getActivity(), 2018, 11, 15);


            // SimpleView
                if (mPref.getBoolean("simpleShowDDay", true)) {
                // 활성화
                    mAdapter.ddayItem(R.drawable.calendar2,
                            counter.text,
                            counter.result, true);


                }
                else{
                    // 비활성화
                    }
                // 간편보기
                if (mPref.getBoolean("simpleShowBap", true)) {
                    BapTool.todayBapData mBapData = BapTool.getTodayBap(getActivity());
                    mAdapter.addItem(R.drawable.foodrice,
                            mBapData.title,
                            mBapData.info, true);
                } else {
                    // 간편보기 아닐 때
                    mAdapter.addItem(R.drawable.foodrice,
                            getString(R.string.title_activity_bap),
                            true);
                }

                if (mPref.getBoolean("simpleShowTimeTable", true)) {
                    TimeTableTool.todayTimeTableData mTimeTableData = TimeTableTool.getTodayTimeTable(getActivity());
                    mAdapter.addItem(R.drawable.sche,
                            mTimeTableData.title,
                            mTimeTableData.info, true);
                } else {
                    mAdapter.addItem(R.drawable.sche,
                            getString(R.string.title_activity_time_table),
                            true);
                }

            } else if(code == 2) {
            mAdapter.addItem(R.drawable.calendar2,
                    getString(R.string.title_activity_schedule), true);
            mAdapter.addItem(R.drawable.sche,
                    getString(R.string.title_activity_exam_range), true);
                // DetailedView
// R.drawable.ic_launcher

            } else if(code == 3){
            mAdapter.addItem(R.drawable.note,
                    getString(R.string.title_activity_notice), true);

        } else {
            mAdapter.addItem(R.drawable.sugges,
                    getString(R.string.title_activity_suggestion), true);
        }



        return recyclerView;
    }
}
