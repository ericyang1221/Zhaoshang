package com.rugao.zhaoshang.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * 
 * @author Eric
 * 
 */
public class HttpRequestHelper {
	private final String TAG = "HttpRequestHelper";

	public String sendRequestAndReturnString(String url) throws IOException {
		StringBuffer response = new StringBuffer();
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse hr = client.execute(new HttpGet(url));
			HttpEntity entity = hr.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String buff = null;
			while ((buff = br.readLine()) != null) {
				response.append(buff);
			}
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
			throw cpe;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		} finally {
			client.getConnectionManager().shutdown();
		}
		return response.toString();
	}

	public String sendPostRequestAndReturnString(String url,
			List<NameValuePair> params) {
		StringBuffer response = new StringBuffer();
		// HttpClient client = new DefaultHttpClient();
		HttpClient client = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse hr = client.execute(httpPost);
			HttpEntity entity = hr.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String buff = null;
			while ((buff = br.readLine()) != null) {
				response.append(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// client.getConnectionManager().shutdown();
		}
		return response.toString();
	}

	public JSONObject sendRequestAndReturnJson(String url)
			throws JSONException, IOException {
		JSONObject jsonObject = null;
		Log.d(TAG, url);
		String jsonStr = sendRequestAndReturnString(url);
		Log.d(TAG, jsonStr);
		jsonObject = new JSONObject(jsonStr);
		return jsonObject;
	}

	public JSONObject sendPostRequestAndReturnJson(String url,
			List<NameValuePair> params) {
		JSONObject jsonObject = null;
		try {
			Log.d(TAG, url);
			String res = sendPostRequestAndReturnString(url, params);
			Log.d(TAG, res);
			jsonObject = new JSONObject(res);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public JSONArray sendRequestAndReturnJsonArray(String url)
			throws JSONException, IOException {
		JSONArray jsonArray = null;
		Log.d(TAG, url);
		String jsonStr = sendRequestAndReturnString(url);
		Log.d(TAG, jsonStr);
		jsonArray = new JSONArray(jsonStr);
		return jsonArray;
	}
}
