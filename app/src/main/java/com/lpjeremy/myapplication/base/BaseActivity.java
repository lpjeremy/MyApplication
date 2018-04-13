package com.lpjeremy.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    protected void LogI(String info) {
        if (mContext == null) return;
        Log.i(TAG, info);
    }

    protected void LogE(String err) {
        if (mContext == null) return;
        Log.e(TAG, err);
    }


}
