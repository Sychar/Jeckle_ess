package com.felhr.serialportexample.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.felhr.serialportexample.DatenBank.InfoDataBase;
import com.felhr.serialportexample.Backend.Kennlinie;
import com.felhr.serialportexample.R;

public class Setting extends AppCompatActivity {
    private Intent intent;
    private InfoDataBase datenReader;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        datenReader=new InfoDataBase(this);
      Button Kennlinie =(Button)findViewById(R.id.kennlinie);
       Button jobuser =(Button) findViewById(R.id.jobsUser);
        Button account =(Button) findViewById(R.id.accoutnt);
        Button datalogger=(Button)findViewById(R.id.dlogger);
        jobuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicJobUser();
            }
        });
        datalogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_Datenlogger();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAccount();
            }
        });
        Kennlinie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_Kennlinie();
            }
        });

    }
    void onClicJobUser(){
        intent = new Intent(this,JobsUser.class);
        startActivity(intent);
    }

    void onClickAccount(){
        intent =new Intent(this, Account_deteils.class);
        startActivity(intent);
    }
    void onClick_Datenlogger() {
        Intent  intent = new Intent(this, DataLoger.class);
        startActivity(intent);
    }
    void onClick_Kennlinie() {
        Intent  intent = new Intent(this, Kennlinie.class);
        startActivity(intent);
    }
}
