package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ase.team22.ihealthcare.R;
import com.ase.team22.ihealthcare.jsonmodel.Choice;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.Item;
import com.ase.team22.ihealthcare.jsonmodel.Question;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupMultiple.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupMultiple#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupMultiple extends Fragment {
    public static final String tag = "GroupMultiple";
    private static ResponseJSONInfermedica responseJSONInfermedica;
    private ArrayList<Condition> conditions = new ArrayList<>();
    Map<Integer,String> map = new ArrayMap<>();
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
    public static GroupMultiple newInstance(ResponseJSONInfermedica object) {
        GroupMultiple fragment = new GroupMultiple();
        responseJSONInfermedica = object;
        Bundle args = new Bundle();
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
        Question question = responseJSONInfermedica.getQuestion();
        List<Item> items = question.getItems();
        TextView tv = (TextView) view.findViewById(R.id.multiple_question_group);
        tv.setText(question.getText());
        for(int i=0;i<items.size();i++){
            Item item = items.get(i);
            CheckBox ch = new CheckBox(getContext());
            ch.setId(i);
            ch.setText(item.getName());
            linearLayout.addView(ch);
            map.put(ch.getId(),item.getId());
            //Log.i(tag,map.toString());
            Condition condition = new Condition();
            condition.setId(item.getId());
            condition.setChoiceId("absent");
            conditions.add(condition);
            ch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOrRemoveCondition(v);
                }
            });
        }
        return view;
    }

    private void addOrRemoveCondition(View v) {
        //responseJSONInfermedica.getQuestion().getItems().get(itemIndex).getId();
        String condtionId = map.get(v.getId());
        for (int i=0;i<conditions.size();i++) {
            //Log.i(tag,"this is conditions list size : "+conditions.size()+" : "+conditions.get(i).getId());
            Condition condition = conditions.get(i);
            if (condition.getId().equals(condtionId)) {
                if(condition.getChoiceId().equals("absent")){
                    condition.setChoiceId("present");
                }else{
                    condition.setChoiceId("absent");
                }
            }
            //Log.i(tag,condition.getId()+" : "+condition.getChoiceId());
        }
        mListener.onFragmentInteraction(conditions,3);
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
        void onFragmentInteraction(ArrayList<Condition> conditions, int identifier);
    }
}
