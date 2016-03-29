package com.zhaoss.weixinactivitystart;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Zhaoss on 2016/3/29.
 */
public class TowActivity extends BaseActivity {

    @Override
    protected void initData() {

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TowActivity.this, "开启主页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TowActivity.this, MainActivity.class), TowActivity.this);
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
        return View.inflate(this, R.layout.activity_tow, null);
    }
}
