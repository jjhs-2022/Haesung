package com.jhspt.recycleproj.tool;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeCount {




    public static Counter countdday(Context mContext, int myear, int mmonth, int mday) {
        Counter counter = new Counter();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


            Calendar todaCal = Calendar.getInstance(); //오늘날자 가져오기
            Calendar ddayCal = Calendar.getInstance(); //오늘날자를 가져와 변경시킴

            mmonth -= 1; // 받아온날자에서 -1을 해줘야함.
            ddayCal.set(myear, mmonth, mday);// D-day의 날짜를 입력
            Log.e("테스트", simpleDateFormat.format(todaCal.getTime()) + "");
            Log.e("테스트", simpleDateFormat.format(ddayCal.getTime()) + "");

            long today = todaCal.getTimeInMillis() / 86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
            long dday = ddayCal.getTimeInMillis() / 86400000;


            long count = dday - today; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            counter.result = String.valueOf((int) count) + "일";
            counter.text = "수능까지";

            if(today == dday) {
                counter.text = "애들아 수능 잘봐~";
                counter.text2 = "해성고 앱이 응원합니다";
                counter.result = "";
            }
            else if(today > dday) {
                counter.text = "업데이트 필요";
                counter.text2 = "해성고 앱은 지금 업데이트가 필요합니다";
                counter.result = "";
            }

        return counter;


        } catch (Exception e) {
            e.printStackTrace();
            // return -1;
            return counter;
        }
    }

    public static class Counter {
        public static String text = "수능날짜";
        public static String text2 = "수능날짜를 확인합니다";
        public static String result;

    }

}

