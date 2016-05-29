package com.ssimone94.listaspesasano;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

public class MainActivity extends Activity {
	
	private List<Lista> liste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getActionBar().setTitle("Spesa Sanò - Liste");
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		JSONObject liste = Server.getListe();
		JSONArray names = liste.names();
		this.liste = new ArrayList<Lista>();
		for(int i = 0; i < liste.length(); i++){
			try {
				List<Entry> entries = new LinkedList<Entry>();
				String lista = names.getString(i);
				JSONArray json = liste.getJSONArray(lista);
				for(int j = 0; j < json.length(); j++){
					String[] entry = json.getString(j).split("~");
					String desc = entry[0];
					int mult = Integer.parseInt(entry[1]);
					boolean check = Boolean.parseBoolean(entry[2]);
					entries.add(new Entry(desc, mult, check));
				}
				this.liste.add(new Lista(lista, entries));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
}
