package com.haijun.shop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haijun.shop.R;
import com.haijun.shop.activity.PersionDataActivity;
import com.haijun.shop.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener{


    private View inflate;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtil.i("MeFragment","onCreateView");
        inflate = inflater.inflate(R.layout.fragment_me, container, false);
        initView();
        return inflate;
    }

    private void initView() {
        TextView tv_me_persiondata = inflate.findViewById(R.id.tv_me_persiondata);

        tv_me_persiondata.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_me_persiondata:
                startActivity(new Intent(getActivity(), PersionDataActivity.class));
                break;

        }
    }
}
