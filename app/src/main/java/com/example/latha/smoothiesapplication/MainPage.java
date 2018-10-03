package com.example.latha.smoothiesapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Smoothies.myInterface{
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Smoothies s;
    public static int v,flag;
    TextView n,m,version;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        s = new Smoothies();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_layout);
        toolbar = findViewById(R.id.toolbar_id);
        drawerLayout = (DrawerLayout) findViewById(R.id.nav_layout);
        Intent i = getIntent();
        String extra = i.getStringExtra("frag");
        navigationView = findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        n=header.findViewById(R.id.my_name);
        m=header.findViewById(R.id.my_mail);
        version=findViewById(R.id.ver_id);
        version.setText("version"+" "+BuildConfig.VERSION_NAME);
        n.setText(SignUp.name);
        m.setText(SignUp.emailId);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorHeader));
        toggle.syncState();
        if (extra == null)
        {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Smoothies()).commit();
            navigationView.setCheckedItem(R.id.nav_smoothie);
            getSupportActionBar().setTitle("Smoothies");
        }
    }
    else
        if(extra.equals("Orders"))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Orders()).commit();
            navigationView.setCheckedItem(R.id.nav_orders);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_smoothie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Smoothies()).commit();
                getSupportActionBar().setTitle("Smoothies");
                flag++;
                break;
            case R.id.nav_orders:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Orders()).commit();
                getSupportActionBar().setTitle("My Orders");
                flag++;

                break;
            case R.id.nav_signout:
                MainPage.v=0;
                Intent intent=new Intent(MainPage.this,SignUp.class);
                startActivity(intent);

               /* Orders.fn=new ArrayList<>();
                Orders.fn.clear();
                Orders.amt=new ArrayList<>();
                Orders.amt.clear();
                Orders.sr=new ArrayList<>();
                Orders.sr.clear();
                HorizontalSliderAdapter.order_names_array=new ArrayList<>();
                HorizontalSliderAdapter.nos_array=new ArrayList<>();
                HorizontalSliderAdapter.price_array=new ArrayList<>();
                HorizontalSliderAdapter.order_names_array.clear();
                HorizontalSliderAdapter.price_array.clear();
                HorizontalSliderAdapter.nos_array.clear();
                HorizontalSliderAdapter.h_slide_images=new ArrayList<>();
                HorizontalSliderAdapter.h_slide_images.clear();*/
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public  void  onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {

            Intent intent=new Intent(this,SignUp.class);
            startActivity(intent);

        }
    }

    @Override
    public void data(int values) {
        v=values;
    }
}