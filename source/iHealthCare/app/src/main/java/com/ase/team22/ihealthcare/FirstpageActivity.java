package com.ase.team22.ihealthcare;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


public class FirstpageActivity extends AppCompatActivity {
    ViewPager viewpager;
    CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        viewpager = (ViewPager) findViewById(R.id.view_pagerpager);
        adapter = new CustomSwipeAdapter(this);
        viewpager.setAdapter(adapter);
        //loading custom xml file
   Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
    }
    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            FirstpageActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewpager.getCurrentItem() == 0) {
                        viewpager.setCurrentItem(1);
                    } else if(viewpager.getCurrentItem() == 1) {
                        viewpager.setCurrentItem(2);
                        }
                    else {
                        viewpager.setCurrentItem(0);
                    }
                    }

            });
        }
    }

    public void userLogin(View view) {
        if(R.id.login == view.getId()){
            Intent intent = new Intent(this,login.class);
            startActivity(intent);
        }
    }

    public void userRegister(View view) {
        if(R.id.signup == view.getId()){
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}


