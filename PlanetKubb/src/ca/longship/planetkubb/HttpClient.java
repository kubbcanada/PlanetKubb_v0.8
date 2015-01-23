package ca.longship.planetkubb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class HttpClient extends Activity implements View.OnClickListener {


    String value, result;
    Button button;
    TextView jsonTextView;
    InputStream inputStream;
    ScrollView svJSON;
    String teamname[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpclient);
        jsonTextView = (TextView) findViewById(R.id.tJsontext);
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
        svJSON = (ScrollView) findViewById(R.id.svMatchDetails);

    }

    @Override
    public void onClick(View v) {
        value = "http://wiki.planetkubb.com/w/api.php?action=ask&query=%5B%5BCategory%3ATeam%5D%5D%7Csort%3DHas%20name%7Corder%3Dasc%7Climit%3D50%7Coffset%3D0&format=json";

        grabURL("http://wiki.planetkubb.com/w/api.php?action=ask&query=%5B%5BCategory%3ATeam%5D%5D%7Csort%3DHas%20name%7Corder%3Dasc%7Climit%3D50%7Coffset%3D0&format=json");

    }

    private class GrabURL extends AsyncTask<String, Void, Void> {



        private ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        private ProgressDialog Dialog = new ProgressDialog(HttpClient.this);

        protected void onPreExecute() {

            Dialog.setMessage("Sending value..");

            Dialog.show();

            nameValuePairs.add(new BasicNameValuePair("value",value));

            jsonTextView.setText("Did this part work?");


        }

        protected Void doInBackground(String... urls) {

            try{

                org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

                HttpGet httpget = new HttpGet(String.valueOf(value));
                HttpPost httppost = new HttpPost(String.valueOf(value));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();


            }catch(Exception e){

                Log.e("log_tag", "Error in http connection " + e.toString());

            }

            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    sb.append(line + "\n");
                result = sb.toString();
                jsonTextView.setText("It has read the data");

            } catch (Exception e) {
                Log.e("log_tag" , "Error converting result " + e.toString());
            }

            try{

                JSONObject rootobject = new JSONObject(result).getJSONObject("query").getJSONObject("results");
                String teamname = rootobject.getString("fulltext");
                jsonTextView.setText(teamname);

            } catch (JSONException e){
                Log.e("JSON Parser", Log.getStackTraceString(e));
                e.printStackTrace();
            }



            return null;


        }

        protected void onPostExecute(Void unused) {

            Dialog.dismiss();


        }

    }

    public void grabURL(String url){
        new GrabURL().execute(url);
        jsonTextView.setText("Did it get the URL?");
    }

}



