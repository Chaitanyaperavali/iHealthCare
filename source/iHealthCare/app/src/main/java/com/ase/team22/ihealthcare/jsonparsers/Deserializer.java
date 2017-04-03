package com.ase.team22.ihealthcare.jsonparsers;

import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONBetterDoctor;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    //TODO- create a static method in this class to parse JSON string from BetterDoctorAPI and map to java class. refer above method(Sindhu,Navya)
    public static ResponseJSONBetterDoctor parseFromBDApiResponse(String response){

        Gson gson = new GsonBuilder().create();
        //Log.i(tag,response.toString());
        ResponseJSONBetterDoctor responseJSON = gson.fromJson(response,ResponseJSONBetterDoctor.class);
        return responseJSON;
    }
}
