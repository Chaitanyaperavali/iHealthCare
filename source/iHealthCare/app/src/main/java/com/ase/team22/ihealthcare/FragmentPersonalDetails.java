package com.ase.team22.ihealthcare;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import com.ase.team22.ihealthcare.othermodel.UserRegistration;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPersonalDetails extends Fragment {


    private EditText mHeightView;

    private UserRegistration userRegistration;

    private OnFragmentInteractionListener mListener;

    public FragmentPersonalDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_personal_details, container, false);

        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRegistration.setDateOfBirth("12/17/1991");
                userRegistration.setHeight("162");
                userRegistration.setWeight("65");

                mListener.onFragmentInteraction();



            }
        });

        return  view;
    }

    public void setUserInstance(UserRegistration user){
        this.userRegistration = user;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
