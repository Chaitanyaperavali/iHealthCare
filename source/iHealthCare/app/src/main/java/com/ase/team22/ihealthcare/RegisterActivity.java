package com.ase.team22.ihealthcare;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.R.attr.tag;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSignupOne fragmentSignupOne = new FragmentSignupOne();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
        fragmentTransaction.add(R.id.activity_register,fragmentSignupOne );
        fragmentTransaction.commit();
//
//        Button okButton = (Button)findViewById(R.id.button_Ok);
//
//        okButton.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
//                transaction.replace(R.id.activity_register, new FragmentSignupTwo());
//                transaction.commit();
//            }
//        } );
    }

}
