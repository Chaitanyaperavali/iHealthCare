package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ase.team22.ihealthcare.R;
import com.ase.team22.ihealthcare.jsonmodel.Choice;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.Item;
import com.ase.team22.ihealthcare.jsonmodel.Question;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupSingle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupSingle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupSingle extends Fragment {

    public static final String tag =  "GroupSingle";
    private RadioGroup radioGroup;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Condition> conditions = new ArrayList<>();
    private static ResponseJSONInfermedica responseJSONInfermedica;
    private Map<Integer,String> map = new ArrayMap<>();
    public GroupSingle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GroupSingle.
     */
    public static GroupSingle newInstance(ResponseJSONInfermedica object) {
        GroupSingle fragment = new GroupSingle();
        responseJSONInfermedica = object;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_group_single, container, false);
            int totalOptions = 0;
            radioGroup = (RadioGroup)view.findViewById(R.id.radio_group);
            TextView qst = (TextView)view.findViewById(R.id.multiple_question_single);
            radioGroup.setOrientation(LinearLayout.VERTICAL);
            final Question question = responseJSONInfermedica.getQuestion();
            qst.setText(question.getText());
            final List<Item> items = question.getItems();;
            totalOptions = items.size();
            for(int i=0;i<totalOptions;i++){
                RadioButton rb = new RadioButton(getContext());
                //rb.setId(i);
                rb.setText(items.get(i).getName());
                radioGroup.addView(rb);
                map.put(rb.getId(),items.get(i).getId());
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    String conditionID = map.get(checkedId);
                    Condition condition = new Condition();
                    condition.setId(conditionID);
                    condition.setChoiceId("present");
                    if(conditions.size()>1){
                        conditions.remove(0);
                    }
                    conditions.add(condition);
                   // Log.i(tag,conditions.size()+"");
                    mListener.onFragmentInteraction(conditions,2);
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
        void onFragmentInteraction(ArrayList<Condition> options, int identifier);
    }
}
