package com.mlab.trainings.loginregapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity
{

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private EditText emailEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		SharedPreferences prefs = getSharedPreferences("auth", Context.MODE_PRIVATE);
		String login = prefs.getString("login", null);
		String pass = prefs.getString("password", null);
		if(login != null && pass != null)
		{
			//TODO check
			Intent intent = new Intent(this, MainActivity.class);

			startActivity(intent);
			finish();
			return;
		}
		
		setContentView(R.layout.activity_login);
		
		emailEditText = (EditText) findViewById(R.id.edit_login);
		passwordEditText = (EditText) findViewById(R.id.edit_password);
	}

	public void clickRegister(View v)
	{
		startActivityForResult(new Intent(this, RegisterActivity.class), RequestCodes.REG_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
			case RequestCodes.REG_REQUEST_CODE:
				firstName = data.getExtras().getString("firstName");
				lastName = data.getExtras().getString("lastName");
				email = data.getExtras().getString("email");
				password = data.getExtras().getString("password");

				emailEditText.setText(email);
				passwordEditText.setText(password);

				break;

			default:
				break;
		}
	}

	public void clickLogin(View v)
	{
		Bundle extras = new Bundle();
		String login = emailEditText.getText().toString();
		String pass = passwordEditText.getText().toString();
		
		SharedPreferences prefs = getSharedPreferences("auth", Context.MODE_PRIVATE);
		prefs.edit().putString("login", login).putString("password", pass).commit();
		
		extras.putString("firstName", firstName);
		extras.putString("lastName", lastName);
		extras.putString("email", login);
		extras.putString("password", pass);

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtras(extras);

		startActivity(intent);
		finish();
	}

	public void clickForgot(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
		startActivity(intent);
	}
}
