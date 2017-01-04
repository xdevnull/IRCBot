package com.xdevnull.bot.support;

import java.io.File;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.xdevnull.bot.brain.Brain;
import com.xdevnull.bot.log.Logger;
import com.xdevnull.bot.net.EventDispatcher;
import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.script.Permission;
import com.xdevnull.bot.script.Trigger;
import com.xdevnull.bot.script.YoutubeSearch;
/**
 * ConfigurationReader
 * @author xdevnull
 * @date Jan 1, 2017
 */
public class XMLConfigurationReader {
	
	/**
	 * IRC Server
	 */
	private String server;
	
	/**
	 * IRC port
	 */
	private int port;
	
	/**
	 * IRC SSL Connection
	 */
	private boolean secureConnection;
	
	/**
	 * IRCBot nickname
	 */
	private String nickname;
	
	/**
	 * IRCBot alternative
	 */
	private String alternative;
	
	/**
	 * IRCBot username
	 */
	private String username;
	
	/**
	 * IRCBot password for username
	 */
	private String password;
	
	/**
	 * IRCBot realname
	 */
	private String realName;
	
	/**
	 * IRCBot channels
	 */
	private String[] channels;
	
	/**
	 * IRCBot Brain
	 */
	private Brain brain;
	
	/**
	 * IRCBot logger
	 */
	private Logger logger;
	
	/**
	 * IRCBot Event Dispatcher
	 */
	private EventDispatcher dispatcher;
	
	/**
	 * YouTube Search Script
	 */
	private YoutubeSearch youtubeSearch;
	
	/**
	 * IRCBot trigger script
	 */
	private Trigger trigger;
	
	/**
	 * IRCBot permission script
	 */
	private Permission permission;
	
	
	/**
	 * ConfigurationReader
	 * @param configurations
	 * @param configFilePath
	 */
	public XMLConfigurationReader(Configurations configurations, File configFile) {
		if(!configFile.exists() || configFile.isDirectory()) {
			System.out.println("Config file wasn't found!");
			System.exit(100);
		}
		try {
			this.build(configurations.xml(configFile));
		} catch (ConfigurationException e) {
			System.out.println("ConfigurationException");
		}
	}
	
	/**
	 * Scan XML
	 * @param configs
	 */
	/**
	 * Scan XML
	 * @param configs
	 */
	private final void build(XMLConfiguration config) {
			this.server = config.getString("server", null);
			this.port = config.getInt("port", 6667);
			this.secureConnection = config.getBoolean("port(0)[@ssl]", false);
			this.nickname = config.getString("nickname", null);
			this.alternative = config.getString("nickname(0)[@alternative]", null);
			this.username = config.getString("nickname", null);
			this.password = config.getString("password", null);
			this.realName = config.getString("real-name", null);
			this.channels = config.getString("channels", "").trim().split(",");
			this.brain = (Brain) Factory.createRaw(config.getString("brain", null), config.getString("brain(0)[@brain-file]", null));
			this.logger = (Logger) Factory.createRaw(config.getString("logger", null));
			this.dispatcher = (EventDispatcher) Factory.createRaw(config.getString("event-dispatcher(0))[@class]", null));
			if(this.dispatcher != null)
				for(String listener : config.getStringArray("event-dispatcher.listener"))
					this.dispatcher.addListener((EventListener) Factory.createRaw(listener));
			if(config.getBoolean("youtube-search(0)[@enabled]", null))
				this.youtubeSearch = (YoutubeSearch) Factory.createRaw(config.getString("youtube-search", null), config.getString("youtube-search(0)[@API_KEY]", null));
			
			if(config.getBoolean("trigger(0)[@enabled]", null)) {
				if(brain != null && brain.has(Trigger.class.getName()))
					this.trigger = (Trigger) brain.get(Trigger.class.getName());
				else
					this.trigger = (Trigger) Factory.createRaw(config.getString("trigger"));
				if(this.trigger != null) {
					if(config.getString("trigger(0)[@trigger-json-file]", null) != null)
						this.trigger.load(config.getString("trigger(0)[@trigger-json-file]", null));	
					if(config.getBoolean("trigger(0)[@storeable]", null))
						if(this.brain != null)
							this.brain.store(this.trigger);
				}
			}
			
			if(config.getBoolean("permission(0)[@enabled]")) {
				if(brain != null && brain.has(Permission.class.getName()))
					this.permission = (Permission) brain.get(Permission.class.getName());
				else
					this.permission = (Permission) Factory.createRaw(config.getString("permission"));
				if(this.permission != null) {
					if(config.getString("permission(0)[@permission-json-file]") != null)
						this.permission.load(config.getString("permission(0)[@permission-json-file]"));
					if(config.getBoolean("permission(0)[@storeable]"))
						if(this.brain != null)
							this.brain.store(this.permission);
				}
			}
	}
	
	/**
	 * Get IRC Server
	 * @return
	 */
	public String getServer() {
		return server;
	}
	
	/**
	 * Get IRC Server port
	 * @return
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Determine whether connection is ssl
	 * @return
	 */
	public boolean isSecureConnection() {
		return secureConnection;
	}
	
	/**
	 * Get IRCBot nickname
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Get IRCBot alternative nickname
	 * @return
	 */
	public String getAlternative() {
		return alternative;
	}
	
	/**
	 * Get IRCBot username
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get IRCBot password of username
	 * @return
	 */
	public String getPassword() {
		return password;
	}	
	
	/**
	 * Get IRCBot Real name
	 * @return
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * Get IRCBot channels
	 * @return
	 */
	public String[] getChannels() {
		return channels;
	}
	
	/**
	 * Get IRCBot Brain
	 * @return
	 */
	public Brain getBrain() {
		return brain;
	}
	
	/**
	 * Get IRCBot Logger
	 * @return
	 */
	public Logger getLogger() {
		return logger;
	}
	
	/**
	 * Get IRCBot EventDispatcher
	 * @return
	 */
	public EventDispatcher getDispatcher() {
		return dispatcher;
	}
	
	/**
	 * Get IRCBot Youtube Search Script
	 * @return
	 */
	public YoutubeSearch getYoutubeSearch() {
		return youtubeSearch;
	}
	
	/**
	 * Get IRCBot triggers script
	 * @return
	 */
	public Trigger getTrigger() {
		return trigger;
	}
	
	/**
	 * Get IRCBot permission script
	 * @return
	 */
	public Permission getPermission() {
		return permission;
	}
	
}
