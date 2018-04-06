package com.lpjeremy.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lpjeremy.permissionlibrary.request.IRequestPermissions;
import com.lpjeremy.permissionlibrary.request.RequestPermissions;
import com.lpjeremy.permissionlibrary.requestresult.IRequestPermissionResult;
import com.lpjeremy.permissionlibrary.requestresult.PermissionResult;
import com.lpjeremy.permissionlibrary.requestresult.RequestPermissionResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "NBA";

    private IRequestPermissions requestPermissions = RequestPermissions.getInstance();
    private IRequestPermissionResult requestPermissionResult = RequestPermissionResult.getInstance();
    LinearLayout layoutPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutPermission = findViewById(R.id.layoutPermission);
        findViewById(R.id.btnPaiZhao).setOnClickListener(this);
        findViewById(R.id.btnLuyin).setOnClickListener(this);
        findViewById(R.id.txtPermission).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtPermission:
                int visibility = layoutPermission.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                layoutPermission.setVisibility(visibility);
                break;
            case R.id.btnPaiZhao:
                String[] permissions = {Manifest.permission.CAMERA};

                if (requestPermission(permissions)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 10002);
                } else {
                    Log.e(TAG, "没有拍照的权限");
                }
                break;
            case R.id.btnLuyin:
                String[] permissions1 = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                if (requestPermission(permissions1)) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction("android.intent.action.PICK");
                    intent.addCategory("android.intent.category.DEFAULT");
                    startActivityForResult(intent, 10003);
                } else {
                    Log.e(TAG, "没有打开相册的权限");
                }
                break;
        }

    }

    private boolean requestPermission(String[] permissions) {
        return requestPermissions.requestPermissions(this, permissions, 10001);
    }

    /**
     * 用户授权操作结果（可能授权了，也可能未授权）
     *
     * @param requestCode  请求码
     * @param permissions  权限集合
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionResult permissionResult = requestPermissionResult.doRequestPermissionsResult(MainActivity.this, permissions, grantResults);
        switch (permissionResult) {
            case GRANTED:
                Toast.makeText(MainActivity.this, "授权成功，请重新点击刚才的操作！", Toast.LENGTH_LONG).show();
                break;

            case DENIED:
                Toast.makeText(MainActivity.this, "拒绝授权可以循环一直获取，直到用户同意！", Toast.LENGTH_LONG).show();
                String[] permissions2 = {Manifest.permission.CAMERA};
                requestPermission(permissions2);
                break;

            case NOTREMINDED://用户现在拒绝并不在提醒只有引导用户去设置中打开权限了
                Toast.makeText(MainActivity.this, "请给APP授权，否则功能无法正常使用！!!!", Toast.LENGTH_LONG).show();
                break;
        }

    }

}

