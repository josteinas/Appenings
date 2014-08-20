package com.lavalampe.appenings;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends Activity{
	private static final String TAG = MainActivity.class.getSimpleName();
	String[] menuItems;
	DrawerLayout drawerLayout;
	ListView drawerList;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		
		setContentView(R.layout.activity_main);

        menuItems = getResources().getStringArray(R.array.menu_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.listViewMenu);
        
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);
        
        if(fragment == null) {
        	fragment = new EventListFragment();
        	fm.beginTransaction().add(R.id.content_frame, fragment).commit();
        }

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_layout, menuItems));
        // Set the list's click listener
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position,
	              long id) {
	        Log.d(TAG, "Clicked: " + view + " id: " + id + " pos: " + position);

	    }


	}
}
