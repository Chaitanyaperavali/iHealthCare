package com.ase.team22.ihealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void userLogin(View v){
        if (v.getId()== R.id.login1)
        {
            Intent i = new Intent( Home.this,login.class);
            startActivity(i);
        }

    }
}
