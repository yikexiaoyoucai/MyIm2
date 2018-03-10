package com.cui.test.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cui.test.R;
import com.cui.test.bean.User;
import com.cui.test.utils.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout mZcnameWrapper;
    private TextInputLayout mZcpwdWrapper;
    private Button mYesReg;
    private EditText mZcname;
    private EditText mZcpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        mZcnameWrapper = (TextInputLayout) findViewById(R.id.zcnameWrapper);
        mZcpwdWrapper = (TextInputLayout) findViewById(R.id.zcpwdWrapper);
        mYesReg = (Button) findViewById(R.id.reg_yes);
        mYesReg.setOnClickListener(this);
        mZcname = (EditText) findViewById(R.id.zcname);
        mZcpwd = (EditText) findViewById(R.id.zcpwd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_yes:
                final String zcname = mZcname.getText().toString();
                final String zcpwd = mZcpwd.getText().toString();
                if (TextUtils.isEmpty(zcname)) {
                    ToastUtils.showToast(this,"注册名不能为空");
                    mZcname.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(zcpwd)) {
                    ToastUtils.showToast(this,"注册密码不能为空");
                    mZcpwd.requestFocus();
                    return;
                }
                if (!TextUtils.isEmpty(zcname) && !TextUtils.isEmpty(zcpwd)) {

                    final ProgressDialog pd = new ProgressDialog(this);
                    pd.setMessage("正在注册");
                    pd.show();
                    User bu=new User();
                    bu.setUsername(zcname);
                    bu.setPassword(zcpwd);
//注意：不能用save方法进行注册
                    bu.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User s, BmobException e) {
                            if(e==null){
                                ToastUtils.showToast(getApplicationContext(),"bomb注册成功"+s.toString());
                                new Thread(new Runnable() {
                                    public void run() {
                                        try {
                                            // call method in SDK
                                            EMClient.getInstance().createAccount(zcname, zcpwd);
                                            runOnUiThread(new Runnable() {
                                                public void run() {
                                                    if (!RegActivity.this.isFinishing())
                                                        pd.dismiss();
                                                    // save current user
                                                    ToastUtils.showToast(getApplicationContext(),"环信注册成功");
                                                    finish();

                                                }
                                            });
                                        } catch (final HyphenateException e) {

                                        }
                                    }
                                }).start();
                            }else{
                                Log.i("pppppppp",e+"");
                            }
                        }
                    });


                }
                break;
            default:
                break;
        }
    }
}
