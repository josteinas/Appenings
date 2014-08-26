package com.lavalampe.appenings.networking;


public interface AsyncPostCallback {
	public void onPostComplete(PostResult postResult);
	public void onError();
}
