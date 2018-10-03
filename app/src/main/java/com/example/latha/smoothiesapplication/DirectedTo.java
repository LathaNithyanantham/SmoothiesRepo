package com.example.latha.smoothiesapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DirectedTo extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter tabAdapter;
    Toolbar toolbar;
    Button button;
    ImageView dyn_img;
    TextView t;
    VerticalSlideAdapter vs;
    int d;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directed_to_layout);
        vs=new VerticalSlideAdapter(this);
        final Intent intent=getIntent();
        t=(TextView)findViewById(R.id.h_id) ;
        tabLayout = findViewById(R.id.tabs_id);
        viewPager = findViewById(R.id.vp_id);
        toolbar=findViewById(R.id.tool_id);
        setSupportActionBar(toolbar);
        d=MainPage.v;
        if(d==0)
        getSupportActionBar().setTitle("Red Fruit");
        else if(d==1)
            getSupportActionBar().setTitle("Green Fruit");
        else
            getSupportActionBar().setTitle("PeachFruit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dyn_img=(ImageView)findViewById(R.id.dyn_img) ;
         if(d==1) {
             dyn_img.setImageResource(R.drawable.green);
             t.setText("Green Fruit");
         }
        else if(d==2) {
             dyn_img.setImageResource(R.drawable.peach_juice);
             t.setText("Peach Fruit");
         }
        else
            dyn_img.setImageResource(R.drawable.red_juice);
        button=(Button)findViewById(R.id.order_id) ;

        tabAdapter=new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new Ingredients(),"Ingredients");
        tabAdapter.addFragment(new Preparation(),"Preparation");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DirectedTo.this,SelectItems.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


}
