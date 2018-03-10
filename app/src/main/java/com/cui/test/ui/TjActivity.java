package com.cui.test.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cui.test.R;
import com.cui.test.utils.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class TjActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNamebro;
    private Button mAddbro;
    private List<String> usernames = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tj);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.i("nnn===",usernames.toString());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initView() {
        mNamebro = (EditText) findViewById(R.id.namebro);
        mAddbro = (Button) findViewById(R.id.addbro);
        mAddbro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addbro:
                final String broname=mNamebro.getText().toString();
                if(TextUtils.isEmpty(broname))
                {
                    ToastUtils.showToast(this,"您的好友名不完整");
                }
                else {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           try {
                               EMClient.getInstance().contactManager().addContact(broname,broname+"交个朋友吧");
                               ToastUtils.showToast(getApplicationContext(),"发送添加信息成功");
                           } catch (HyphenateException e) {
                               e.printStackTrace();
                           }
                       }
                   }).start();
                }

                break;
            default:
                break;
        }
    }
}
