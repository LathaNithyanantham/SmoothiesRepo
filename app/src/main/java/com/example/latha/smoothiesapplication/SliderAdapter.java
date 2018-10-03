package com.example.latha.smoothiesapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context)
    {
        this.context=context;
    }
    public  int[] slide_images={
            R.drawable.straw,
            R.drawable.watermelon,
            R.drawable.van
    };
    public String[] heading_array={
            "Discover Smoothies",
            "Choose recepie",
            "Next day delivery"
    };
    public String[] description_array={
            "Discover thousands of tasty smoothies for any smoothie enthusiasist",
            "Choose your favourite smoothie and see full details on the recepie and preparation",
            "Order all your favourite smoothie ingredients in one click.Next day delivery guaranteed"
    };

   /* public SliderAdapter(@NonNull View itemView) {
        super(itemView);
    }*/

    @Override
    public int getCount() {
        return heading_array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout)o;
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {

        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView images=(ImageView)view.findViewById(R.id.imageView_id);
        TextView heading=(TextView)view.findViewById(R.id.heading_id);
        TextView description=(TextView)view.findViewById(R.id.description_id);

        images.setImageResource(slide_images[position]);
        heading.setText(heading_array[position]);
        description.setText(description_array[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
