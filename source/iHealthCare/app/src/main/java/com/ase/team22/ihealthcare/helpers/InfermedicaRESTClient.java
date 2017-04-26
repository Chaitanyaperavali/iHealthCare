package com.ase.team22.ihealthcare.helpers;

import android.util.Log;

import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONInfermedica;
import com.ase.team22.ihealthcare.jsonparsers.Serializer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by chaitanya on 26/03/2017.
 * This is the most important and core class of application. All network related
 * activities(request data from API, send data to API, etc.,) happens here.
 */

public class InfermedicaRESTClient {
    private static final String urlString = " https://api.infermedica.com/v2/diagnosis";
    private static final String APP_ID = "214359d0";
    private static final String APP_KEY = "848b7f4da58ce85ce8e9f23addb3de2d";
    public String continueDiagnosis(String request){
        URL url = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            //Log.i(this.getClass().getName(),request);
            url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("App-Id",APP_ID);
            connection.setRequestProperty("App-Key",APP_KEY);
            // Open communications link (network traffic occurs here).
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();
            //connection.connect();
            int responseCode = connection.getResponseCode();
            //Log.i(this.getClass().getName(),"JSON response code : "+responseCode);
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
                BufferedReader bR = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                StringBuilder responseStrBuilder = new StringBuilder();
                while((line =  bR.readLine()) != null){

                    responseStrBuilder.append(line);
                }
                bR.close();

                result = responseStrBuilder.toString();
        }catch (Exception e){
            Log.e(this.getClass().getName(),e.getMessage()+" at : continueDiagnosis()");
        }
        finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e(this.getClass().getName(),"Error in closing stream at : continueDiagnosis()");
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}
