package com.ase.team22.ihealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void userLogin(View v){
        if (v.getId()== R.id.login)
        {
            Intent i = new Intent( login.this,Home.class);
            startActivity(i);
        }

    }
    public void facebookSignin(View v){
        if (v.getId()== R.id.fin)
        {
            /*Intent i = new Intent( login.this,Home.class);
            startActivity(i);*/
        }


    }
    public void googleSignin(View v){
       /* if (v.getId()== R.id.gsin)
        {
            *//*Intent i = new Intent( login.this,Home.class);
            startActivity(i);*//*
        }*/


    }
}
