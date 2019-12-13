package com.example.hitesh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static JSONObject jsonObject;
    TextView UserName;
    TextView UserLocation;
    TextView Rides;
    TextView FreeRides;
    TextView Credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName = findViewById(R.id.usertitle);
        UserLocation= findViewById(R.id.userLocation);
        Rides = findViewById(R.id.Rides);
        FreeRides = findViewById(R.id.freerides);
        Credits = findViewById(R.id.credits);
        Log.e(TAG, "onCreate: api done" );
        Log.e(TAG, "onCreate:" + jsonObject );
        try {
            jsonObject = jsonObject.getJSONObject("data");
            JSONObject profile = jsonObject.getJSONObject("profile");
            String username =profile.getString("first_name") + " "+ profile.getString("last_name");
            UserName.setText(username);
            String userloc= profile.getString("city") + "," + profile.getString("Country");
            UserLocation.setText(userloc);
            JSONObject stats = jsonObject.getJSONObject("stats");
            Rides.setText(stats.getString("rides"));
            FreeRides.setText(stats.getString("free_rides"));
            JSONObject credits = stats.getJSONObject("credits");
            String amount =credits.getString("currency_symbol") + credits.getString("value");
            Credits.setText(amount);

            JSONArray trips = jsonObject.getJSONArray("trips");


            RecyclerView recyclerView = findViewById(R.id.cardRecycle);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CustomAdapter(this,trips));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: "+ e.toString() );
        }





    }
}
