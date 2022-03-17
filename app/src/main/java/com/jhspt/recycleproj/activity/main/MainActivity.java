package com.jhspt.recycleproj.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import com.jhspt.recycleproj.R;

import com.jhspt.recycleproj.activity.loading.LoadingActivity;
import com.jhspt.recycleproj.activity.notice.NoticeAdapter;
import com.jhspt.recycleproj.activity.settings.SettingsActivity;
import com.jhspt.recycleproj.tool.Preference;

public class MainActivity extends AppCompatActivity {

    // SwipeRefreshLayout mSwipeRefreshLayout;

    MainAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Intent intent = new Intent(this, LoadingActivity.class);
        // startActivity(intent);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);












        // 넘기는 부분
        ViewPager viewPager = (ViewPager) findViewById(R.id.mViewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        // 탭
        TabLayout tabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        showUpdateNotification();
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("앱을 종료하시겠습니까?");
        builder.setNegativeButton("취소", null);
        builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }

        });
        builder.show();
    }





        public void showUpdateNotification() {
        try {
            Preference mPref = new Preference(getApplicationContext());
            PackageManager packageManager = getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);

            int versionCode = info.versionCode;

            if (mPref.getInt("versionCode", 0) != versionCode) {
                mPref.putInt("versionCode", versionCode);

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle(R.string.update_notification_title);
                builder.setMessage(R.string.update_notification_message);
                builder.setPositiveButton(android.R.string.ok, null);
                builder.show();
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void setupViewPager(ViewPager viewPager) {
        Adapter mAdapter = new Adapter(getSupportFragmentManager());

       // mAdapter.addFragment("수능", MainFragment.getInstance(0));
        mAdapter.addFragment(getString(R.string.activity_main_fragment_simple), MainFragment.getInstance(1));
        mAdapter.addFragment("학사 일정", MainFragment.getInstance(2));
        mAdapter.addFragment("교내 정보", MainFragment.getInstance(3));
        mAdapter.addFragment("앱 정보", MainFragment.getInstance(4));

        viewPager.setAdapter(mAdapter);
    }


    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        public void addFragment(String mTitle, Fragment mFragment) {
            mFragments.add(mFragment);
            mFragmentTitles.add(mTitle);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
