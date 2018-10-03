package com.example.latha.smoothiesapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.latha.smoothiesapplication.MainPage.v;

public class FinalPage extends AppCompatActivity {
    Button confirm;
    Toolbar toolbar;
    int d;
    TextView underline, items, amount, fruit, serving, underline1;
    TextView  myaddress;
    ArrayList<String> i = new ArrayList<>();
    SelectItems s;
    Toolbar toolbars;
    Context context;
    Cursor c;

    static String fruitsent;
    List<Address> addresses=new ArrayList<>();
    LocationManager locationManager;
    LocationListener locationListener;
    double latitude, longitude;
    Geocoder geocoder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_page_layout);
        underline = (TextView) findViewById(R.id.u_id);
        underline1 = (TextView) findViewById(R.id.u1_id);
        items = (TextView) findViewById(R.id.items_id);
        myaddress = findViewById(R.id.address_id);
        toolbar = (Toolbar) findViewById(R.id.tool_id);
        amount = (TextView) findViewById(R.id.f_amt_id);
        fruit = (TextView) findViewById(R.id.f_fruit_id);
        serving = (TextView) findViewById(R.id.f_serve_id);
        confirm = (Button) findViewById(R.id.co_id);

        s = new SelectItems();
        if (v == 0) {
            fruit.setText("Red Fruit");
            fruitsent = "Red Fruit";
        } else if (v == 1) {
            fruit.setText("Green Fruit");
            fruitsent = "Green Fruit";
        } else {
            fruit.setText("Peach Fruit");
            fruitsent = "Peach Fruit";
        }
        amount.setText(SelectItems.val);
        serving.setText(" X " + (SelectItems.serve + " servings"));

        final Intent intent = getIntent();
        i = intent.getStringArrayListExtra("selectedi");
        String res = "";
        for (String item : i) {
            res = res + item + "\n";
        }
        items.setText(res);
        setSupportActionBar(toolbar);
        d = v;
        if (d == 0)
            getSupportActionBar().setTitle("Red Fruit");
        else if (d == 1)
            getSupportActionBar().setTitle("Green Fruit");
        else
            getSupportActionBar().setTitle("PeachFruit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        underline.setPaintFlags(underline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        underline1.setPaintFlags(underline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        configureButton();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableData td = new TableData(v.getContext());
                td.putDataToDB(td,SignUp.emailId, fruitsent, SelectItems.serve, SelectItems.val);
                MainPage.v=0;
                Intent intent2 = new Intent(getApplicationContext(), Progress.class);
                startActivity(intent2);
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                latitude = location.getLatitude();
                longitude = location.getLongitude();
               myaddress.setText(latitude + " " + longitude);
              try {
                  geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    addresses = geocoder.getFromLocation(latitude,longitude,1);
                  Log.d("latitude", String.valueOf(latitude));
                  Log.d("longitude", String.valueOf(longitude));
                    if(addresses.size()>0) {
                        String address = addresses.get(0).getAddressLine(0);
                        /*String city = addresses.get(0).getAdminArea();
                        String area = addresses.get(0).getLocality();*/
                        /*String country = addresses.get(0).getCountryName();
                        String postalcode = addresses.get(0).getCountryCode();*/
                        myaddress.setText(  address+"\n" );
                    }
                    } catch(IOException e){
                        e.printStackTrace();
                    }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }
            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        myaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

            }

        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                    configureButton();
                    break;
                    default:break;
                }

        }


    public void configureButton() {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 10);
                    }
                    return;
                }

                myaddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

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
