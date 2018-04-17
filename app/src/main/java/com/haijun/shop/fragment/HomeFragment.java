package com.haijun.shop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.haijun.shop.R;
import com.haijun.shop.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View inflate;
    private WebView webView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtil.i("HomeFragment","onCreateView");
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        return inflate;
    }

}
