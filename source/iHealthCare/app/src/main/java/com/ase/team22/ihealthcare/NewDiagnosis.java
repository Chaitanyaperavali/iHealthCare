package com.ase.team22.ihealthcare;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.ase.team22.ihealthcare.questions.GroupMultiple;
import com.ase.team22.ihealthcare.questions.GroupSingle;
import com.ase.team22.ihealthcare.questions.QuestionInitiatorFragment;
import com.ase.team22.ihealthcare.questions.Single;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class NewDiagnosis extends AppCompatActivity
        implements Single.OnFragmentInteractionListener,
        GroupMultiple.OnFragmentInteractionListener,
        GroupSingle.OnFragmentInteractionListener,
        QuestionInitiatorFragment.OnFragmentInteractionListener {

    private Button newDiagnosis;
    private List<Condition> conditions = new ArrayList();
    private Stack<String> mTransactionStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diagnosis);
        QuestionInitiatorFragment questionInitiatorFragment = new QuestionInitiatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.add(R.id.fragment_container, questionInitiatorFragment,QuestionInitiatorFragment.tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Condition[] conditions,int identifier) {
        if(conditions.length <= 1){
            //this is temperory implementation to test navigation and UI rendering
            if(identifier == 0){
                mTransactionStack.add(QuestionInitiatorFragment.tag);
                renderSingleQuestionFragment(null);

            }
            else if(identifier == 1){
                renderGroupSingleQuestionFragment(null);
            }
            else if(identifier == 2){
                renderGroupMultipleQuestionFragment(null);
            }
        }
        else {

        }
    }

    public void startDiagnosis(View view){
        view.setVisibility(View.INVISIBLE);
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
        transaction.add(R.id.fragment_container, singleQuestionFragment,Single.tag).commit();
        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag(mTransactionStack.peek())).commit();
        mTransactionStack.add(Single.tag);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(mTransactionStack.pop())).commit();
            }
        }, 1000);

    }

    public void renderGroupSingleQuestionFragment(JSONObject object){
        GroupSingle groupSingleQuestionFragment = GroupSingle.newInstance(object);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.replace(R.id.fragment_container, groupSingleQuestionFragment,GroupSingle.tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void renderGroupMultipleQuestionFragment(JSONObject object){
        GroupMultiple groupMultipleQuestionFragment = GroupMultiple.newInstance(object);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.replace(R.id.fragment_container, groupMultipleQuestionFragment,GroupMultiple.tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
