package com.xdevnull.bot.brain;

import java.io.File;
import java.io.Serializable;

/**
 * Brain
 * @author xdevnull
 * @date Dec 30, 2016
 */
public interface Brain {
	
	/**
	 * Store item in brain
	 * getClass().getName() used to as unique value
	 * @param Class
	 */
	public void store(Serializable Class);
	
	/**
	 * Store array of items in brain
	 * @param Classes
	 */
	public void store(Serializable[] Classes);
	
	/**
	 * Remove item from brain
	 * @param className
	 */
	public void remove(String className);
	
	/**
	 * Remove item from brain
	 * @param Class
	 */
	public void remove(Serializable Class);
	
	/**
	 * Remove items
	 * @param Classes
	 */
	public void remove(Serializable[] Classes);
	
	/**
	 * Clear Brain
	 */
	public void clear();
	
	/**
	 * Determine whether item is in brain
	 * @param Class
	 * @return
	 */
	public boolean has(String Class);
	
	/**
	 * Get item from brain
	 * @param Class
	 * @return
	 */
	public Object get(String Class);
	
	/**
	 * Load brain
	 */
	public void load();
	
	/**
	 * Load Brain From External Brain File
	 * @param brainFile
	 */
	public void load(File brainFile);
	
	/**
	 * Commit changes
	 */
	public void commit();
	
	/**
	 * Get all registered items
	 */
	public String[] registered();
}
