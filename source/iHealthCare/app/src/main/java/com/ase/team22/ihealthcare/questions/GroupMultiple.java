package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.ase.team22.ihealthcare.Condition;
import com.ase.team22.ihealthcare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupMultiple.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupMultiple#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupMultiple extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String tag = "GroupMultiple";
    // TODO: Rename and change types of parameters
    private JSONObject jsonResponse;
    private ArrayList<Condition> conditions = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public GroupMultiple() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GroupMultiple.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupMultiple newInstance(JSONObject object) {
        GroupMultiple fragment = new GroupMultiple();
        Bundle args = new Bundle();
        if(object != null){
            String jsonString = object.toString();
            args.putString(ARG_PARAM1,jsonString);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*try {
                jsonResponse = new JSONObject(getArguments().getString(ARG_PARAM1));
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_multiple, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkBox_container);
        for(int i=0;i<5;i++){
            CheckBox ch = new CheckBox(getContext());
            ch.setText("Symptom : "+i);
            linearLayout.addView(ch);
        }
        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ArrayList<Condition> conditions, int identifier);
    }
}
