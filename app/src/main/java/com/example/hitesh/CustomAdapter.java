package com.example.hitesh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    LayoutInflater inflater;
    JSONArray trips;
    private static final String TAG = "CustomAdapter";

    public CustomAdapter(Context context, JSONArray jsonObject) {
        this.context = context;
        inflater= LayoutInflater.from(context);
        this.trips = jsonObject;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.layout,viewGroup,false);
//        Log.e(TAG, "onCreateViewHolder: " + mjsonObject.toString() );
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        try {
            JSONObject trip = trips.getJSONObject(i);
            myViewHolder.loc_origin.setText(trip.getString("from"));
            myViewHolder.loc_destination.setText(trip.getString("to"));
            JSONObject credits = trip.getJSONObject("cost");
            String amount =credits.getString("currency_symbol") + credits.getString("value");
            myViewHolder.journey_cost.setText(amount);

            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,HH:mm", Locale.US);
            Date d = new Date(Long.parseLong(trip.getString("from_time")));

            Date d2 = new Date(Long.parseLong(trip.getString("to_time"))+(Long.parseLong(trip.getString("trip_duration_in_mins"))*60*1000));
            String dateString = formatter.format(d);
            String desdateString = formatter.format(d2);
            myViewHolder.or_time.setText(dateString);
            myViewHolder.des_time.setText(desdateString);

            Log.e(TAG, "onBindViewHolder: "+ dateString);
            Log.e(TAG, "onBindViewHolder: "+ desdateString);
            Log.e(TAG, "onBindViewHolder: "+ d);
            Log.e(TAG, "onBindViewHolder: "+ d2);

            journeyduration(myViewHolder.journey_duration,trip);










        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void journeyduration(TextView journey_duration, JSONObject trip) throws JSONException {

        int time = Integer.parseInt(trip.getString("trip_duration_in_mins"));
        int hr= time/60;
        int mins = time - hr*60;
        String traveltime = "Travel time:";
        if (hr!=0){

            traveltime = traveltime + String.valueOf(hr) +"h"+" "+String.valueOf(mins)+"min";

        }

        else {

            traveltime += String.valueOf(mins)+"min";

        }

        journey_duration.setText(traveltime);


    }

    private void setdescheck(TextView des_time, JSONObject i) throws JSONException {

        SimpleDateFormat sfd = new SimpleDateFormat("dd MMMM,HH:mm",Locale.US);

        des_time.setText(sfd.format(new Date(Long.parseLong(i.getString("to_time")))));
        Log.e(TAG, "setdescheck: " + i.toString() );


    }

    @Override
    public int getItemCount() {
        return trips.length();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder{

    TextView loc_origin;
    TextView loc_destination;
    TextView or_time;
    TextView des_time;
    TextView journey_cost;
    TextView journey_duration;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        loc_origin = itemView.findViewById(R.id.loc_origin);
        loc_destination=itemView.findViewById(R.id.loc_destination);
        or_time = itemView.findViewById(R.id.or_time);
        des_time = itemView.findViewById(R.id.des_time);
        journey_cost = itemView.findViewById(R.id.travel_cost);
        journey_duration=itemView.findViewById(R.id.journey_duration);
    }
}
