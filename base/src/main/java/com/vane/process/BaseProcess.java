package com.vane.process;

import java.util.List;
import java.util.Map;

import com.vane.interceptor.Interceptor;

public abstract class BaseProcess {
	public List<Interceptor> interceptorList;
	
	public String serviceIdName;
	
	private Map<String,Object> dataMap;
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public List<Interceptor> getInterceptorList() {
		return interceptorList;
	}

	public void setInterceptorList(List<Interceptor> interceptorList) {
		this.interceptorList = interceptorList;
	}

	public String getServiceIdName() {
		return serviceIdName;
	}

	public void setServiceIdName(String serviceIdName) {
		this.serviceIdName = serviceIdName;
	}
	
	public abstract void init();
	
	public abstract void destory();

	public void execute(Object object) {
		
	}
}
