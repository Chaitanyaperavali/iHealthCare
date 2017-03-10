package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ase.team22.ihealthcare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupSingle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupSingle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupSingle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "jsonResponse";

    // TODO: Rename and change types of parameters
   private JSONObject jsonResponse;
    private RadioGroup radioGroup;
    private OnFragmentInteractionListener mListener;

    public GroupSingle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GroupSingle.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupSingle newInstance(JSONObject object) {
        GroupSingle fragment = new GroupSingle();
        String jsonString = object.toString();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,jsonString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                jsonResponse = new JSONObject(getArguments().getString(ARG_PARAM1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_single, container, false);
        int totalOptions = 0;
        try {
            radioGroup = (RadioGroup) getView().findViewById(R.id.radio_group);
            radioGroup.setOrientation(LinearLayout.VERTICAL);
            JSONObject options = (JSONObject) jsonResponse.getJSONObject("question").getJSONArray("items").get(0);
            JSONArray jsonArray = (JSONArray) options.getJSONArray("choices");
            totalOptions = jsonArray.length();
            for(int i=0;i<totalOptions;i++){
                RadioButton rb = new RadioButton(getContext());
                rb.setId(i);
                rb.setText(((JSONObject)jsonArray.get(i)).get("label").toString());
                radioGroup.addView(rb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }
}
