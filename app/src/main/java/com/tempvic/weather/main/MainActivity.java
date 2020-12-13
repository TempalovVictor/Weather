package com.tempvic.weather.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tempvic.weather.R;
import com.tempvic.weather.fragments.FilterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFirstFragment();
    }

    private void startFirstFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment_root, new FilterFragment(), "FilterFragment")
                .addToBackStack("FilterFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 || getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}