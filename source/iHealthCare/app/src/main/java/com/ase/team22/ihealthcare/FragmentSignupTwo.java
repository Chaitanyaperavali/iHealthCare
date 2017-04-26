package com.ase.team22.ihealthcare;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.ase.team22.ihealthcare.othermodel.UserRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;


public class FragmentSignupTwo extends Fragment  {

    private EditText firstNameView;
    private EditText lastNameView;
    private EditText passwordView;
    private EditText confirmPasswordView;

    private UserRegistration userRegistration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_two, container, false);


        firstNameView = (EditText) view.findViewById(R.id.text_FirstName);
        lastNameView = (EditText) view.findViewById(R.id.text_LastName);
        passwordView = (EditText) view.findViewById(R.id.text_Password);
        confirmPasswordView = (EditText) view.findViewById(R.id.text_ConfirmPassword);
        //emailView = (EditText) view.findViewById(R.id.text_Email);

        //receiving the email from fragment one

        Button btn = (Button) view.findViewById(R.id.button_Ok);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                registerUser();


            }
        });
        return view;
    }

    public void setUserInstance(UserRegistration user){
        this.userRegistration = user;
    }

    public void registerUser() {

        // Reset errors.
        lastNameView.setError(null);
        firstNameView.setError(null);
        passwordView.setError(null);
        confirmPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String firstName = firstNameView.getText().toString();
        String lastName = lastNameView.getText().toString();
        String password = passwordView.getText().toString().trim();
        String confirmPassword = confirmPasswordView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Check for a first name, if the user entered one.
        if (TextUtils.isEmpty(firstName)) {
            firstNameView.setError(getString(R.string.error_field_required));
            focusView = firstNameView;
            cancel = true;
        }

        // Check for a last name, if the user entered one.
        if (TextUtils.isEmpty(lastName)) {
            lastNameView.setError(getString(R.string.error_field_required));
            focusView = lastNameView;
            cancel = true;
        }

        // Check for password field.
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        } else if ( isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for confirm password field.
        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordView.setError(getString(R.string.error_field_required));
            focusView = confirmPasswordView;
            cancel = true;
        }else if(!isPasswordEqual(password,confirmPassword)){
            confirmPasswordView.setError(getString(R.string.error_invalid_confirmPassword));
            //confirmPasswordView.setError(email);
            focusView = confirmPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {


            userRegistration.setFirstName(this.firstNameView.getText().toString());
            userRegistration.setLastName(this.lastNameView.getText().toString());
            userRegistration.setPassword(this.passwordView.getText().toString());


            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentGender fragmentGender = new FragmentGender();

            //Passing user instance
            fragmentGender.setUserInstance(userRegistration);

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
            transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), fragmentGender);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() < 8;
    }

    private boolean isPasswordEqual(String password,String confirmPassword){

        return password.equals(confirmPassword);
    }

}
