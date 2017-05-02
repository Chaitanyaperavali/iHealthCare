package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import com.ase.team22.ihealthcare.R;
import com.ase.team22.ihealthcare.helpers.InfermedicaRESTClient;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.Mention;
import com.ase.team22.ihealthcare.jsonmodel.RequestNLP;
import com.ase.team22.ihealthcare.jsonmodel.ResponseNLPJson;
import com.ase.team22.ihealthcare.jsonparsers.Deserializer;
import com.ase.team22.ihealthcare.jsonparsers.Serializer;

import java.util.ArrayList;
import java.util.Set;

public class QuestionInitiatorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String tag = "QuestionInitiator";
    private ArrayList<Condition> conditions = new ArrayList<>();
    private Button btn;
    private Button symptomBtn;
    private ListView listView;
    private ArrayList<Mention> mentions = null;

    private OnFragmentInteractionListener mListener;

    public QuestionInitiatorFragment() {

    }

    public static QuestionInitiatorFragment newInstance(String param1, String param2) {
        QuestionInitiatorFragment fragment = new QuestionInitiatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_question_initiator, container, false);
        listView  = (ListView) view.findViewById(R.id.lv_symptoms_holder);
        btn = (Button) view.findViewById(R.id.btn_next);
        CheckBox cb1 = (CheckBox) view.findViewById(R.id.s_13);
        CheckBox cb2 = (CheckBox) view.findViewById(R.id.s_21);
        CheckBox cb3 = (CheckBox) view.findViewById(R.id.s_98);
        CheckBox cb4 = (CheckBox) view.findViewById(R.id.s_241);
        CheckBox cb5 = (CheckBox) view.findViewById(R.id.s_285);
        CheckBox cb6 = (CheckBox) view.findViewById(R.id.s_1190);

        CheckBox[] cbs = {cb1,cb2,cb3,cb4,cb5,cb6};

        addOnClickListners(cbs);
        symptomBtn = (Button) view.findViewById(R.id.btn_fetch_symptoms);
        symptomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) view.findViewById(R.id.et_symptoms);
                String text = et.getText().toString();
                if(text != null){
                    new NLPparser().execute(text);
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(conditions,0);
            }
        });
        return view;
    }

    public class NLPparser extends AsyncTask<String,String,ResponseNLPJson>{

        @Override
        protected ResponseNLPJson doInBackground(String[] params) {
            RequestNLP requestNLP = new RequestNLP();
            requestNLP.setText(params[0]);
            String response = new InfermedicaRESTClient().parseNLP(Serializer.parseToJSON(requestNLP));
            ResponseNLPJson responseNLPJson =  Deserializer.parseFromInfermedicaNLP(response);
            return responseNLPJson;
        }

        @Override
        protected void onPostExecute(ResponseNLPJson responseNLPJson) {
           mentions = (ArrayList<Mention>) responseNLPJson.getMentions();
            createListView();
        }
    }

    public void createListView(){
        if(mentions != null){
            String[] listItems = new String[mentions.size()];

            for(int i = 0; i < mentions.size(); i++){
                Mention mention = mentions.get(i);
                Log.i(tag,i+" : "+mention.getName());
                listItems[i] = mention.getName();
            }
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, listItems);
            listView.setAdapter(adapter);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(ArrayList<Condition> conditions, int identifier);
    }

    public void addOnClickListners(CheckBox[] cbs){
        for(int i=0;i<cbs.length;i++){
            cbs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOrRemoveCondition(v);
                }
            });
        }
    }

    public void addOrRemoveCondition(View view){
        int viewID = view.getId();
        String id = "";
        switch(viewID){
            case R.id.s_13 : id = "s_13";
                break;
            case R.id.s_21 : id = "s_21";
                break;
            case R.id.s_98 : id = "s_98";
                break;
            case R.id.s_241: id = "s_241";
                break;
            case R.id.s_285: id = "s_285";
                break;
            case R.id.s_1190: id = "s_1190";
                break;
        }
            boolean flag = true;
            for (int i=0;i<conditions.size();i++) {
                //Log.i(tag,"this is conditions list size : "+conditions.size()+" : "+conditions.get(i).getId());
                if (conditions.get(i).getId().equals(id)) {
                    conditions.remove(i);
                    flag = false;
                    //Log.i(tag, "item removed");
                    break;
                }
            }
            if(flag){
                Condition condition = new Condition();
                condition.setId(id);
                condition.setChoiceId("present");
                Log.i(tag,condition.getId());
                conditions.add(condition);
                //Log.i(tag,"item added");
            }
            if(conditions.size()>0){
                btn.setBackgroundColor(getResources().getColor(R.color.md_blue_700));
                btn.setEnabled(true);
            }else{
                btn.setBackgroundColor(getResources().getColor(R.color.md_grey_100));
                btn.setEnabled(false);
            }

        }
    }
