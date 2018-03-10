package com.cui.test.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cui.test.R;
import com.cui.test.adp.Myadp2;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by ZMoffice on 2018.3.9.
 */

public class F2 extends Fragment {
    private ListView mLv2;
    private List<String> usernames=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.f2, null);
        mLv2 =v.findViewById(R.id.lv2);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.i("bro====",usernames.toString());
                    Myadp2 adp2=new Myadp2(getActivity(),usernames);
                    mLv2.setAdapter(adp2);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }

}
