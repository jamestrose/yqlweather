package com.roseware.yqlweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {
	// Declare Variables
	String title;
	String description;
	String thumbnail;

	String day;
    String date;
    String high;
    String low;
    String text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);

		Intent i = getIntent();

        day = i.getStringExtra("day");
        date = i.getStringExtra("date");
        high = i.getStringExtra("high");
        low = i.getStringExtra("low");
        text = i.getStringExtra("text");

        // Locate the TextView in singleitemview.xml
		TextView txtday = (TextView) findViewById(R.id.day);
        TextView txtdate = (TextView) findViewById(R.id.date);
        TextView txthigh = (TextView) findViewById(R.id.high);
        TextView txtlow = (TextView) findViewById(R.id.low);
        TextView txttext = (TextView) findViewById(R.id.text);

        // Set results to the TextView
		txtdate.setText(date);
        txtday.setText(day + " ");
        txthigh.setText(high);
        txtlow.setText(low);
        txttext.setText(text);
    }
}
