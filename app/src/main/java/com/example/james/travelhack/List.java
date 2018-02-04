package com.example.james.travelhack;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class List extends AppCompatActivity {

    private static final String REQUESTTAG = "string request first";
    private Button btnNext;
    private int timeAtLocation = 7200;
    String[] locations;
    String[] arrival = new String[3];
    String[] leave = new String[3];
    String[] duration = new String[3];
    private TextView tvLeave1, tvLeave2, tvLeave3;
    private TextView tvArrival1, tvArrival2, tvArrival3;
    private TextView tvDuration1, tvDuration2, tvDuration3;
    private TextView tvLocation1, tvLocation2, tvLocation3;


    private int currTime;
    private String TAG = MainActivity.class.getName();
    private int departureTime = 1517942615;
    private String departure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        String[] selections = i.getStringArrayExtra("selections");
        locations = selections;
        new JsonTask1().execute(createURL(locations[0], locations[1], departureTime));

    }

    private class JsonTask1 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                currTime = (int)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("arrival_time").get("value");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                duration[0] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                arrival[0] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("arrival_time").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                departure = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("departure_time").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            currTime += timeAtLocation;

            new JsonTask2().execute(createURL(locations[1], locations[2], currTime));

        }
    }//Json Task 1


    /****************************************************************************************************/


    private class JsonTask2 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                currTime = (int)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("arrival_time").get("value");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                duration[1] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                arrival[1] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("arrival_time").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                leave[0] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("departure_time").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            currTime += timeAtLocation;

            new JsonTask3().execute(createURL(locations[2], locations[3], currTime));


        }
    }//Json Task 2


    /***************************************************************/

    private class JsonTask3 extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                currTime = (int)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("arrival_time").get("value");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                duration[2] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                arrival[2] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("arrival_time").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                leave[1] = (String)object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("departure_time").get("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.i(TAG, "Leave home at: " + departure);
            Log.i(TAG, "Bus Ride for: " + duration[0]);
            Log.i(TAG, "Arrive at Location 1: " + arrival[0]);
            Log.i(TAG, "Leave Location 1 at: " + leave[0]);
            Log.i(TAG, "Bus Ride for: " + duration[1]);
            Log.i(TAG, "Arrive at Location 2: " + arrival[1]);
            Log.i(TAG, "Leave Location 2 at: " + leave[1]);
            Log.i(TAG, "Bus Ride for: " + duration[2]);
            Log.i(TAG, "Arrive at Location 3: " + arrival[2]);

            tvLeave1 = (TextView)findViewById(R.id.tvLeave0);
            tvLeave1.setText("Leave home at " + departure);
            tvLeave2 = (TextView)findViewById(R.id.tvLeave1);
            tvLeave2.setText("Leave at " + leave[0]);
            tvLeave3 = (TextView)findViewById(R.id.tvLeave2);
            tvLeave3.setText("Leave at " + leave[1]);

            tvArrival1 = (TextView)findViewById(R.id.tvArrival1);
            tvArrival1.setText("Arrive at: " + arrival[0]);
            tvArrival2 = (TextView)findViewById(R.id.tvArrival2);
            tvArrival2.setText("Arrive at: " + arrival[1]);
            tvArrival3 = (TextView)findViewById(R.id.tvArrival3);
            tvArrival3.setText("Arrive at: " + arrival[2]);

            tvDuration1 = (TextView)findViewById(R.id.tvDuration1);
            tvDuration1.setText("Duration: " + duration[0]);
            tvDuration2 = (TextView)findViewById(R.id.tvDuration2);
            tvDuration2.setText("Duration: " + duration[1]);
            tvDuration3 = (TextView)findViewById(R.id.tvDuration3);
            tvDuration3.setText("Duration: " + duration[2]);

            tvLocation1 = (TextView)findViewById(R.id.tvPlace1);
            tvLocation1.setText(locations[0]);
            tvLocation2 = (TextView)findViewById(R.id.tvPlace2);
            tvLocation2.setText(locations[1]);
            tvLocation3 = (TextView)findViewById(R.id.tvPlace3);
            tvLocation3.setText(locations[2]);







        }
    }//Json Task 3




    private String createURL(String origin, String destination, int departureTime) {

        try {

            origin = URLEncoder.encode(origin, "UTF-8");
            destination = URLEncoder.encode(destination, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "https://maps.googleapis.com/maps/api/directions/json?origin=" + origin + "&destination=" + destination + "&departure_time=" + departureTime + "&mode=transit&key=AIzaSyBU8yxgy0MpZD3DXkkf_c5Cm8fIEikM3i4";
    }

}
