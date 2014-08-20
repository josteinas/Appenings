package com.lavalampe.appenings.networking;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;

public class PostResult {
	public final HttpResponse response;
	public final List<Cookie> cookies;
	public PostResult(HttpResponse response, List<Cookie> cookies) {
		super();
		this.response = response;
		this.cookies = cookies;
	}
}
