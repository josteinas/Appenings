package com.lavalampe.appenings;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lavalampe.appenings.networking.AsyncPost;
import com.lavalampe.appenings.networking.AsyncPostCallback;
import com.lavalampe.appenings.networking.PostResult;

public class EventListFragment extends ListFragment{
	
	ArrayList<Category> followedCategories;
	String username;
	CategoryListAdapter catListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SharedPreferences pref = this.getActivity().getSharedPreferences(LoginActivity.sessionPref,Context.MODE_PRIVATE);
		username = pref.getString("username", "no username stored");
		followedCategories = new ArrayList<Category>();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View v = inflater.inflate(R.layout.fragment_category_list, container,false);
		
        ListView cardList = (ListView) v.findViewById(R.id.fragment_category_list);
        
        catListAdapter = new CategoryListAdapter(v.getContext(),
                R.layout.category_list_item, followedCategories);
		
		cardList.setAdapter(catListAdapter);
        getFollowedCategories(username);
		return v;
	}
	
	private void getFollowedCategories(String username) {
		String serverUrl = getString(R.string.serverUrl) + "/GetFollowedCategories";
		
		NameValuePair[] params = new NameValuePair[1];
		params[0] = new BasicNameValuePair("username", username);
		
		AsyncPost post = new AsyncPost(serverUrl, new GetCategoriesResponse());
		post.execute(params);
	}
	private class GetCategoriesResponse implements AsyncPostCallback {

		@Override
		public void onPostComplete(PostResult postResult) {
			HttpResponse response = postResult.response;
			JSONObject json = Utils.httpResponseToJSON(response);
			try {
				JSONArray categories = json.getJSONArray("result");
				for (int i = 0; i < categories.length(); i++) {
					JSONObject c = categories.getJSONObject(i);
					JSONObject creator = c.getJSONObject("creator");
					followedCategories.add(new Category(c.getString("name"), (float)c.getDouble("latitude"), (float)c.getDouble("longitude"), new User(creator.getString("username")), c.getBoolean("private")));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			catListAdapter.notifyDataSetChanged();
			
		}
		
	}
	
}
