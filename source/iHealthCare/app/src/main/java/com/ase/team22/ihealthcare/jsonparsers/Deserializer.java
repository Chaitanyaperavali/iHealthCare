package com.ase.team22.ihealthcare.jsonparsers;

import android.util.Log;

import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONBetterDoctor;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;
import com.ase.team22.ihealthcare.jsonmodel.ResponseNLPJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chaitanya on 26/03/2017.
 */

public class Deserializer {
    private static String tag = "Deserializer";

    public static ResponseJSONInfermedica parseFromJSON(String response) {
        Gson gson = new GsonBuilder().create();
        //Log.i(tag,response.toString());
        ResponseJSONInfermedica responseJSON = gson.fromJson(response, ResponseJSONInfermedica.class);
        return responseJSON;
    }

    public static ResponseJSONBetterDoctor parseFromBDApiResponse(String response) {
        if (response != null) {
            Gson gson = new GsonBuilder().create();
            //Log.i(tag,response.toString());
            ResponseJSONBetterDoctor responseJSON = gson.fromJson(response, ResponseJSONBetterDoctor.class);
            //Log.i(tag,"This is response doctor data : "+responseJSON);
            return responseJSON;
        }
        return null;
    }

    public static ResponseNLPJson parseFromInfermedicaNLP(String response) {
        Log.i(tag,response);
        if (response != null) {
            Gson gson = new GsonBuilder().create();
            //Log.i(tag,response.toString());
            ResponseNLPJson responseJSON = gson.fromJson(response, ResponseNLPJson.class);
            //Log.i(tag,"This is response doctor data : "+responseJSON);
            return responseJSON;
        }
        return null;
    }
}
