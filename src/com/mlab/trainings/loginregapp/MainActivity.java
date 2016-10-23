package com.mlab.trainings.loginregapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private MyListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		// String firstName = getIntent().getExtras().getString("firstName");
		// String lastName = getIntent().getExtras().getString("lastName");
		// String email = getIntent().getExtras().getString("email");
		// String password = getIntent().getExtras().getString("password");
		//

		// ((TextView) findViewById(R.id.txtLastName)).setText(lastName);
		// ((TextView) findViewById(R.id.txtEmail)).setText(email);
		// ((TextView) findViewById(R.id.txtPassword)).setText(password);

		TextView draggable = (TextView) findViewById(R.id.txtFirstName);
		draggable.setText("drag me");
		draggable.setOnDragListener(new OnDragListener()
		{
			@Override
			public boolean onDrag(View v, DragEvent event)
			{

				switch (event.getAction())
				{
				case DragEvent.ACTION_DRAG_STARTED:
					Toaster.show("drag start", Toast.LENGTH_SHORT);
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					Toaster.show("drag end", Toast.LENGTH_SHORT);
					break;
				default:
					break;
				}
				return true;
			}
		});

		draggable.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View view, MotionEvent motionEvent)
			{
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
				{
					ClipData data = ClipData.newPlainText("", "");
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
					view.startDrag(data, shadowBuilder, view, 0);
					view.setVisibility(View.GONE);
					return true;
				} else
				{
					return false;
				}
			}
		});

		ListView myList = (ListView) findViewById(R.id.list);
		adapter = new MyListAdapter(this);
		myList.setAdapter(adapter);

		List<MyListModel> models = new ArrayList<MyListModel>();
		for (int i = 0; i < 50; i++)
		{
			models.add(new MyListModel("top text " + i, "bottom text " + i));
		}

		adapter.addItems(models);

		myList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				MyListModel model = adapter.getItem(position);
				Toaster.show(model.getText1() + ", " + model.getText2(), Toast.LENGTH_SHORT);
			}
		});
	}

	public void clickShowProgress(View v)
	{
		// final ProgressDialog dialog = ProgressDialog.show(this, "title",
		// "our progress");
		// new Thread(new Runnable()
		// {
		// @Override
		// public void run()
		// {
		// try
		// {
		// for (int i = 0; i < 20; i++)
		// {
		// final int index = i;
		// Thread.sleep((int) (Math.random() * 1000));
		// runOnUiThread(new Runnable()
		// {
		// public void run()
		// {
		// dialog.setMessage("message " + index);
		// }
		// });
		//
		// }
		// dialog.dismiss();
		// } catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }
		// }
		// }).start();

		// final AlertDialog dialog;
		// AlertDialog.Builder builder = new Builder(this);
		//
		// builder.setTitle("Our builder");
		// builder.setMessage("our message");
		// builder.setIcon(R.drawable.the_final_cut);
		//
		// builder.setPositiveButton("Yes", new OnClickListener()
		// {
		// @Override
		// public void onClick(DialogInterface dialog, int which)
		// {
		// dialog.dismiss();
		// Toast.makeText(MainActivity.this, "yes clicked",
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		//
		// builder.setNegativeButton("No", new OnClickListener()
		// {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which)
		// {
		// Toast.makeText(MainActivity.this, "no clicked",
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		//
		// dialog = builder.show();

		DatePickerDialog dialog = new DatePickerDialog(this, new OnDateSetListener()
		{

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
			{
				Calendar cal = Calendar.getInstance();
				cal.set(year, monthOfYear, dayOfMonth);
				SimpleDateFormat format = new SimpleDateFormat("yyyy, MMM, $### 'address' dd HH:mm");
				Toast.makeText(MainActivity.this, format.format(cal.getTime()), Toast.LENGTH_SHORT).show();
			}
		}, 1998, 10, 5);
		dialog.show();

	}
}
