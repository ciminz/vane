package com.vane.interceptor;

public interface Interceptor {
	public void onRequest(Object object);
	
	public void onResponse(Object object);
}
