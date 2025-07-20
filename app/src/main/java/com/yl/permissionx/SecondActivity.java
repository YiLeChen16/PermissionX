package com.yl.permissionx;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.permission.yiledev.PermissionX;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/**
 * @author YL Chen
 * @version 1.0
 * @description: java调用测试
 * @date 2025/7/20 17:23
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionX.INSTANCE.request(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.RECORD_AUDIO}, new Function2<Boolean, List<String>, Unit>() {
            @Override
            public Unit invoke(Boolean aBoolean, List<String> strings) {
                if (aBoolean) {
                    call();
                } else {
                    String deniedPermissions = "You denied " + strings.toString();
                    Toast.makeText(SecondActivity.this, deniedPermissions, Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }

    //跳转到拨号界面
    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
