package com.example.latha.smoothiesapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SelectItems extends AppCompatActivity {
    Button plus;
    Button minus,order;
    TextView result,cost;
    int ans,amt=50,d;
   static String val;
   static String serve;
   Toolbar toolbar;
    ArrayList<String> selected=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_items_layout);
        Intent intent=getIntent();
        toolbar=(Toolbar)findViewById(R.id.tool_id);
        plus=(Button)findViewById(R.id.plus_is);
        minus=(Button)findViewById(R.id.minus_id);
        result=(TextView)findViewById(R.id.num_id);
        cost=(TextView)findViewById(R.id.Cost_id);
        order=(Button)findViewById(R.id.oi_id);
        selected.add("1/2 Banana");
        selected.add("2 large strawberries");
        selected.add("1/4 cup blueberry");
        selected.add("1/2 cup coconut milk");
        selected.add("Mint Leaves");

        setSupportActionBar(toolbar);
        d=MainPage.v;
        if(d==0)
            getSupportActionBar().setTitle("Red Fruit");
        else if(d==1)
            getSupportActionBar().setTitle("Green Fruit");
        else
            getSupportActionBar().setTitle("PeachFruit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans=Integer.parseInt(result.getText().toString());
                if(ans<50) {
                    ans = ans + 10;
                    result.setText(String.valueOf(ans));
                }
               amt=50*(ans/10);
                cost.setText(String.valueOf(amt)+" USD");

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans=Integer.parseInt(result.getText().toString());
                if(ans>10) {
                    ans = ans -10;
                    result.setText(String.valueOf(ans));
                }
                amt=50*(ans/10);
                cost.setText(String.valueOf(amt)+" USD");

            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val=cost.getText().toString();
                serve=result.getText().toString();
                Intent intent=new Intent(SelectItems.this,FinalPage.class);
               intent.putExtra("selectedi",selected);
                startActivity(intent);
            }
        });

    }
    public  void selectItems(View view)
    {  boolean checked=((CheckBox)view).isChecked();
        switch (view.getId())
        {
            case  R.id.c1_id:
                if(checked)
                    selected.add("1/2 Banana");
                else
                    selected.remove("1/2 Banana");
                break;
            case  R.id.c2_id:
                if(checked)
                    selected.add("2 large strawberries");
                else
                    selected.remove("2 large strawberries");
                break;
            case  R.id.c3_id:
                if(checked)
                    selected.add("1/4 cup blueberry");
                else
                    selected.remove("1/4 cup blueberry");
                break;
            case  R.id.c4_id:
                if(checked)
                    selected.add("1/2 cup coconut milk");
                else
                    selected.remove("1/2 cup coconut milk");
                break;
            case  R.id.c5_id:
                if(checked)
                    selected.add("Mint Leaves");
                else
                    selected.remove("Mint Leaves");
                break;
            case  R.id.c6_id:
                if(checked)
                    selected.add("Long glass with straw");
                else
                    selected.remove("Long glass with straw");
                break;
        }
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
