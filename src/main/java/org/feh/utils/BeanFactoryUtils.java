package org.feh.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanFactoryUtils {
	
	@SuppressWarnings({ "unchecked", "resource", "hiding" })
	public static <T> T getBean(Class<?> clazz) {
		ApplicationContext context = new FileSystemXmlApplicationContext(BeanFactoryUtils.class.getResource("/spring/spring-mvc.xml").toString());
        return (T) context.getBean(clazz);
	}
	
	@SuppressWarnings({ "unchecked", "resource", "hiding" })
	public static <T> T getBean(String name) {
		ApplicationContext context = new FileSystemXmlApplicationContext(BeanFactoryUtils.class.getResource("/spring/spring-mvc.xml").toString());
        return (T) context.getBean(name);
	}
	
}
