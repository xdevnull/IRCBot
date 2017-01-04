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
 * IRCBot Permissions
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class Permission implements Serializable {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5945891691009399467L;
	
	/**
	 * IRCBot Permissions
	 * Boolean value to determine whether is nicknames cannot be removed
	 */
	private final TreeMap<String, Boolean> permissions = new TreeMap<String, Boolean>(String.CASE_INSENSITIVE_ORDER);
		
	
	/**
	 * Add Nickname to permissions
	 * @param nickname
	 * @param locked
	 */
	public void add(String nickname, boolean locked) {
		if(!permissions.containsKey(nickname))
			permissions.put(nickname, Boolean.valueOf(locked));
	}
	
	/**
	 * Determine whether nickname is permitted
	 * @param nickname
	 * @return
	 */
	public boolean has(String nickname) {
		return permissions.containsKey(nickname);
	}
	
	/**
	 * Get locked value for a nickname
	 * @param nickname
	 * @return
	 */
	public boolean get(String nickname) {
		return permissions.get(nickname);
	}
	
	/**
	 * Remove permission for nicknames
	 * @param nickname
	 */
	public void remove(String nickname) {
		if(has(nickname) && get(nickname) != true)
			permissions.remove(nickname);
	}
	
	/**
	 * Get permitted nicknames count
	 * @return
	 */
	public int count() {
		return permissions.size();
	}
	
	/**
	 * Get permitted nicknames
	 * @return
	 */
	public String[] nicknames() {
		return permissions.keySet().toArray(new String[permissions.size()]);
	}
	
	/**
	 * Merge external permissions with current permissions
	 * @param otherPermissions
	 */
	public void merge(TreeMap<String, Boolean> otherPermissions) {
		for(Map.Entry<String, Boolean> entry : otherPermissions.entrySet())
			add(entry.getKey(), entry.getValue());
	}
	
	/**
	 * Load permissions from JSON File
	 * @param filePath
	 */
	public void load(String filePath) {
		load(new File(filePath));
	}
	
	/**
	 * Load permissions from JSON File
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
			Type type = new TypeToken<TreeMap<String, Boolean>>(){}.getType();
			merge((TreeMap<String, Boolean>) gson.fromJson(jsonReader, type));
			jsonReader.close();
		}
		catch(IOException e) {
			System.out.println("Unable to load permissions from JSON File!");
		}
		
	}
}
