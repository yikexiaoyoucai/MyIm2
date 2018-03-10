package com.cui.test.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cui.test.R;
import com.cui.test.frag.F1;
import com.cui.test.frag.F2;
import com.cui.test.frag.F3;
import com.cui.test.myview.Mytitle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Mytitle mT1;
    private FrameLayout mFl;
    private TextView mB1;
    private TextView mB2;
    private TextView mB3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mB1.setTextColor(Color.BLUE);
        mB2.setTextColor(Color.BLACK);
        mB3.setTextColor(Color.BLACK);
        mT1.setHeadtt("消息");
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new F1()).commit();
    }

    private void initView() {
        mT1 = (Mytitle) findViewById(R.id.t1);
        mFl = (FrameLayout) findViewById(R.id.fl);
        mB1 = (TextView) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (TextView) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mB3 = (TextView) findViewById(R.id.b3);
        mB3.setOnClickListener(this);
        mT1.setBack(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                mB1.setTextColor(Color.BLUE);
                mB2.setTextColor(Color.BLACK);
                mB3.setTextColor(Color.BLACK);
                mT1.setHeadtt("消息");
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new F1()).commit();
                break;
            case R.id.b2:
                mB2.setTextColor(Color.BLUE);
                mB1.setTextColor(Color.BLACK);
                mB3.setTextColor(Color.BLACK);
                mT1.setHeadtt("联系人");
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new F2()).commit();
                break;
            case R.id.b3:
                mB3.setTextColor(Color.BLUE);
                mB2.setTextColor(Color.BLACK);
                mB1.setTextColor(Color.BLACK);
                mT1.setHeadtt("动态");
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new F3()).commit();
                break;
            default:
                break;
        }
    }
}
