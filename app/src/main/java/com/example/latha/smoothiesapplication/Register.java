package com.example.latha.smoothiesapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText rUserId,rPassword,email;
    Button register;
    String userId,password,emailId;
boolean flag;
TableData td;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        rUserId=findViewById(R.id.r_user_id);
        rPassword=findViewById(R.id.r_password_id);
        email=findViewById(R.id.r_email_id);
        register=findViewById(R.id.registerit_id);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userId= rUserId.getText().toString();
               password=rPassword.getText().toString();
               emailId=email.getText().toString();
               boolean a=validate();
                Log.d("pooo",String.valueOf(a));
               if(validate()==false)
               {
                   td = new TableData(getApplicationContext());
                   td.register(td,emailId,userId,password);
                   Intent intent=new Intent(v.getContext(),SignUp.class);
                   startActivity(intent);
               }

        }
        });
    }
    boolean validate()
    {   flag=true;
        if(TextUtils.isEmpty(userId)) {
            rUserId.setError("please enter username");
            flag = true;
        }
        else
            flag=false;
        if(TextUtils.isEmpty(password)) {
            rPassword.setError("please enter Pasword");
            flag=true;
        }
        else
            flag=false;
        if(TextUtils.isEmpty(emailId)) {
            flag=true;
            email.setError("please enter emailId");
        }
           else if (!(emailId.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))) {
            email.setError("please enter a valid email id");
            flag = true;
        }
        else
            flag=false;

        TableData td = new TableData(getApplicationContext());
        Cursor c1;
        c1 = td.getRegisteredData(td);
        if(c1.getCount()>0) {
            c1.moveToFirst();
            do {
                if ( flag== false && emailId.equals(c1.getString(0))) {

                    Toast.makeText(this,"USER ALREADY EXIST",Toast.LENGTH_SHORT).show();
                    flag=true;
                    break;
                }

            } while (c1.moveToNext());
        }

        return  flag;

    }
}
