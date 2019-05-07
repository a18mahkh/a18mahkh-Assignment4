package com.example.brom.listviewjsonapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Create a new class, Mountain, that can hold your JSON data

// Create a ListView as in "Assignment 1 - Toast and ListView"

// Retrieve data from Internet service using AsyncTask and the included networking code

// Parse the retrieved JSON and update the ListView adapter

// Implement a "refresh" functionality using Android's menu system


public class MainActivity extends AppCompatActivity {
    // private String[] mountainNames = {"Matterhorn","Mont Blanc","Denali"};
    //private String[] mountainLocations = {"Alps","Alps","Alaska"};
    //private int[] mountainHeights ={4478,4808,6190};
    // Create ArrayLists from the raw data above and use these lists when populating your ListView
    ListView myListView;
    //ArrayList<String> name_array = new ArrayList<String>();
    //ArrayList<String> location_array = new ArrayList<String>();
   private ArrayAdapter<Mountain> mountainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchData().execute();

       //Mountain m = new Mountain("K2");
        //Mountain m2 = new Mountain("Fuji", "Japan", 3776);


       //List<String> list=new ArrayList<String>(Arrays.asList(mountainNames));

        //TextView txt = (TextView) findViewById(R.id.myTextView);
        //txt.setText(((Mountain) m).info());*/

       mountainAdapter=new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview,R.id.my_item_textview);

        myListView = (ListView) findViewById(R.id.my_Listview);
        myListView.setAdapter(mountainAdapter);
        myListView.setDivider(null);


    }

    private class FetchData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.
            Log.d("brom", "dataFetched:" + o);

            //String response = o.toString();
            //List<String> listData = new ArrayList<String>();
            //  JSONArray new_array = new JSONArray(jsonStr);
         try {
             JSONArray new_array = new JSONArray(o);

             for (int i = 0; i < new_array.length(); i++) {
                 try {

                     // JSONArray jsonFile = new JSONArray(o);

                     JSONObject jsonObject = new_array.getJSONObject(i);

                     String inName = jsonObject.getString("name");
                     String inLocation= jsonObject.getString("location");
                     int inHeight= jsonObject.getInt("size");

                     Mountain mountains = new Mountain(inName, inLocation, inHeight);
                     mountainAdapter.add(mountains);


                     Log.d("Kancadius", mountains.info());
                     //name_array.add(jsonObject.getString("name").toString());
                    // location_array.add(jsonObject.getInt("location").parseInt());

                   //  ob1 = jsonFile.getString("name");


                 } catch (JSONException e) {
                     Log.e("brom", "E:" + e.getMessage());

                 }
                 // Implement a parsing code that loops through the entire JSON and creates objects
                 // of our newly created Mountain class.

                //ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview,R.id.my_item_textview, name_array);
                // myListView.setAdapter(adapter);

                 //Intent intent = new Intent(getApplicationContext(), MountainDetailsActivity.class);
                // intent.putExtra(name_array);



                 //startActivity(intent);



             }
         }catch (JSONException e) {
             // TODO Auto-generated catch block
            // e.printStackTrace();
             // tv.setText("error2");
         }

        }
    }
}

