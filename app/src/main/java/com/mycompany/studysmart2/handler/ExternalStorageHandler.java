package com.mycompany.studysmart2.handler;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anders on 26-11-2015.
 * External Storage handler
 * handles the communication to the database.
 */
public class ExternalStorageHandler {
    public static ExternalStorageHandler instance = new ExternalStorageHandler();
    private final String serverUrl = "http://fuelshare.byethost.com/index.php";
    private String enteredEmail;
    private String enteredPassword;
    private boolean loggedIn = false;

    public boolean checkUser(){
        return loggedIn;
    }

    public void connectToDb(String email, String password) {
        this.enteredEmail = email;
        this.enteredPassword = password;

        AsyncDataClass asyncRequestObject = new AsyncDataClass();

        asyncRequestObject.execute(serverUrl, enteredEmail, enteredPassword);
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override

        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);

            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(params[0]);

            String jsonResult = "";

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                nameValuePairs.add(new BasicNameValuePair("email", params[1]));

                nameValuePairs.add(new BasicNameValuePair("password", params[2]));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);

                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

            } catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return jsonResult;

        }

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            System.out.println("Resulted Value: " + result);

            if (result.equals("") || result == null) {

                System.out.println("not gonna happen");
                //conn failed

                return;

            }

            int jsonResult = returnParsedJsonObject(result);

            if (jsonResult == 0) {

                loggedIn = false;
                //invalid user or pass

                return;

            }

            if (jsonResult == 1) {

                loggedIn = true;
                //correct

            }
        }
//            try {
//                JSONArray Jarray = new JSONArray(result);
//                for (int i = 0; i < Jarray.length(); i++) {
//                    JSONObject Jasonobject = null;
//                    Jasonobject = Jarray.getJSONObject(i);
//
//                    //String id = Jasonobject.getString("id");
//                    String name = Jasonobject.getString("name");
//                    String db_detail = "";
//
//                    if (et.getText().toString().equalsIgnoreCase(name)) {
//                        db_detail = Jasonobject.getString("detail");
//                        break;
//                    }
//
//                }
//
//            } catch (Exception e) {
//                // TODO: handle exception
//                Log.e("log_tag", "Error parsing data " + e.toString());
//            }
//        }

        private StringBuilder inputStreamToString(InputStream is) {

            String rLine = "";

            StringBuilder answer = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {

                while ((rLine = br.readLine()) != null) {

                    answer.append(rLine);

                }

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            return answer;

        }

    }

    private int returnParsedJsonObject(String result){

        JSONObject resultObject = null;

        int returnedResult = 0;

        try {

            resultObject = new JSONObject(result);

            returnedResult = resultObject.getInt("success");

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return returnedResult;

    }

}
