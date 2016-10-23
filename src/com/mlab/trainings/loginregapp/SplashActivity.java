package com.mlab.trainings.loginregapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread waitingThread = new Thread(new Runnable()
		{
			Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

			@Override
			public void run()
			{
				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				startActivity(intent);
				finish();
			}
		});

		waitingThread.start();

	}
}
