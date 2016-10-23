package com.mlab.trainings.loginregapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter
{
	private List<MyListModel> items;
	private LayoutInflater inflater;

	public MyListAdapter(Context context)
	{
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		items = new ArrayList<MyListModel>();
	}
	
	public void addItems(List<MyListModel> items)
	{
		this.items.addAll(items);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount()
	{
		return items.size();
	}

	@Override
	public MyListModel getItem(int position)
	{
		return items.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.item_list, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.text1 = (TextView) convertView.findViewById(R.id.list_item_text1);
			holder.text2 = (TextView) convertView.findViewById(R.id.list_item_text2);
			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		
		MyListModel model = items.get(position);
		holder.text1.setText(model.getText1());
		holder.text2.setText(model.getText2());
		
		return convertView;
	}
	
	private final static class ViewHolder
	{
		TextView text1;
		TextView text2;
	}

}
