package com.cui.test.myview;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cui.test.R;
import com.cui.test.adp.Myadp1;
import com.cui.test.ui.TjActivity;
import com.cui.test.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by ZMoffice on 2018.3.9.
 */

public class Mytitle extends LinearLayout{
    private TextView back;
    private TextView headtt;
    private TextView more;
    private ArrayList list = new ArrayList();
    private ListView listView;
    private Myadp1 adapter;
    private  boolean isShow;
    private PopupWindow popupWindow;
    public Mytitle(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDate();
        View v=View.inflate(context, R.layout.title,this);
        back=v.findViewById(R.id.back);
        headtt=v.findViewById(R.id.headtt);
        more=v.findViewById(R.id.more);
        View view=View.inflate(context, R.layout.more,null);
        adapter=new Myadp1(context,list);
        listView= (ListView) view.findViewById(R.id.lv);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    Intent in=new Intent(context, TjActivity.class);
                    context.startActivity(in);
                }
                else
                {
                    ToastUtils.showToast(context,list.get(i).toString());
                }
            }
        });
        popupWindow =new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setContentView(view);
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAsDropDown(more);
                }
            }
        });
    }
   public void setHeadtt(String ss)
   {
       headtt.setText(ss);
   }
   public void setBack(OnClickListener li)
   {
       back.setOnClickListener(li);
   }

    private void initDate() {
        list.add("添加好友");
        list.add("分享好友");
        list.add("关于我们");
    }
}
