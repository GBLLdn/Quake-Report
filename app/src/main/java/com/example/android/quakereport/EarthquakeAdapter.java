package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

public class EarthquakeAdapter extends ArrayAdapter <Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";


    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {

        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

        //Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());

        //Display the magnitude of the current earthquake
        magnitudeView.setText(formattedMagnitude);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);

        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);

        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        //Getting location String and storing it as a variable

        String originalLocation = currentEarthquake.getmLocation();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //Displaying first of 2 split views from the ID primary_location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        //Displaying second of 2 split views from the ID location_offset
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        //Set background for magnitude circle, which is from the TextView also a GradientDrawable.

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        //Get the right background color based on current earthquake magnitude

        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        //Set the colour on the magnitude background

        magnitudeCircle.setColor(magnitudeColor);

        int magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
        int magnitude2Color = ContextCompat.getColor(getContext(), R.color.magnitude2);
        int magnitude3Color = ContextCompat.getColor(getContext(), R.color.magnitude3);
        int magnitude4Color = ContextCompat.getColor(getContext(), R.color.magnitude4);
        int magnitude5Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
        int magnitude6Color = ContextCompat.getColor(getContext(), R.color.magnitude6);
        int magnitude7Color = ContextCompat.getColor(getContext(), R.color.magnitude7);
        int magnitude8Color = ContextCompat.getColor(getContext(), R.color.magnitude8);
        int magnitude9Color = ContextCompat.getColor(getContext(), R.color.magnitude9);
        int magnitude10PlusColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);



        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

     //Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.

   private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


    //Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.

    String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }

            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

        }
}

