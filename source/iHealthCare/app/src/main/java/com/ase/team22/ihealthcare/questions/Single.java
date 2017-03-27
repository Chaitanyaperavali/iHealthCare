package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ase.team22.ihealthcare.R;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.Question;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Single.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Single#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Single extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String tag = "Single";
    private ArrayList<Condition> conditions = new ArrayList<>();

    // TODO: Rename and change types of parameters to view that hold question and options
    private static ResponseJSONInfermedica responseJSONInfermedica;
    private String selectedAnswer;
    private RadioButton radioButton ;
    private RadioGroup radioGroup;

    private OnFragmentInteractionListener mListener;
    public Single() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Single.
     */
    // TODO: Rename and change types and number of parameters
    public static Single newInstance(ResponseJSONInfermedica result) {
        Single fragment = new Single();
        responseJSONInfermedica = result;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            try {
                jsonResponse = new JSONObject(getArguments().getString(ARG_PARAM1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_single, container, false);
        TextView tv = (TextView) view.findViewById(R.id.single_question);
        tv.setText(responseJSONInfermedica.getQuestion().getText());
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = checkedId;
                radioButton = (RadioButton) view.findViewById(id);
                String ans = radioButton.getText().toString();
                if(ans.equalsIgnoreCase("yes")){
                    selectedAnswer = "present";
                }
                else if(ans.equalsIgnoreCase("no")){
                    selectedAnswer= "absent";
                }
                else
                    selectedAnswer = "unknown";
                Condition condition = new Condition();
                condition.setId(responseJSONInfermedica.getQuestion().getItems().get(0).getId());
                condition.setChoiceId(selectedAnswer);
                conditions.add(condition);
                mListener.onFragmentInteraction(conditions,1);

            }
        });

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
        void onFragmentInteraction(ArrayList<Condition> options, int identifier);
    }

}
