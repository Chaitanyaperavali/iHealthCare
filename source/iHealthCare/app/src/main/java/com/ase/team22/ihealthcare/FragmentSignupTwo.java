package com.ase.team22.ihealthcare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentSignupTwo extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_two, container, false);
        Button btn = (Button) view.findViewById(R.id.button_Signup);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
                transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), new FragmentGender());
                transaction.addToBackStack(null);
                transaction.commit();

//                Intent intent = new Intent(getActivity(),Home.class);
//                startActivity(intent);

            }
        });
        return view;
    }
}
