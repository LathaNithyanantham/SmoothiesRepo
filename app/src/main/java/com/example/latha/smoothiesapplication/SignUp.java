package com.example.latha.smoothiesapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    TextView signup,signin;
    String x,y,z;
    Cursor c,c1;
    String userId,password;
    static  String emailId;
    public static String name;
    EditText sUserId,sPassword;
    boolean flag,validity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        sUserId=findViewById(R.id.user_id);
        sPassword=findViewById(R.id.password_id);
        Intent intent=getIntent();
        signup=(TextView)findViewById(R.id.signup_id);
        signin=(TextView)findViewById(R.id.signin_id);
        signup.setPaintFlags(signup.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId=sUserId.getText().toString();
                password=sPassword.getText().toString();
                if(validate()==false)
                {
                    TableData td = new TableData(getApplicationContext());
                    c1 = td.getRegisteredData(td);
                    if(c1.getCount()>0) {
                        c1.moveToFirst();
                        do {
                            if (userId.equals( c1.getString(1)) && password.equals(c1.getString(2))) {
                                validity = true;
                                emailId = c1.getString(0);
                                name=c1.getString(1);
                                break;

                            } else
                                validity = false;

                        } while (c1.moveToNext());
                    }
                    if(validity==true) {

                        Intent intent1 = new Intent(SignUp.this, MainPage.class);
                        startActivity(intent1);
                    }
                    else
                        Toast.makeText(v.getContext(),"NOT A VALID USER",Toast.LENGTH_SHORT).show();
            }
            }
        });

    }
    public  void register(View view)
    {
        Intent intent2=new Intent(SignUp.this,Register.class);
        startActivity(intent2);

    }
    boolean validate()
    {   flag=true;
        if(TextUtils.isEmpty(userId))
            sUserId.setError("please enter username");
        else
            flag=false;
        if(TextUtils.isEmpty(password))
            sPassword.setError("please enter Pasword");
        else
            flag=false;
        return  flag;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
