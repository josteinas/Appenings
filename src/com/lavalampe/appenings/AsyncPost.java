package com.lavalampe.appenings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncPost extends AsyncTask<NameValuePair, Integer, HttpResponse> {
	
	private HttpPost httpPost;
	
	public AsyncPost(String serverUrl) {
		super();
		httpPost = new HttpPost(serverUrl);
	}

	@Override
	protected HttpResponse doInBackground(NameValuePair... params) {
		
		HttpClient httpClient = new DefaultHttpClient();

		try {
			List<NameValuePair> nameValuePairs = Arrays.asList(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = httpClient.execute(httpPost);
			Log.d(LoginActivity.class.getSimpleName(), response.toString());
			return response;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
