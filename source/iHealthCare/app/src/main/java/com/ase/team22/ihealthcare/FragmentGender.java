package com.ase.team22.ihealthcare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGender extends Fragment {


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

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), new FragmentPersonalDetails());
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

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right);
                transaction.replace(getActivity().findViewById(R.id.activity_register).getId(), new FragmentPersonalDetails());
                //transaction.addToBackStack(null);
                transaction.commit();

//                Intent intent = new Intent(getActivity(),Home.class);
//                startActivity(intent);
            }
        });

        return view;
    }

}
