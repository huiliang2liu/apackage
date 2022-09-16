package com.lhl.apackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.lhl.apackage.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gaode(View v) {
        Toast.makeText(this, ApkUtil.isAvailableMiniMap(this) ? "安装了":"没安装",Toast.LENGTH_SHORT).show();
    }

    public void baidu(View v) {
        Toast.makeText(this, ApkUtil.isAvailableBaiDuMap(this) ? "安装了":"没安装",Toast.LENGTH_SHORT).show();
    }

    public void weixin(View v) {
        Toast.makeText(this, ApkUtil.isAvailableWeiXin(this) ? "安装了":"没安装",Toast.LENGTH_SHORT).show();
    }
}