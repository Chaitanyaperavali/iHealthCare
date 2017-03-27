package com.ase.team22.ihealthcare.jsonparsers;

import android.util.Log;

import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by chaitanya on 26/03/2017.
 */

public class Deserializer {
    private static String tag = "Deserializer";
    public static ResponseJSONInfermedica parseFromJSON(String response){
        Gson gson = new GsonBuilder().create();
        //Log.i(tag,response.toString());
        ResponseJSONInfermedica responseJSON = gson.fromJson(response,ResponseJSONInfermedica.class);
        return responseJSON;
    }
}
