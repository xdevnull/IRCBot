package com.xdevnull.bot.support;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;


/**
 * Factory
 * @author xdevnull
 * @date Jan 1, 2017
 */
public class Factory {
	
	/**
	 * Create XMLConfigurationReader
	 * @param xmlPath
	 * @return
	 */
	public static XMLConfigurationReader createXMLConfigReader(File xmlPath) {
		return new XMLConfigurationReader(createConfigurations(), xmlPath);
	}
	
	/**
	 * Create Apache Configurations
	 * @return
	 */
	public static Configurations createConfigurations() {
		return new Configurations();
	}
	
	/**
	 * Create Xpath Expression Engine
	 * @return
	 */
	public static XPathExpressionEngine createXpathEngine() {
		return new XPathExpressionEngine();
	}
	/**
	 * Create Object by passing classpath and list of parameters
	 * @param classpath
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object createRaw(String classpath, Object... parameters) {
		if(classpath == null)
			return null;
		ArrayList<Class> classes = new ArrayList<Class>();
		for(Object parameter : parameters)
			classes.add(parameter.getClass());
		
		try {
			return Class.forName(classpath).getDeclaredConstructor(classes.toArray(new Class[classes.size()])).newInstance(parameters);
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: " + classpath);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: " + classpath);
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException: " + classpath);
		} catch (InvocationTargetException e) {
			System.out.println("InvocationTargetException: " + classpath);
		} catch (NoSuchMethodException e) {
			System.out.println("NoSuchMethodException: " + classpath);
		} catch (SecurityException e) {
			System.out.println("SecurityException: " + classpath);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + classpath);
		}		
		return null;
	}
}
