package com.ssimone94.listaspesasano;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Server {
	private static final String URL_ROOT = "http://toushare.altervista.org";
	private static final String URL_SHELL = URL_ROOT.concat("/spesasano.php");
	
	/**
	 * @return {"lista1":["entry11~mul~0","entry12~mul~1",...],"lista2":...}
	 */
	public static JSONObject getListe(){
		return getResponse(URL_SHELL+"?liste");
	}
	
	public static JSONObject getLista(String lista){
		return getResponse(URL_SHELL+"?lista="+lista);
	}
	
	private static JSONObject getResponse(String link){
		List<String> response = sendRequest(link);
		StringBuilder json = new StringBuilder();
		
		for(String line : response)
			json.append(line);
		
		try {
			return new JSONObject(json.toString());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static List<String> sendRequest(String link) {
		List<String> list = new ArrayList<String>();
		link = link.replace(" ", "%20");
		try {
			BufferedReader in;
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(link));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent(), "UTF-8"));
			list = stringListFromBuffer(in); // just user ID
		} catch (MalformedURLException exc) {
			list.add("{ERROR=100}");
		} catch (URISyntaxException exc) {
			list.add("{ERROR=101}");
		} catch (UnknownHostException exc) { // Connection off (or Server)
			Log.e("HostError", exc.getMessage());
			list.add("{ERROR=112}");
		} catch (IOException exc) {
			list.add("{ERROR=102}");
		} catch (Exception e){
			Log.e("UnknownError", e.getMessage());
		}
		return list;
	}
	
	private static List<String> stringListFromBuffer(BufferedReader br) {
		List<String> list = new ArrayList<String>();

		try {
			String line = "";
			while ((line = br.readLine()) != null)
				list.add(line);
			br.close();
		} catch (IOException exc) { }

		return list;
	}
}
