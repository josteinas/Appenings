package com.lavalampe.appenings;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CategoryListAdapter extends ArrayAdapter<Category> {

	public CategoryListAdapter(Context context, int resource) {
		super(context, resource);
	}

	public CategoryListAdapter(Context context, int resource,
			List<Category> items) {
		super(context, resource, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.category_list_item, parent,false);
			
			holder = new ViewHolder();
			holder.header = (TextView) convertView.findViewById(R.id.category_item_header);
			holder.map = (TextView) convertView.findViewById(R.id.category_item_maptemp);
			holder.follow = (Button) convertView.findViewById(R.id.btnFollow);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.header.setText(getItem(position).getName());
		holder.map.setText("This will be a map");
		holder.follow.setText("Following");
		holder.follow.setSelected(true);
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView header;
		TextView map;
		Button follow;
	}

}
