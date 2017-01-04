package com.xdevnull.bot.brain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;

/**
 * FileBrain
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class FileBrain implements Brain {

	/**
	 * Brain
	 */
	private final TreeMap<String, Object> brain = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
	
	/**
	 * File Brain File
	 */
	private final File brainFile;
	
	/**
	 * FileBrain
	 * @param brainFilePath
	 */
	public FileBrain(String brainFilePath) {
		this(new File(brainFilePath));
	}
	
	/**
	 * FileBrain
	 * @param brainFile
	 */
	public FileBrain(File brainFile) {
		this.brainFile = brainFile;
		if(brainFile.exists() && brainFile.length() > 0)
			this.load();
		else {
			try {
				brainFile.createNewFile();
			}
			catch(IOException e) {
				System.out.println("Unable to create brain file");
			}
		}
	}
	
	/**
	 * Store item in brain
	 */
	public void store(Serializable Class) {
		if(!brain.containsKey(Class.getClass().getName()))
			brain.put(Class.getClass().getName(), Class);
	}
	
	/**
	 * Store array of items in brain
	 */
	public void store(Serializable[] Classes) {
		for(Serializable Class : Classes)
			store(Class);
	}
	
	/**
	 * Remove Item from brain
	 */
	public void remove(String className) {
		brain.remove(className);
	}
	
	/**
	 * Remove Item from brain
	 */
	public void remove(Serializable Class) {
		remove(Class.getClass().getName());
	}
	
	/**
	 * Remove array of classes
	 * @param Classes
	 */
	public void remove(Serializable[] Classes) {
		for(Serializable Class : Classes)
			remove(Class.getClass().getName());
	}
	
	/**
	 * Determine whether class exists in brain
	 */
	public boolean has(String className) {
		return brain.containsKey(className);
	}
	
	/**
	 * Determine whether class exists in brain
	 * @param Class
	 * @return
	 */
	public boolean has(Serializable Class) {
		return has(Class.getClass().getName());
	}
	
	/**
	 * Get item from brain
	 */
	public Object get(String className) {
		return brain.get(className);
	}
	
	/**
	 * Get item from brain
	 * @param Class
	 * @return
	 */
	public Object get(Serializable Class) {
		return get(Class.getClass().getName());
	}
	
	/**
	 * Clear Brain
	 */
	public void clear() {
		brain.clear();
	}
	
	/**
	 * Load data from FileBrain
	 */
	public void load() {
		load(brainFile);
	}
	
	/**
	 * Load data from external brain file
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void load(File brainFile) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(brainFile));
			Object brainData = null;
			if((brainData = in.readObject()) != null) {
				brain.putAll((TreeMap<String, Object>) brainData);
			}
		}
		catch(Exception e) {
			System.out.println("Unable to load brain!");
		}
	}
	
	/**
	 * Commit changes
	 */
	public synchronized void commit() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(brainFile));			
			out.writeObject(brain);
			out.flush();
			out.close();
		}
		catch(IOException e) {
			System.out.println("Unable to commit changes!");
		}
	}
	
	public String[] registered() {
		return brain.keySet().toArray(new String[brain.size()]);
	}

	
}
