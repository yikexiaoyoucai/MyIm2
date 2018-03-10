package com.cui.test.adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cui.test.R;

import java.util.ArrayList;

/**
 * Created by ZMoffice on 2018.3.9.
 */

public class Myadp1 extends BaseAdapter {
    private Context context;
    private ArrayList list;

    public Myadp1(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item1, null);
            vh.textview = (TextView) convertView.findViewById(R.id.gongneng1);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textview.setText(list.get(position) + "");
        return convertView;
    }
    static class ViewHolder {
        TextView textview;
    }

}
