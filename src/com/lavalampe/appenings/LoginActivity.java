package com.lavalampe.appenings;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

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
				if(doLogin()) {
					SharedPreferences pref = getApplicationContext().getSharedPreferences("SessionPref", 0);
					Log.d("sessionID", pref.getString("SessionID", "none"));
				}
				
			}

		});
	}

	private boolean doLogin() {
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		String serverUrl = getString(R.string.serverUrl) + "/ValidateUser";
		
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new BasicNameValuePair("username", username);
		params[1] = new BasicNameValuePair("password", password);
		
		AsyncPost post = new AsyncPost(serverUrl);
		
		try {
			PostResult result = post.execute(params).get();
			HttpResponse response = result.response;
			JSONObject jsonResponse = Utils.httpResponseToJSON(response);
			
			try {
				if(jsonResponse.getBoolean("success")) {
					List<Cookie> cookies = result.cookies;
					boolean foundSession = false;
					Log.d(TAG, String.format("Found %d cookies", cookies.size()));
					for (Cookie cookie : cookies) {
						Log.d(TAG, "found cookie: " + cookie.toString() + " with name: " + cookie.getName());
						if(cookie.getName().equalsIgnoreCase("JSessionId")) {
							SharedPreferences pref = getApplicationContext().getSharedPreferences("SessionPref", Context.MODE_PRIVATE);
							Editor editor = pref.edit();
							editor.putString("SessionID", cookie.getValue());
							editor.commit();
							foundSession = true;
						}
					}
					return foundSession;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return false;
	}
}
