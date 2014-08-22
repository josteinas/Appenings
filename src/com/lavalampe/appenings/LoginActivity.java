package com.lavalampe.appenings;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.lavalampe.appenings.networking.AsyncPost;
import com.lavalampe.appenings.networking.AsyncPostCallback;
import com.lavalampe.appenings.networking.PostResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private static final String TAG = LoginActivity.class.getSimpleName();
	private TextView registerScreen;
	private Button loginButton;
	private EditText usernameInput, passwordInput;
	public static final String sessionPref = "SessionPref";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.activity_login);

		registerScreen = (TextView) findViewById(R.id.link_to_register);
		loginButton = (Button) findViewById(R.id.btnLogin);
		usernameInput = (EditText) findViewById(R.id.log_username);
		passwordInput = (EditText) findViewById(R.id.log_password);

		// Listening to register new account link
		registerScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(i);
			}
		});

		// Listening to register new account link
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG, "Login button clicked");
				doLogin();
			}

		});
	}
	
	private class OnLoginResponse implements AsyncPostCallback{

		@Override
		public void onPostComplete(PostResult postResult) {
			PostResult result = postResult;
			HttpResponse response = result.response;
			JSONObject jsonResponse = Utils.httpResponseToJSON(response);
			boolean foundSession = false;
			try {
				if(jsonResponse.getBoolean("success")) {
					List<Cookie> cookies = result.cookies;
					Log.d(TAG, String.format("Found %d cookies", cookies.size()));
					for (Cookie cookie : cookies) {
						Log.d(TAG, "found cookie: " + cookie.toString() + " with name: " + cookie.getName());
						if(cookie.getName().equalsIgnoreCase("JSessionId")) {
							SharedPreferences pref = getApplicationContext().getSharedPreferences(LoginActivity.sessionPref, Context.MODE_PRIVATE);
							Editor editor = pref.edit();
							editor.putString("SessionID", cookie.getValue());
							editor.putString("username", jsonResponse.getString("username"));
							editor.commit();
							foundSession = true;
						}
					}				
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if(foundSession){
				SharedPreferences pref = getApplicationContext().getSharedPreferences("SessionPref", 0);
				Log.d("sessionID", pref.getString("SessionID", "none"));
				Log.d("username", pref.getString("username", "none"));
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(i);
			}
		}
					
				
			
		
		
	}

	private void doLogin() {
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		String serverUrl = getString(R.string.serverUrl) + "/ValidateUser";
		
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new BasicNameValuePair("username", username);
		params[1] = new BasicNameValuePair("password", password);
		
		AsyncPost post = new AsyncPost(serverUrl, new OnLoginResponse());
		post.execute(params);
	}
}
