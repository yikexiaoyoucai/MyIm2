package com.cui.test.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cui.test.R;
import com.cui.test.utils.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtlog;
    private TextView mBtreg;
    private TextInputLayout mUsernameWrapper;
    private TextInputLayout mPasswordWrapper;
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        mBtlog = (Button) findViewById(R.id.btlog);
        mBtlog.setOnClickListener(this);
        mBtreg = (TextView) findViewById(R.id.btreg);
        mBtreg.setOnClickListener(this);
        mUsernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        mPasswordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btlog:
                final String name = mUsername.getText().toString();
                final String pwd = mPassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(this,"登录名不能为空");
                    mUsername.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showToast(this,"登录密码不能为空");
                    mPassword.requestFocus();
                    return;
                }
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    final ProgressDialog pd = new ProgressDialog(this);
                    pd.setMessage("正在登录");
                    pd.show();
                    new Thread(new Runnable() {
                        public void run() {
                            EMClient.getInstance().login(name,pwd,new EMCallBack() {//回调
                                @Override
                                public void onSuccess() {
                                    EMClient.getInstance().groupManager().loadAllGroups();
                                    EMClient.getInstance().chatManager().loadAllConversations();
                                    Log.d("main", "登录环信聊天服务器成功！");
                                    Intent in=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(in);
                                }

                                @Override
                                public void onProgress(int progress, String status) {

                                }

                                @Override
                                public void onError(int code, String message) {
                                    Log.d("main", "登录环信聊天服务器失败！");
                                }
                            });
                        }
                    }).start();
                }
                break;
            case R.id.btreg:
                Intent in = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(in);
                break;
            default:
                break;
        }
    }
}
