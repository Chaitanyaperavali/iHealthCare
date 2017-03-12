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
import android.widget.RadioButton;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;




public class FirstpageActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private CustomSwipeAdapter adapter;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        radioButton1 = (RadioButton) findViewById(R.id.radio_btn1);
        radioButton2 = (RadioButton) findViewById(R.id.radio_btn2);
        radioButton3 = (RadioButton) findViewById(R.id.radio_btn3);
        radioButton4 = (RadioButton) findViewById(R.id.radio_btn4);
        radioButton1.setChecked(true);
        viewpager = (ViewPager) findViewById(R.id.view_pagerpager);
        adapter = new CustomSwipeAdapter(this);
        viewpager.setAdapter(adapter);
        //loading custom xml file
   Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),1000,2000);
    }
    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            FirstpageActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewpager.getCurrentItem() == 0) {
                        viewpager.setCurrentItem(1);
                        radioButton2.setChecked(true);
                    } else if(viewpager.getCurrentItem() == 1) {
                        viewpager.setCurrentItem(2);
                        radioButton3.setChecked(true);
                        }
                    else if(viewpager.getCurrentItem() == 2) {
                        viewpager.setCurrentItem(3);
                        radioButton4.setChecked(true);
                    }
                    else {
                        viewpager.setCurrentItem(0);
                        radioButton1.setChecked(true);
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


