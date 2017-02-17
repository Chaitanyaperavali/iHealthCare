package com.ase.team22.ihealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    /*public void userLogin(View v){
        if (v.getId()== R.id.login)
        {
            Intent i = new Intent( LoginActivity.this,AppPage.class);
            startActivity(i);
        }

    }
    public void facebookSignin(View v){
        if (v.getId()== R.id.bfb)
        {
            Intent i = new Intent( LoginActivity.this,AppPage.class);
            startActivity(i);
        }


    }
    public void googleSignin(View v){
        if (v.getId()== R.id.gsin)
        {
            Intent i = new Intent( LoginActivity.this,AppPage.class);
            startActivity(i);
        }


    }*/
}
