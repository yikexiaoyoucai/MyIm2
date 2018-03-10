package com.cui.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.cui.test.R;

public class IntoActivity extends AppCompatActivity {
   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           Intent in=new Intent(IntoActivity.this,LoginActivity.class);
           startActivity(in);
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);
        handler.sendEmptyMessageDelayed(0,3000);
    }


}
