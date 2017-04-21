package com.ase.team22.ihealthcare.questions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ase.team22.ihealthcare.R;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.ResponseCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiagnosisReport.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiagnosisReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiagnosisReport extends Fragment {

    private static ResponseCondition responseCondition;
    private OnFragmentInteractionListener mListener;
    private TextView conditionTV;
    public static String tag = "DiagnosisReport";
    private TextView probabilityTV;
    public DiagnosisReport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DiagnosisReport.
     */
    public static DiagnosisReport newInstance(ResponseCondition res) {
        DiagnosisReport fragment = new DiagnosisReport();
        responseCondition = res;
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
        View view = inflater.inflate(R.layout.fragment_diagnosis_report, container, false);
        conditionTV = (TextView) view.findViewById(R.id.result_condition_sol);
        probabilityTV = (TextView) view.findViewById(R.id.result_probability_sol);
        conditionTV.setText(responseCondition.getName());
        probabilityTV.setText(responseCondition.getProbability()+"");
        final ArrayList<Condition> conditions = new ArrayList<>();
        //TODO - Pass condition to(Chaitanya)
        Button btn = (Button) view.findViewById(R.id.btn_doctors_near_by);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(responseCondition.getName(),4);
            }
        });
        //TODO - create an intent to connect to BetterDoctorAPI, get required details for this condition and open map activity(Sindhu, Navya)
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
        void onFragmentInteraction(String condition, int identifier);
    }
}
