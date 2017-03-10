package com.ase.team22.ihealthcare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPersonalDetails extends Fragment {


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
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
//                transaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
//                transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), new FragmentPersonalDetails());
//                transaction.commit();

                Intent intent = new Intent(getActivity(),Home.class);
                startActivity(intent);
            }
        });

        return  view;
    }

}
