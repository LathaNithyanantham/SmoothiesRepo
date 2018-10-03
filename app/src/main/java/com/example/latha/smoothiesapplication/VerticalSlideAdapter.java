package com.example.latha.smoothiesapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VerticalSlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    int pos;

    public VerticalSlideAdapter(Context context) {
        this.context=context;
    }

    public int[] slide_images = {
            R.drawable.red_juice,
            R.drawable.green,
            R.drawable.peach_juice
    };
    public String[] heading_array = {
            "Red Fruit",
            "Green Fruit",
            "Peach Fruit"
    };


    @Override
    public int getCount() {
        return heading_array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.vertical_slider_layout, container, false);
        ImageView imageView=(ImageView)view.findViewById(R.id.img_id) ;
        TextView textView=(TextView)view.findViewById(R.id.name_id) ;
        TextView detail=(TextView)view.findViewById(R.id.details_id) ;
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(),DirectedTo.class);
                context.startActivity(intent);
            }
        });
        imageView.setImageResource(slide_images[position]);
        textView.setText(heading_array[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
