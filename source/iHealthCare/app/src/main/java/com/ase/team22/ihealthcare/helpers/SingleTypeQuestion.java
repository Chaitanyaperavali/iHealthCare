package com.ase.team22.ihealthcare.helpers;

import com.ase.team22.ihealthcare.Condition;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitanya on 11/03/2017.
 */

public class SingleTypeQuestion {

    private ArrayList<Condition> conditions;
    private JSONObject jsonObject;
    public SingleTypeQuestion(ArrayList<Condition> conditions){
        this.conditions = conditions;
    }

    public JSONObject getJsonObject(){
        return this.jsonObject;
    }

    public void setJsonObject(){
        //TODO - connect to the api and fetch new response by passing patient conditions
        this.jsonObject = null;
    }
}
