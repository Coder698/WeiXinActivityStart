package com.zhaoss.weixinactivitystart;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void initData() {

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "开启子界面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, TowActivity.class), MainActivity.this);
            }
        });

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public View initView() {
        return View.inflate(this, R.layout.activity_main, null);
    }
}
