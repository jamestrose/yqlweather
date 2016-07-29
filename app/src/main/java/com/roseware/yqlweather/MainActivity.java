package com.roseware.yqlweather;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	static String DAY = "day";
	static String DATE = "date";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.listview_main);
		// Execute DownloadJSON AsyncTask
		new DownloadJSON().execute();
	}

	// DownloadJSON AsyncTask
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Android Parsing YQL in JSON Tutorial");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			arraylist = new ArrayList<HashMap<String, String>>();
			String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
			try {
				// Retrive JSON Objects from the given URL in JSONfunctions.class
				JSONObject json_data = JSONfunctions.getJSONfromURL(url);
				JSONObject json_query = json_data.getJSONObject("query");
				JSONObject json_results = json_query.getJSONObject("results");
				JSONObject json_json_result = json_results.getJSONObject("channel");
				JSONObject json_item = json_json_result.getJSONObject("item");
				JSONArray jsona_forecast = json_item.getJSONArray("forecast");

				for (int i = 0; i < jsona_forecast.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					JSONObject forecast = jsona_forecast.getJSONObject(i);
					map.put("code", forecast.optString("code"));
					map.put("date", forecast.optString("date"));
					map.put("day", forecast.optString("day"));
					map.put("high", forecast.optString("high"));
					map.put("low", forecast.optString("low"));
					map.put("text", forecast.optString("text"));
					arraylist.add(map);
				}

			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(MainActivity.this, arraylist);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}
}