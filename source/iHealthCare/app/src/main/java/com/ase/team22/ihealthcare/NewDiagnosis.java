package com.ase.team22.ihealthcare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ase.team22.ihealthcare.helpers.InfermedicaRESTClient;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.Item;
import com.ase.team22.ihealthcare.jsonmodel.RequestJSONInfermedica;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;
import com.ase.team22.ihealthcare.jsonparsers.Deserializer;
import com.ase.team22.ihealthcare.jsonparsers.Serializer;
import com.ase.team22.ihealthcare.questions.GroupMultiple;
import com.ase.team22.ihealthcare.questions.GroupSingle;
import com.ase.team22.ihealthcare.questions.QuestionInitiatorFragment;
import com.ase.team22.ihealthcare.questions.Single;

import java.util.ArrayList;
import java.util.List;

public class NewDiagnosis extends AppCompatActivity
        implements Single.OnFragmentInteractionListener,
        GroupMultiple.OnFragmentInteractionListener,
        GroupSingle.OnFragmentInteractionListener,
        QuestionInitiatorFragment.OnFragmentInteractionListener {

    private Button nextQuestion;
    private ArrayList<Condition> conditions = new ArrayList();
    private int identifier;
    //private Stack<String> mTransactionStack = new Stack<>();
    private static int tagIdentifier=0;
    //temporary identifiers for testing
    private int age = 19;
    private String sex = "male";
    private String[] progress = {"We are Consulting your doctor...","Almost Ready","Done!"};
    private ResponseJSONInfermedica responseJSONInfermedica;
    private RequestJSONInfermedica requestJSONInfermedica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diagnosis);
        nextQuestion = (Button) findViewById(R.id.btn_next);
        nextQuestion.setVisibility(View.INVISIBLE);
        String tag = QuestionInitiatorFragment.tag+tagIdentifier;
        QuestionInitiatorFragment questionInitiatorFragment = new QuestionInitiatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
        transaction.add(R.id.fragment_container, questionInitiatorFragment,tag);
        transaction.addToBackStack(tag);
        transaction.commit();


    }

    @Override
    public void onFragmentInteraction(ArrayList<Condition> conditions,int identifier) {
        this.identifier = identifier;
        //this is temperory implementation to test navigation and UI rendering
        if(identifier == 0){
            nextQuestion.setVisibility(View.VISIBLE);
            this.conditions.addAll(conditions);
            requestJSONInfermedica = new RequestJSONInfermedica();
            requestJSONInfermedica.setAge(this.age);
            requestJSONInfermedica.setSex(this.sex);
            requestJSONInfermedica.setCondition(this.conditions);
            String seralizedJSON = Serializer.parseToJSON(requestJSONInfermedica);
            new ResponseThread().execute(seralizedJSON);
        }
        else if(identifier == 3){
            this.conditions.addAll(conditions);
        }
        else{
            enableNextButton();
            this.conditions.addAll(conditions);
        }
    }

    public void enableNextButton(){
        nextQuestion.setBackgroundColor(getResources().getColor(R.color.md_blue_600));
        nextQuestion.setEnabled(true);
    }
    public void disableNextButton(){
        nextQuestion.setBackgroundColor(getResources().getColor(R.color.md_grey_100));
        nextQuestion.setEnabled(false);
    }
    /*
     * Depending on the question type in each API response one of the below methods will be called.
     */
    public void renderSingleQuestionFragment(ResponseJSONInfermedica responseJSONInfermedica){
        Single singleQuestionFragment = Single.newInstance(responseJSONInfermedica);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        tagIdentifier++;
        String tag = Single.tag+tagIdentifier;
        transaction.replace(R.id.fragment_container, singleQuestionFragment,tag);
        disableNextButton();
        transaction.addToBackStack(tag);
        transaction.commit();

        /*getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag(mTransactionStack.peek())).commit();
        Log.i(this.getClass().getName(),"Stack after first peek: "+mTransactionStack);
        mTransactionStack.add(Single.tag);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(mTransactionStack.pop())).commit();
                Log.i(this.getClass().getName(),"Stack after first pop : "+mTransactionStack);
            }
        }, 1000);*/

    }

    public void renderGroupSingleQuestionFragment(ResponseJSONInfermedica responseJSONInfermedica){
        GroupSingle groupSingleQuestionFragment = GroupSingle.newInstance(responseJSONInfermedica);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        tagIdentifier++;
        String tag = GroupSingle.tag+tagIdentifier;
        transaction.replace(R.id.fragment_container, groupSingleQuestionFragment,tag);
        disableNextButton();
        transaction.addToBackStack(tag);
        transaction.commit();
    }
    // TODO  - Save state instance should be implemented
    public void renderGroupMultipleQuestionFragment(ResponseJSONInfermedica responseJSONInfermedica){
        GroupMultiple groupMultipleQuestionFragment = GroupMultiple.newInstance(responseJSONInfermedica);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        tagIdentifier++;
        String tag = GroupMultiple.tag+tagIdentifier;
        transaction.replace(R.id.fragment_container, groupMultipleQuestionFragment,tag);
        disableNextButton();
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    // TODO - all this impelementaion is temporary. render method should be called based on questiontype in returned json object.

    public void getNextQuestion(View view){
        requestJSONInfermedica = new RequestJSONInfermedica();
        requestJSONInfermedica.setAge(this.age);
        requestJSONInfermedica.setSex(this.sex);
        requestJSONInfermedica.setCondition(this.conditions);
        String seralizedJSON = Serializer.parseToJSON(requestJSONInfermedica);
        new ResponseThread().execute(seralizedJSON);
    }

    private class ResponseThread extends AsyncTask<String, String, String> {
        protected String doInBackground(String... request) {
            //to connect to api and fetch JSON.
            InfermedicaRESTClient infermedicaRESTClient = new InfermedicaRESTClient();
            publishProgress(progress[0]);
            String response = infermedicaRESTClient.continueDiagnosis(request[0]);
            publishProgress(progress[1]);
                return response;
            }

        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            //TODO - keep progress bar spinning and tell user to be patient if it is gonna take time.
        }

        protected void onPostExecute(String response) {
            renderQuestion(response);
            //TODO - render page to display next question.
        }
        protected void onPreExecute() {
            //TODO - set progress bar status here.
        }
    }

    public void renderQuestion(String response){
        responseJSONInfermedica = Deserializer.parseFromJSON(response);
        String questionType = responseJSONInfermedica.getQuestion().getType();
        switch(questionType){
            case "single" : renderSingleQuestionFragment(responseJSONInfermedica);
                break;
            case "group_single" : renderGroupSingleQuestionFragment(responseJSONInfermedica);
                break;
            case "group_multiple" : renderGroupMultipleQuestionFragment(responseJSONInfermedica);
                enableNextButton();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        tagIdentifier = 0;
        // Check if that Fragment is currently visible
        // boolean myFragXwasVisible = myFragment.isVisible();
        FragmentManager fm = getSupportFragmentManager();
        int totalQuestions = fm.getBackStackEntryCount();

        for(int i=totalQuestions-1;i>-1;i--){
            //Log.i(this.getClass().getName(),fm.getBackStackEntryAt(i).getName());
            fm.beginTransaction().remove(fm.findFragmentByTag(fm.getBackStackEntryAt(i).getName())).commit();
        }
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        // If it was your particular Fragment that was visible...
        /*if (myFragXwasVisible) {
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.remove(myFragment).commit;
        }*/
    }
}
