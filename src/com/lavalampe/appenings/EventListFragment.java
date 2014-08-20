package com.lavalampe.appenings;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventListFragment extends ListFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_category_list, container,false);
		
		String[] menuItems = getResources().getStringArray(R.array.menu_items);
        ListView cardList = (ListView) v.findViewById(R.id.fragment_category_list);
		
		cardList.setAdapter(new ArrayAdapter<String>(v.getContext(),
                R.layout.category_list_item, menuItems));
		return v;
	}
	
	
}
