package org.feh.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanFactoryUtils implements ApplicationContextAware {
	
	private static ApplicationContext _applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		_applicationContext = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		return (T) _applicationContext.getBean(clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) _applicationContext.getBean(name);
	}

}
