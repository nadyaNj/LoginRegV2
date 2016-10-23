package com.mlab.trainings.loginregapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	public void clickRegistration(View v)
	{
		String firstName = ((EditText) findViewById(R.id.edit_firstName)).getText().toString();
		String lastName = ((EditText) findViewById(R.id.edit_lastName)).getText().toString();
		String email = ((EditText) findViewById(R.id.edit_email)).getText().toString();
		String password = ((EditText) findViewById(R.id.edit_password_reg)).getText().toString();

		Bundle extras = new Bundle();
		extras.putString("firstName", firstName);
		extras.putString("lastName", lastName);
		extras.putString("email", email);
		extras.putString("password", password);

		Intent intent = new Intent(this, LoginActivity.class);
		intent.putExtras(extras);

		setResult(RESULT_OK, intent);
		finish();
	}

}
