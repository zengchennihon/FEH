package org.feh.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;

@Order(10)
public class BeanFactoryUtils implements ApplicationContextAware {
	
	private static ApplicationContext _applicationContext;
	
	private Logger logger = Logger.getLogger(BeanFactoryUtils.class);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		_applicationContext = applicationContext;
		logger.info("********初始化ApplicationContext【" + _applicationContext + "】*******");
	}
	
	public static ApplicationContext getApplicationContext() {
		return _applicationContext;
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
