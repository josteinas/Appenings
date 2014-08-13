package com.lavalampe.appenings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

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
				doLogin();
			}

		});
	}

	private void doLogin() {
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		String serverUrl = getString(R.string.serverUrl);
		
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new BasicNameValuePair("username", username);
		params[1] = new BasicNameValuePair("password", password);
		
		AsyncPost post = new AsyncPost(serverUrl);
		post.execute(params);
	}
}
