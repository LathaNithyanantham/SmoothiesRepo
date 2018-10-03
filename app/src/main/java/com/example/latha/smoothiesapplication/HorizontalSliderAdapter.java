package com.example.latha.smoothiesapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HorizontalSliderAdapter extends  PagerAdapter{
    Context context;
    LayoutInflater layoutInflater;
    public static ArrayList<Integer> h_slide_images;
    public static ArrayList<String> order_names_array;
    public static ArrayList<String> nos_array;
    public static ArrayList<String> price_array;
    int val;

    public HorizontalSliderAdapter(Context context,ArrayList<String> a,ArrayList<String> b,ArrayList<String> c)
    {
        h_slide_images=new ArrayList<>();
        order_names_array=new ArrayList<>();
        nos_array=new ArrayList<>();
        price_array=new ArrayList<>();
        this.context=context;

        for (String x : a)
            order_names_array.add(x);
        for (String x :b)
            nos_array.add(x);
        for (String x : c)
            price_array.add(x);
        for (String x : a) {
            if (x.equals("Red Fruit"))
                h_slide_images.add(R.drawable.red_juice);
            else if (x.equals("Green Fruit"))
                h_slide_images.add(R.drawable.green);
            else
                h_slide_images.add(R.drawable.peach_juice);

        }
    }


    @Override
    public int getCount() {

        return order_names_array.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(ConstraintLayout)o;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.horizontal_slider_layout, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.o_img_id);
        TextView name = (TextView) view.findViewById(R.id.order_name_id);
        TextView nos = (TextView) view.findViewById(R.id.nos_id);
        TextView price = (TextView) view.findViewById(R.id.price_id);
            img.setImageResource(h_slide_images.get(position));
            name.setText(order_names_array.get(position));
            nos.setText(nos_array.get(position));
            price.setText(price_array.get(position));

            container.addView(view);
            return view;


    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
