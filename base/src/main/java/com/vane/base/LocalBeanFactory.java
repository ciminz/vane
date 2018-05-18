package com.vane.base;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LocalBeanFactory implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = arg0;
	}
	
	public static Object get(Class beanType) {
		return applicationContext.getBean(beanType);
	}
	
	public static Object get(String beanNme) {
		return applicationContext.getBean(beanNme);
	}

}
