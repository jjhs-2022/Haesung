package com.jhspt.recycleproj.tool;

import android.content.Context;
import android.database.Cursor;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import com.jhspt.recycleproj.R;

/**
 * Created by whdghks913 on 2015-02-17.
 */
public class TimeTableTool {
    public static final String TimeTableDBName = "HaeSungHighSchoolTimeTable.db";
    public static final String tableName = "HaeSungTimeTable";

    //    public final static String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WondangHS/";
    public final static String mFilePath = "/data/data/com.jhspt.recycleproj/databases/";
            // "/data/data/com.jhspt.recycleproj/databases/";
    public final static String mGoogleSpreadSheetUrl = "https://docs.google.com/spreadsheets/d/1LKUM85A_0tJd88OKiUoWv9ySSpojix0E6OLWDl1GeDE/edit?usp=sharing";
//    public final static int dbVersion = 6;

    public final static String[] mDisplayName = {"월요일", "화요일", "수요일", "목요일", "금요일"};



    public static boolean fileExists() {
        return new File(TimeTableTool.mFilePath + TimeTableTool.TimeTableDBName).exists();
    }

    public static timeTableData getTimeTableData(int mGrade, int mClass, int DayOfWeek) {
        if (mGrade == -1 || mClass == -1) {
            return null;
        }

        timeTableData mData = new timeTableData();
        String[] subject = new String[10];
        // String[] room = new String[7];

        Database mDatabase = new Database();
        mDatabase.openDatabase(TimeTableTool.mFilePath, TimeTableTool.TimeTableDBName);

        Cursor mCursor;


            mCursor = mDatabase.getData(TimeTableTool.tableName, "G" + mGrade + mClass);


        /**
         * Move to Row
         * ---- moveToFirst
         * ---- moveToNext
         * ---- moveToPosition
         * ---- moveToLast
         *
         * Mon : DayOfWeek : 0
         * Tus : DayOfWeek : 1
         * ...
         * Fri : DayOfWeek : 4
         */
        mCursor.moveToPosition((DayOfWeek * 9) + 1);

        for (int period = 0; period < 9; period++) {
            String mSubject;//, mRoom;

            /**
             * | | | |
             * 0 1 2 3
             */

            mSubject = mCursor.getString(0);
           // mRoom = "교실";


            if (mSubject != null && !mSubject.isEmpty()
                    && mSubject.contains("\n"))
                mSubject = mSubject.replace("\n", " (") + ")";

            subject[period] = mSubject;
            // room[period] = mRoom;

            mCursor.moveToNext();
        }

        mData.subject = subject;
        // mData.room = room;

        return mData;
    }

    public static class timeTableData {
        public String[] subject;//, room;
    }

    public static todayTimeTableData getTodayTimeTable(Context mContext) {
        Preference mPref = new Preference(mContext);
        todayTimeTableData mData = new todayTimeTableData();

        Calendar mCalendar = Calendar.getInstance();

        int DayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);

        int mGrade = mPref.getInt("myGrade", -1);
        int mClass = mPref.getInt("myClass", -1);

        if (DayOfWeek > 1 && DayOfWeek < 7) {
            DayOfWeek -= 2;
        } else {
            mData.title = mContext.getString(R.string.not_go_to_school_title);
            mData.info = mContext.getString(R.string.not_go_to_school_message);
            return mData;
        }

        mData.title = String.format(mContext.getString(R.string.today_timetable), mCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.KOREAN));

        if (mGrade == -1 || mClass == -1) {
            mData.info = mContext.getString(R.string.no_setting_my_grade);
            return mData;
        }

        boolean mFileExists = new File(TimeTableTool.mFilePath + TimeTableTool.TimeTableDBName).exists();
        if (!mFileExists) {
            mData.info = mContext.getString(R.string.not_exists_data);
            return mData;
        }

        String mTimeTable = "";

        Database mDatabase = new Database();
        mDatabase.openDatabase(TimeTableTool.mFilePath, TimeTableTool.TimeTableDBName);


        Cursor mCursor;

            mCursor = mDatabase.getData(TimeTableTool.tableName, "G" + mGrade + mClass);

        /**
         * Move to Row
         * ---- moveToFirst
         * ---- moveToNext
         * ---- moveToPosition
         * ---- moveToLast
         *
         * Mon : DayOfWeek : 0
         * Tus : DayOfWeek : 1
         * ...
         * Fri : DayOfWeek : 4
         */
        mCursor.moveToPosition((DayOfWeek * 9) + 1);


        for (int period = 0; period < 9; period++) {
            String mSubject;

            /**
             * | | | |
             * 0 1 2 3
             */

            mSubject = mCursor.getString(0);

            if (mSubject != null && !mSubject.isEmpty()
                    && mSubject.contains("\n"))
                mSubject = mSubject.replace("\n", " (") + ")";

            mTimeTable += Integer.toString(period + 1) + ". " + mSubject;

            if (mCursor.moveToNext()) {
                mTimeTable += "\n";
            }
        }

        mData.info = mTimeTable;

        return mData;
    }

    public static class todayTimeTableData {
        public String title;
        public String info;
    }
}
