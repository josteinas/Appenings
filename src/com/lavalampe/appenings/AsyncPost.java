package com.lavalampe.appenings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncPost extends AsyncTask<NameValuePair, Integer, PostResult> {
	
	private HttpPost httpPost;
	
	public AsyncPost(String serverUrl) {
		super();
		httpPost = new HttpPost(serverUrl);
	}

	@Override
	protected PostResult doInBackground(NameValuePair... params) {
		
		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {
			List<NameValuePair> nameValuePairs = Arrays.asList(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = httpClient.execute(httpPost);
			Log.d(LoginActivity.class.getSimpleName(), response.toString());
			
			List<Cookie> cookies = httpClient.getCookieStore().getCookies();
			
			return new PostResult(response, cookies);
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
