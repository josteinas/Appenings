package com.lavalampe.appenings;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        
        List<Category> categories = new ArrayList<Category>();
        for (int i = 0; i < menuItems.length; i++) {
			categories.add(new Category(menuItems[i], 85, 85, new User("jostein", "blabla!", "hnnng", "@"), false));
		}
		
		cardList.setAdapter(new CategoryListAdapter(v.getContext(),
                R.layout.category_list_item, categories));
		return v;
	}
	
	
}
