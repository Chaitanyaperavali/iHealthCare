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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import com.ase.team22.ihealthcare.othermodel.UserRegistration;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPersonalDetails extends Fragment {


    private UserRegistration userRegistration;

    private DatePicker datePicker;
    private int day;
    private int month;
    private int year;
    private EditText heightView;
    private  EditText weightView;

    private OnFragmentInteractionListener mListener;

    public FragmentPersonalDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_personal_details, container, false);

        datePicker = (DatePicker) view.findViewById(R.id.datePicker_dob);
        heightView = (EditText) view.findViewById(R.id.text_height);
        weightView = (EditText) view.findViewById(R.id.text_weight);

        Button btnOk = (Button) view.findViewById(R.id.button_Signup);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth();
                year = datePicker.getYear();

                String height = heightView.getText().toString().trim();
                String weight = weightView.getText().toString().trim();

                userRegistration.setDateOfBirth(day+"/"+month+"/"+year);
                userRegistration.setHeight(height);
                userRegistration.setWeight(weight);

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
