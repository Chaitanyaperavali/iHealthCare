package com.ase.team22.ihealthcare;


import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentSignupOne extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_one, container, false);

        Button btn = (Button) view.findViewById(R.id.button_Ok);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

//                Pattern p;
//                Matcher m;
//                String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//                p = Pattern.compile(EMAIL_STRING);
//
//                EditText phone = (EditText) v.findViewById(R.id.text_Phone);
//                EditText email = (EditText) v.findViewById(R.id.text_Email);
//                TextView errorText = (TextView)v.findViewById(R.id.lbl_Error);
//
//                String phoneNumber = phone.getText().toString();
//                String emailAddress = email.getText().toString();
//                boolean validationFlag = false;
//                m = p.matcher(emailAddress);

                //Verify if the fields are not empty.
//                if(!phoneNumber.isEmpty() && !emailAddress.isEmpty()) {
//                    if(m.matches()) {
//                        validationFlag = true;
//
//                    }
//                    else
//                    {
//                        errorText.setVisibility(View.VISIBLE);
//                        errorText.setText("Please enter valid Email");
//                    }
//                }
//                if(!validationFlag)
//                {
//                    errorText.setVisibility(View.VISIBLE);
//                }
//                else
//                {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    transaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
                    transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), new FragmentSignupTwo());
                    transaction.addToBackStack(null);
                    transaction.commit();
//                }
//
           }
        });

        return view;
    }
}
