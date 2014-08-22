package com.lavalampe.appenings;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lavalampe.appenings.networking.AsyncPost;
import com.lavalampe.appenings.networking.AsyncPostCallback;
import com.lavalampe.appenings.networking.PostResult;

public class RegisterActivity extends Activity {

	private EditText usernameInput, emailInput, passwordInput,
			reenterPasswordInput;
	private Button signUpButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set View to register.xml
		setContentView(R.layout.activity_register);

		usernameInput = (EditText) findViewById(R.id.reg_username);
		emailInput = (EditText) findViewById(R.id.reg_email);
		passwordInput = (EditText) findViewById(R.id.reg_password);
		reenterPasswordInput = (EditText) findViewById(R.id.reg_reenter_password);
		signUpButton = (Button) findViewById(R.id.btnRegister);

		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

		loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Closing registration screen
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
		signUpButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				registerUser();
			}

			

		});
	}
	private void registerUser() {
		String username = usernameInput.getText().toString();
		String email = emailInput.getText().toString();
		String password = passwordInput.getText().toString();
		String reenteredPassword = reenterPasswordInput.getText().toString();
		
		boolean correctPassword = password.equals(reenteredPassword);
		boolean correctEmail = !email.isEmpty();
		
		if(correctEmail && correctPassword) {
			String serverUrl = getString(R.string.serverUrl) + "/CreateUser";
			
			NameValuePair[] params = new NameValuePair[3];
			params[0] = new BasicNameValuePair("username", username);
			params[1] = new BasicNameValuePair("password", password);
			params[2] = new BasicNameValuePair("email", email);
			
			AsyncPost post = new AsyncPost(serverUrl, new OnRegisterResponse());
			post.execute(params);
		} else {
			String alertMsg = "Email can not be empty. Passwords does not match.";
			if(correctEmail) {
				alertMsg = "Passwords does not match.";
			}
			if(correctEmail) {
				alertMsg = "Email can not be empty.";
			}
			showDialog(alertMsg, "Invalid input");
			
		}
	}
	
	private void showDialog(String alertMsg, String title) {
		new AlertDialog.Builder(this).setTitle(title)
		.setMessage(alertMsg).setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}
		
	private class OnRegisterResponse implements AsyncPostCallback {

		@Override
		public void onPostComplete(PostResult postResult) {
			HttpResponse response = postResult.response;
			JSONObject json = Utils.httpResponseToJSON(response);
			try {
				if(!json.getBoolean("success")) {
					showDialog("We have a problem on the server.", "Sorry!");
				} else if(!json.getBoolean("registration_succeeded")) {
					showDialog("The username is already in use. Please choose another one.", "Password in use");
				}
				else {
					finish();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}