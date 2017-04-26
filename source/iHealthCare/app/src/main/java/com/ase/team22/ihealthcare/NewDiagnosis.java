package com.ase.team22.ihealthcare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ase.team22.ihealthcare.helpers.BetterDoctorRESTClient;
import com.ase.team22.ihealthcare.helpers.InfermedicaRESTClient;
import com.ase.team22.ihealthcare.jsonmodel.Condition;
import com.ase.team22.ihealthcare.jsonmodel.RequestJSONInfermedica;
import com.ase.team22.ihealthcare.jsonmodel.ResponseCondition;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONBetterDoctor;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;
import com.ase.team22.ihealthcare.jsonparsers.Deserializer;
import com.ase.team22.ihealthcare.jsonparsers.Serializer;
import com.ase.team22.ihealthcare.questions.DiagnosisReport;
import com.ase.team22.ihealthcare.questions.GroupMultiple;
import com.ase.team22.ihealthcare.questions.GroupSingle;
import com.ase.team22.ihealthcare.questions.QuestionInitiatorFragment;
import com.ase.team22.ihealthcare.questions.Single;

import java.util.ArrayList;

public class NewDiagnosis extends AppCompatActivity implements Single.OnFragmentInteractionListener,
        GroupMultiple.OnFragmentInteractionListener, GroupSingle.OnFragmentInteractionListener,
        QuestionInitiatorFragment.OnFragmentInteractionListener, DiagnosisReport.OnFragmentInteractionListener {
    private Button nextQuestion;
    private ArrayList<Condition> conditions = new ArrayList<>();
    private ArrayList<Condition> tempConditions;
    private int identifier;
    private static int tagIdentifier = 0;
    private int age = 56;
    private String sex = "female";
    private String[] progress = { "We are Consulting your doctor...", "Almost Ready", "Done!" };
    private ResponseJSONInfermedica responseJSONInfermedica;
    private RequestJSONInfermedica requestJSONInfermedica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diagnosis);
        nextQuestion = (Button) findViewById(R.id.btn_next);
        nextQuestion.setVisibility(View.INVISIBLE);
        String tag = QuestionInitiatorFragment.tag + tagIdentifier;
        QuestionInitiatorFragment questionInitiatorFragment = new QuestionInitiatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, questionInitiatorFragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(ArrayList<Condition> conditions, int identifier) {
        this.identifier = identifier;
        if (identifier == 0) {
            nextQuestion.setVisibility(View.VISIBLE);
            this.conditions = conditions;
            requestJSONInfermedica = new RequestJSONInfermedica();
            requestJSONInfermedica.setAge(this.age);
            requestJSONInfermedica.setSex(this.sex);
            requestJSONInfermedica.setCondition(this.conditions);
            String seralizedJSON = Serializer.parseToJSON(requestJSONInfermedica);
            new ResponseThread().execute(seralizedJSON);
        } else if(identifier != 4){
            enableNextButton();
            this.tempConditions = conditions;
        }
    }

    @Override
    public void onFragmentInteraction(String condition, int identifier) {
        if (identifier == 4) {
            // TODO - get user current location and assign to lat and lng
            String lat = "39.0997270";
            String lng = "-94.5785670";
            BetterDoctorRESTClient betterDoctorRESTClient = new BetterDoctorRESTClient();
            String responseBDResponseJson = betterDoctorRESTClient.getNearByDoctors(condition,lat,lng);
            ResponseJSONBetterDoctor responseJSONBetterDoctor = Deserializer.parseFromBDApiResponse(responseBDResponseJson);
            //TODO uncomment below lines of code after making pulling from github(Navya, Sindhu)
           Intent intent = new Intent(this,MapsActivity.class);
            intent.putExtra("response_data",responseJSONBetterDoctor);
            startActivity(intent);

        }
        else{
            enableNextButton();
            this.tempConditions = conditions;

        }
    }

    public void enableNextButton() {
        nextQuestion.setBackgroundColor(getResources().getColor(R.color.md_blue_600));
        nextQuestion.setEnabled(true);
    }

    public void disableNextButton() {
        nextQuestion.setBackgroundColor(getResources().getColor(R.color.md_grey_100));
        nextQuestion.setEnabled(false);
    }

    /*
     * Depending on the question type in each API response one of the below
     * methods will be called.
     */
    public void renderSingleQuestionFragment(ResponseJSONInfermedica responseJSONInfermedica) {
        Single singleQuestionFragment = Single.newInstance(responseJSONInfermedica);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        tagIdentifier++;
        String tag = Single.tag + tagIdentifier;
        transaction.replace(R.id.fragment_container, singleQuestionFragment, tag);
        disableNextButton();
        transaction.addToBackStack(tag);
        transaction.commit();

    }

    public void renderGroupSingleQuestionFragment(ResponseJSONInfermedica responseJSONInfermedica) {
        GroupSingle groupSingleQuestionFragment = GroupSingle.newInstance(responseJSONInfermedica);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        tagIdentifier++;
        String tag = GroupSingle.tag + tagIdentifier;
        transaction.replace(R.id.fragment_container, groupSingleQuestionFragment, tag);
        disableNextButton();
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void renderGroupMultipleQuestionFragment(ResponseJSONInfermedica responseJSONInfermedica) {
        GroupMultiple groupMultipleQuestionFragment = GroupMultiple.newInstance(responseJSONInfermedica);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        tagIdentifier++;
        String tag = GroupMultiple.tag + tagIdentifier;
        transaction.replace(R.id.fragment_container, groupMultipleQuestionFragment, tag);
        disableNextButton();
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void getNextQuestion(View view) {
        boolean continueDiagnosis = false;
        this.conditions.addAll(tempConditions);
        tempConditions = null;
        ResponseCondition responseCondition = responseJSONInfermedica.getConditions().get(0);
        if (responseJSONInfermedica != null) {
            if (responseCondition.getProbability() <= 0.8 && tagIdentifier <= 12) {
                continueDiagnosis = true;
            } else {
                getReport(responseCondition);
            }
        }
        if (continueDiagnosis) {
            requestJSONInfermedica = new RequestJSONInfermedica();
            requestJSONInfermedica.setAge(this.age);
            requestJSONInfermedica.setSex(this.sex);
            requestJSONInfermedica.setCondition(this.conditions);
            String seralizedJSON = Serializer.parseToJSON(requestJSONInfermedica);
            new ResponseThread().execute(seralizedJSON);
        }
    }

    private void getReport(ResponseCondition responseCondition) {
        if (tagIdentifier > 12) {
            // Ask user if user want to continue the diagnosis as we are not
            // able to arrive at final condition
            Log.i(this.getClass().getName(), "We reached 12 questions so abandon diagnosis");
            onBackPressed();
        } else {
            // We arrived at probable condition... create report to show user.
            nextQuestion.setVisibility(View.INVISIBLE);
            DiagnosisReport diagnosisReport = DiagnosisReport.newInstance(responseCondition);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            tagIdentifier++;
            String tag = DiagnosisReport.tag + tagIdentifier;
            transaction.replace(R.id.fragment_container, diagnosisReport, tag);
            disableNextButton();
            transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    private class ResponseThread extends AsyncTask<String, String, String> {
        protected String doInBackground(String... request) {
            // to connect to api and fetch JSON.
            InfermedicaRESTClient infermedicaRESTClient = new InfermedicaRESTClient();
            String response = infermedicaRESTClient.continueDiagnosis(request[0]);
            return response;
        }

        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            // TODO - keep progress bar spinning and tell user to be patient if
            // it is gonna take time(Chaitanya)
        }

        protected void onPostExecute(String response) {
            renderQuestion(response);
        }

        protected void onPreExecute() {

        }
    }

    public void renderQuestion(String response) {
        responseJSONInfermedica = Deserializer.parseFromJSON(response);
        String questionType = responseJSONInfermedica.getQuestion().getType();
        switch (questionType) {
            case "single":
                renderSingleQuestionFragment(responseJSONInfermedica);
                break;
            case "group_single":
                renderGroupSingleQuestionFragment(responseJSONInfermedica);
                break;
            case "group_multiple":
                renderGroupMultipleQuestionFragment(responseJSONInfermedica);
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

        for (int i = totalQuestions - 1; i > -1; i--) {
            // Log.i(this.getClass().getName(),fm.getBackStackEntryAt(i).getName());
            fm.beginTransaction().remove(fm.findFragmentByTag(fm.getBackStackEntryAt(i).getName())).commit();
        }
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        // If it was your particular Fragment that was visible...
		/*
		 * if (myFragXwasVisible) { FragmentTransaction trans =
		 * fragMan.beginTransaction(); trans.remove(myFragment).commit; }
		 */
    }
}
