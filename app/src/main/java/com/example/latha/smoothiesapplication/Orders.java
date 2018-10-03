package com.example.latha.smoothiesapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.latha.smoothiesapplication.FinalPage.fruitsent;

public class Orders extends Fragment {
    ViewPager viewPagers;
    LinearLayout linearLayout;
    int i=1;
    static ArrayList<String> fn, amt, sr;
    String x, y, z;
    Cursor c;
    HorizontalSliderAdapter horizontalSliderAdapter;
    TextView[] dots;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_layout, container, false);
        viewPagers = (ViewPager) view.findViewById(R.id.h_view_pager_id);
        linearLayout = (LinearLayout) view.findViewById(R.id.h_linear_layout_id);
        fn = new ArrayList<>();
        amt = new ArrayList<>();
        sr = new ArrayList<>();

        TableData td = new TableData(getContext());
        Log.d("mmmm",SignUp.emailId);
        c = td.retrieveFromDB(td,SignUp.emailId);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                x = c.getString(0);
                y = c.getString(1);
                z = c.getString(2);

                fn.add(x);
                sr.add(y);
                amt.add(z);

            } while (c.moveToNext());
        }else
            Toast.makeText(getContext(), "NO ORDERS YET", Toast.LENGTH_LONG).show();
        horizontalSliderAdapter = new HorizontalSliderAdapter(view.getContext(),fn,sr,amt);
        viewPagers.setAdapter(horizontalSliderAdapter);

        addDots(0);
        viewPagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                addDots(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    public void addDots(int position) {
        dots = new TextView[horizontalSliderAdapter.getCount()];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            linearLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(getResources().getColor(R.color.colorHollowBlue));
    }
    }


