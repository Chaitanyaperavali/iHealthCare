package com.ase.team22.ihealthcare.helpers;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by chaitanya on 27/03/2017.
 */
public class BetterDoctorRESTClient {
    public String getNearByDoctors(String condition,String lat,String lng){
      /*  Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.betterdoctor.com")
                .appendPath("2016-03-01")
                .appendPath("doctors")
                .appendQueryParameter("query",condition)
                .appendQueryParameter("user_location",lat+","+lng)
                .appendQueryParameter("skip","0")
                .appendQueryParameter("limit","10")
                .appendQueryParameter("user_key","cf2f86f8145deaad67330c38fbce0c56").build();

        String urlString = builder.toString();
        Log.i(this.getClass().getName(),urlString);
        URL url = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;

        try {
            url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            Log.i(this.getClass().getName(),"JSON response code : "+responseCode);
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
            Log.e(this.getClass().getName(),result);
        }catch (Exception e){
            Log.e(this.getClass().getName(),e.getMessage()+" at : getNearByDoctors()");
        }
        finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e(this.getClass().getName(),"Error in closing stream at : getNearByDoctors()");
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
*/
        OkHttpClient client = new OkHttpClient();
        Response response = null;
        JSONObject jsonObject = null;
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.betterdoctor.com").newBuilder();
        urlBuilder.addPathSegment("2016-03-01")
                .addPathSegment("doctors")
                .addQueryParameter("query",condition)
                .addQueryParameter("user_location",lat+","+lng)
                .addQueryParameter("skip","0")
                .addQueryParameter("limit","10")
                .addQueryParameter("sort","distance-asc")
                .addQueryParameter("user_key","cf2f86f8145deaad67330c38fbce0c56");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
           response  = client.newCall(request).execute();
            //Log.i(this.getClass().getName(),response.body().string());
            String jsonString = response.body().string();
            jsonObject = new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
