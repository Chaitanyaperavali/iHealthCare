package com.ase.team22.ihealthcare.jsonparsers;

import android.util.Log;

import com.ase.team22.ihealthcare.jsonmodel.RequestJSONInfermedica;
import com.ase.team22.ihealthcare.jsonmodel.RequestNLP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by chaitanya on 26/03/2017.
 * Serilaizer converts the java object into JSON object, which can be used to request API.
 */

public class Serializer {

    public static String parseToJSON(RequestJSONInfermedica request){
        Gson gson = new GsonBuilder().create();
        String builtJSONString = gson.toJson(request);
        //Log.i("Serializer",builtJSONString);
        return builtJSONString;
    }

    public static String parseToJSON(RequestNLP request){
        Gson gson = new GsonBuilder().create();
        String builtJSONString = gson.toJson(request);
        Log.i("Serializer",builtJSONString);
        return builtJSONString;
    }

}
