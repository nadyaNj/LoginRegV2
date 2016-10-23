package com.mlab.trainings.loginregapp;

import android.app.Application;

public class LoginRegApp extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		Toaster.init(this);
	}
}
