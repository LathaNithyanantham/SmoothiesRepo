package com.example.latha.smoothiesapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.zip.Inflater;

public class SecondActivity extends AppCompatActivity {
    ViewPager viewPagers;
    LinearLayout linearLayout;
    SliderAdapter sliderAdapters;
    TextView[] dots;
    Button continu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Intent intent = getIntent();

        viewPagers = (ViewPager) findViewById(R.id.view_pager_id);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_id);
        sliderAdapters = new SliderAdapter(this);
        viewPagers.setAdapter(sliderAdapters);
        continu=(Button)findViewById(R.id.continue_button);
        addDots(0);

       /* viewPagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
*/
    }
    public void addDots(int position) {
        dots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            linearLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(getResources().getColor(R.color.colorHollowBlue));
    }
    public void next(View view)
    {
        Intent intent=new Intent(SecondActivity.this,SignUp.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
