package com.ase.team22.ihealthcare;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.ase.team22.ihealthcare.questions.GroupMultiple;
import com.ase.team22.ihealthcare.questions.GroupSingle;
import com.ase.team22.ihealthcare.questions.Single;

import org.json.JSONObject;

public class NewDiagnosis extends AppCompatActivity
        implements Single.OnFragmentInteractionListener,
        GroupMultiple.OnFragmentInteractionListener,
        GroupSingle.OnFragmentInteractionListener {

    private Button newDiagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diagnosis);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void startDiagnosis(View view){
        //TODO - This method should implement first API call after fetching basic user provided symptom info
        renderSingleQuestionFragment(new JSONObject());

    }

    /*
     * Depending on the question type in each API response one of the below methods will be called.
     */
    public void renderSingleQuestionFragment(JSONObject object){
        Single singleQuestionFragment = Single.newInstance(object);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.replace(R.id.fragment_container, singleQuestionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void renderGroupSingleQuestionFragment(JSONObject object){
        GroupSingle groupSingleQuestionFragment = GroupSingle.newInstance(object);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.replace(R.id.fragment_container, groupSingleQuestionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void renderGroupMultipleQuestionFragment(JSONObject object){
        GroupMultiple groupMultipleQuestionFragment = GroupMultiple.newInstance(object);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.replace(R.id.fragment_container, groupMultipleQuestionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
