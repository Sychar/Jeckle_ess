package com.felhr.serialportexample.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.felhr.serialportexample.DatenBank.User_Datenbank;
import com.felhr.serialportexample.DatenBank.Users;
import com.felhr.serialportexample.R;
import com.felhr.serialportexample.View.Account_deteils;

public class Add_Account extends AppCompatActivity {
   private String name ;
   private static  String prveling;
   private Users user;
   private Button add;
    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__account);
       editText =(EditText) findViewById(R.id.enterName);
        add = (Button) findViewById(R.id.add_user);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

    }

    public void onRadioButton(View view){
        boolean cheaked=((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.master:
            if(cheaked){
                prveling="master";
                break;
            }
            case R.id.general:
                if(cheaked){
                    prveling="General";
                    break;
                }
        }
    }

    public void add(){
        name= editText.getText().toString();
        user =new Users(name,prveling);
        User_Datenbank user_datenbank = new User_Datenbank(this);
        user_datenbank.addUser(user);
        intent = new Intent(this, Account_deteils.class);
        startActivity(intent);

    }

}
