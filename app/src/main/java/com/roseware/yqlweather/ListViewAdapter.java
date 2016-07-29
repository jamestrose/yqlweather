package com.roseware.yqlweather;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	public ListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		TextView txtday;
        TextView txtdate;
        TextView txthigh;
        TextView txtlow;
        TextView txttext;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);

		HashMap<String, String> resultp = new HashMap<String, String>();
		resultp = data.get(position);

		// Locate the TextView in listview_item.xml
        txtday = (TextView) itemView.findViewById(R.id.day);
		txtdate = (TextView) itemView.findViewById(R.id.date);

		// Capture position and set results to the TextViews
		txtday.setText(resultp.get("day" + " "));
        txtdate.setText(resultp.get("date"));


		// Capture button clicks on ListView items
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				HashMap<String, String> resultp = new HashMap<String, String>();

                // Get the position from the results
                resultp = data.get(position);

                // Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, SingleItemView.class);
				intent.putExtra("day", resultp.get(MainActivity.DAY));
				intent.putExtra("date", resultp.get(MainActivity.DATE));
                intent.putExtra("high", "high: " + resultp.get("high"));
                intent.putExtra("low", "low: " + resultp.get("low"));
                intent.putExtra("text", "Forecast: " + resultp.get("text"));

                // Start SingleItemView Class
				context.startActivity(intent);
			}
		});

		return itemView;
	}
}
