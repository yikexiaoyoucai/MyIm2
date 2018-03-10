package com.cui.test.adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cui.test.R;

import java.util.List;

/**
 * Created by ZMoffice on 2018.3.9.
 */

public class Myadp2 extends BaseAdapter {
    private Context context;
    private List list;

    public Myadp2(Context context, List list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item2, null);
            vh.textview = (TextView) convertView.findViewById(R.id.name);
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
