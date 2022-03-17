package com.jhspt.recycleproj.activity.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jhspt.recycleproj.R;
import com.jhspt.recycleproj.activity.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);

        startActivity(intent);
        finish();
    }

}
