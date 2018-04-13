package com.lpjeremy.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lpjeremy.myapplication.base.BaseActivity;

public class BActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogE("onCreate(BActivity)");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogE("onStart(BActivity)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogE("onResume(BActivity)");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogE("onPause(BActivity)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogE("onStop(BActivity)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogE("onDestroy(BActivity)");
    }
}
