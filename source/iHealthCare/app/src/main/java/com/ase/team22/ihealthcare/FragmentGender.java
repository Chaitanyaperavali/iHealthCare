package com.ase.team22.ihealthcare;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ase.team22.ihealthcare.othermodel.UserRegistration;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGender extends Fragment {


    private UserRegistration userRegistration;

    public FragmentGender() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gender, container, false);

        //When clicked on male Image Button
        ImageButton btnMale = (ImageButton) view.findViewById(R.id.img_Male);
        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRegistration.setGender("male");

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentPersonalDetails fragmentPersonalDetails = new FragmentPersonalDetails();

                //Passing user instance
                fragmentPersonalDetails.setUserInstance(userRegistration);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), fragmentPersonalDetails);
                transaction.commit();

//                Intent intent = new Intent(getActivity(),Home.class);
//                startActivity(intent);
            }
        });

        //When clicked on male Image Button
        ImageButton btnFemale = (ImageButton) view.findViewById(R.id.img_Female);
        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRegistration.setGender("female");

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentPersonalDetails fragmentPersonalDetails = new FragmentPersonalDetails();

                //Passing user instance
                fragmentPersonalDetails.setUserInstance(userRegistration);

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
                transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), fragmentPersonalDetails);
                //transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        return view;
    }

    public void setUserInstance(UserRegistration user){
        this.userRegistration = user;
    }
}
