package com.lavalampe.appenings.networking;

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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;

import com.lavalampe.appenings.LoginActivity;

public class AsyncPost extends AsyncTask<NameValuePair, Integer, PostResult> {
	private static final String TAG = AsyncPost.class.getSimpleName();

	private HttpPost httpPost;
	private AsyncPostCallback callback;

	public AsyncPost(String serverUrl, AsyncPostCallback callback) {
		super();
		httpPost = new HttpPost(serverUrl);
		this.callback = callback;
	}

	public AsyncPost(String serverUrl) {
		super();
		httpPost = new HttpPost(serverUrl);
	}

	@Override
	protected PostResult doInBackground(NameValuePair... params) {
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 2000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		int timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

		try {
			List<NameValuePair> nameValuePairs = Arrays.asList(params);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpClient.execute(httpPost);
			Log.d(LoginActivity.class.getSimpleName(), response.toString());

			List<Cookie> cookies = httpClient.getCookieStore().getCookies();
			PostResult result = new PostResult(response, cookies);
			return result;
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, e.getMessage());
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		cancel(true);
		return null;
	}

	@Override
	protected void onPostExecute(PostResult result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (callback != null)
			callback.onPostComplete(result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		if (callback != null)
			callback.onError();
	}
	
	
	
	

}
