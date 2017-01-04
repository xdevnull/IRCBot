package com.xdevnull.bot.script;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * IRCBot Triggers
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class Trigger implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3292293250058985573L;
	
	/**
	 * IRCBot Triggers
	 */
	private final TreeMap<String, String> triggers = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	
	/**
	 * Add new trigger
	 * @param trigger
	 * @param value
	 */
	public void add(String trigger, String value) {
		if(!triggers.containsKey(trigger))
			triggers.put(trigger, value);
	}
	
	/**
	 * Remove trigger
	 * @param trigger
	 */
	public void remove(String trigger) {
		triggers.remove(trigger);
	}
	
	/**
	 * Get trigger
	 * @param trigger
	 * @return
	 */
	public String get(String trigger) {
		return triggers.get(trigger);
	}
	
	/**
	 * Determine whether trigger exists
	 * @param trigger
	 * @return
	 */
	public boolean has(String trigger) {
		return triggers.containsKey(trigger);
	}
	
	/**
	 * Get count of registered triggers
	 * @return
	 */
	public int count() {
		return triggers.size();
	}
	
	/**
	 * Get all registered triggers
	 * @return
	 */
	public String[] triggers() {
		return triggers.keySet().toArray(new String[triggers.size()]);
	}
	
	/**
	 * Merge other triggers with current registered triggers
	 * @param otherTriggers
	 */
	public void merge(TreeMap<String, String> otherTriggers) {
		for(Map.Entry<String, String> entry : otherTriggers.entrySet())
			add(entry.getKey(), entry.getValue());
	}
	
	/**
	 * Load triggers from JSON File
	 * @param filePath
	 */
	public void load(String filePath) {
		load(new File(filePath));
	}
	
	/**
	 * Load triggers from JSON File
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	public void load(File file) {
		//If file doesn't exists
		if(!file.exists() || file.length() == 0)
			return;
		
		try {
			JsonReader jsonReader = new JsonReader(new FileReader(file));
			Gson gson = new Gson();
			Type type = new TypeToken<TreeMap<String, String>>(){}.getType();
			merge((TreeMap<String, String>) gson.fromJson(jsonReader, type));
			jsonReader.close();
		}
		catch(IOException e) {
			System.out.println("Unable to load triggers from JSON File!");
		}
	}

}
