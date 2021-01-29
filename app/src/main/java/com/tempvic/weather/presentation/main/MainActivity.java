package com.tempvic.weather.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.base.BaseFragment;
import com.tempvic.weather.presentation.filter.FilterFragment;

import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

public class MainActivity extends AppCompatActivity {

    private String mTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFirstFragment();
        riseAndShine(this);
    }

    private void startFirstFragment() {
        addFragmentToContainer(new FilterFragment());
    }

    public void addFragmentToContainer(BaseFragment fragmentForContainer) {
        mTag = fragmentForContainer.getFragmentName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_root, fragmentForContainer, mTag)
                .addToBackStack(mTag)
                .commit();
    }

    public BaseFragment findCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentByTag(mTag);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 || getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            mTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            findCurrentFragment().onBackPressedCallback();
            super.onBackPressed();
        }
    }

    //TODO удалить на релизе
    public static void riseAndShine(Activity activity) {
        activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

        PowerManager power = (PowerManager) activity.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock lock =
                power.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
                        | PowerManager.ON_AFTER_RELEASE, activity.getPackageName() + ":wakeup!");
        lock.acquire(1000);
        lock.release();
    }
}