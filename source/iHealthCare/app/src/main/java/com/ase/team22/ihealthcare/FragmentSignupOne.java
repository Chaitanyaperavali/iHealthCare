package com.ase.team22.ihealthcare;


import android.content.Context;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.othermodel.UserRegistration;
import com.ase.team22.ihealthcare.questions.GroupMultiple;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentSignupOne extends Fragment {

    private EditText mEmailView;
    private EditText mPhoneView;
    private UserRegistration userRegistration;

    public static final String TAG = FragmentSignupOne.class.getName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_one, container, false);

        mEmailView = (EditText) view.findViewById(R.id.text_Email);

        mPhoneView = (EditText) view.findViewById(R.id.text_Phone);



        Button btn = (Button) view.findViewById(R.id.button_Ok);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                validateCredentials();


           }
        });

        return view;
    }

    public void setUserInstance(UserRegistration user){
        this.userRegistration = user;
    }

    private void validateCredentials() {

        // Reset errors.
        mEmailView.setError(null);
        mPhoneView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String phone = mPhoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid phone number, if the user entered one.
        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        }else if (!isPhoneValid(phone)){
            mPhoneView.setError(getString(R.string.error_invalid_phone));
            focusView = mPhoneView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            userRegistration.setEmail(this.mEmailView.getText().toString());
            userRegistration.setPhoneNumber(this.mPhoneView.getText().toString());

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentSignupTwo fragmentSignupTwo = new FragmentSignupTwo();

            //Passing user instance
            fragmentSignupTwo.setUserInstance(userRegistration);

            FragmentTransaction transaction = fragmentManager.beginTransaction();

//            Bundle args = new Bundle();
//            args.putString("EMAIL",email);
//            Fragment fr = new FragmentSignupTwo();
//            fr.setArguments(args);


            //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
            transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), fragmentSignupTwo);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length() == 10;
    }

}
