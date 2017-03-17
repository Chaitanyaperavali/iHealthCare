package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ase.team22.ihealthcare.Condition;
import com.ase.team22.ihealthcare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupSingle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupSingle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupSingle extends Fragment {
    // TODO: Rename parameter argument
    // s, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "jsonResponse";
    public static final String tag =  "GroupSingle";
    // TODO: Rename and change types of parameters
   private JSONObject jsonResponse;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String selectedAnswer;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Condition> conditions = new ArrayList<>();

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
        // this is just a temporary JSON to test the code...it will be fetched from activity.
        final View view = inflater.inflate(R.layout.fragment_group_single, container, false);
        String jsonString = "{\n" +
                "  \"question\": {\n" +
                "    \"type\": \"single\",\n" +
                "    \"text\": \"Is your vision impaired?\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"id\": \"s_320\",\n" +
                "        \"name\": \"Impaired vision\",\n" +
                "        \"choices\": [\n" +
                "          {\n" +
                "            \"id\": \"present\",\n" +
                "            \"label\": \"Yes\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": \"absent\",\n" +
                "            \"label\": \"No\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": \"unknown\",\n" +
                "            \"label\": \"Don't know\"\n" +
                "          },\n" +
                "\t\t  {\n" +
                "            \"id\": \"option 4\",\n" +
                "            \"label\": \"may be\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"extras\": {}\n" +
                "  },\n" +
                "  \"conditions\": [...],\n" +
                "  \"extras\": {}\n" +
                "}";
        try {
            jsonResponse = new JSONObject(jsonString);
            int totalOptions = 0;
            radioGroup = (RadioGroup)view.findViewById(R.id.radio_group);
            radioGroup.setOrientation(LinearLayout.VERTICAL);
            final JSONObject options = (JSONObject) jsonResponse.getJSONObject("question").getJSONArray("items").get(0);
            final JSONArray jsonArray = options.getJSONArray("choices");
            totalOptions = jsonArray.length();
            //Log.i(this.getClass().getName(),"array size : "+jsonArray);
            for(int i=0;i<totalOptions;i++){
                RadioButton rb = new RadioButton(getContext());
                rb.setId(i);
                rb.setText(((JSONObject)jsonArray.get(i)).get("label").toString());
                radioGroup.addView(rb);
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int id = checkedId;
                    radioButton = (RadioButton)view.findViewById(id);
                    try {
                        selectedAnswer = ((JSONObject)jsonArray.get(id)).get("id").toString();
                        mListener.onFragmentInteraction(conditions,2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // TODO  - invoke mListener.onFragmentInteraction(conditions,2) here to let activity know about interaction with fragment.
                    //do same thing for all other

                }
            });
            /*btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Condition condition = new Condition();
                    condition.setChoiceId(selectedAnswer);
                    try {
                        condition.setId(options.getJSONArray("id").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Condition[] conditions = new Condition[1];
                    conditions[0] = condition;
                    mListener.onFragmentInteraction(conditions,2);
                }
            });*/
        } catch (JSONException e) {
            e.printStackTrace();
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
        void onFragmentInteraction(ArrayList<Condition> options, int identifier);
    }
}
