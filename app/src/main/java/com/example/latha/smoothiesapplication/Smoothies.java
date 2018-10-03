package com.example.latha.smoothiesapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class Smoothies extends Fragment {
    VerticalViewPager viewPagers;
    VerticalSlideAdapter verticalSlideAdapter;
    TextView details;
    int v ;
    myInterface m;

    interface myInterface {
        void data(int values);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.smoothies_layout, container, false);
        viewPagers = (VerticalViewPager) view.findViewById(R.id.vertical_view_pgr_id);
        details = (TextView) view.findViewById(R.id.details_id);
        verticalSlideAdapter = new VerticalSlideAdapter(view.getContext());
        viewPagers.setAdapter(verticalSlideAdapter);
        viewPagers.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               v=i;
               m.data(v);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof myInterface)
            m = (myInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m = null;
    }
}



