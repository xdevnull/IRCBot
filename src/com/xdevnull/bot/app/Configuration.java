package com.xdevnull.bot.app;

import java.io.File;
import com.xdevnull.bot.brain.Brain;
import com.xdevnull.bot.log.Logger;
import com.xdevnull.bot.net.EventDispatcher;
import com.xdevnull.bot.script.Permission;
import com.xdevnull.bot.script.Trigger;
import com.xdevnull.bot.script.YoutubeSearch;
import com.xdevnull.bot.support.XMLConfigurationReader;

/**
 * Configuration
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class Configuration {
	
	/**
	 * IRCBot daemon
	 */
	private final File daemon;
	
	/**
	 * IRCBot XML Configuration
	 */
	private final File config;
	
	/**
	 * IRCBot Brain
	 */
	private final Brain brain;
	
	/**
	 * IRCBot Logger
	 */
	private final Logger logger;
	
	/**
	 * IRCBot Event Dispatcher
	 */
	private final EventDispatcher dispatcher;
	
	/**
	 * IRCBot YoutubeSearch Script
	 */
	private final YoutubeSearch youtubeSearch;
	
	/**
	 * IRCBot Triggers Script
	 */
	private final Trigger trigger;
	
	/**
	 * IRCBot permissions Script
	 */
	private final Permission permission;
	
	/**
	 * IRC Server
	 */
	private String server;
	
	/**
	 * IRC Server Port
	 */
	private final int port;
	
	/**
	 * Determine whether a SSL Connection used
	 */
	private final boolean secureConnection;
	
	/**
	 * IRCBot nickname
	 */
	private String nickname;
	
	/**
	 * IRCBot alternative nickname
	 */
	private final String altNickname;
	
	/**
	 * IRCBot username
	 */
	private final String username;
	
	/**
	 * IRCBot password
	 */
	private final String password;
	
	/**
	 * IRCBot realname
	 */
	private final String realName;
	
	/**
	 * IRCBot channels
	 */
	private final String[] channels;
	
	/**
	 * Configuration
	 * @param builder
	 */
	public Configuration(Builder builder) {
		this.daemon = builder.daemon;
		this.config = builder.config;
		this.brain = builder.brain;
		this.logger = builder.logger;
		this.dispatcher = builder.dispatcher;
		this.youtubeSearch = builder.youtubeSearch;
		this.trigger = builder.trigger;
		this.permission = builder.permission;
		this.server = builder.server;
		this.nickname = builder.nickname;
		this.port = builder.port;
		this.altNickname = builder.altNickname;
		this.username = builder.username;
		this.password = builder.password;
		this.realName = builder.realName;
		this.channels = builder.channels;
		this.secureConnection = builder.secureConnection;
		
		if(this.server == null || this.nickname == null || this.server.equals("") || this.nickname.equals("")) {
			System.out.println("Server and Nickname are required!");
			System.out.println("Server: " + this.server);
			System.out.println("Nickname: " + this.nickname);
			System.exit(200);
		}
	}
	
	/**
	 * Set server (One server may be linked with multiple server) 
	 * @param server
	 */
	public void setServer(String server) {
		this.server = server;
	}
	
	/**
	 * Set nickname (In case nickname was changed)
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Get IRCBot Daemon File
	 * @return
	 */
	public File getDaemon() {
		return this.daemon;
	}
	
	/**
	 * Get IRCBot XML Config File
	 * @return
	 */
	public File getConfig() {
		return this.config;
	}
	
	/**
	 * Get IRCBot Brain
	 * @return
	 */
	public Brain getBrain() {
		return this.brain;
	}
	
	/**
	 * Get IRCBot Logger
	 * @return
	 */
	public Logger getLogger() {
		return this.logger;
	}
	
	/**
	 * Get IRCBot EventDispatcher
	 * @return
	 */
	public EventDispatcher getEventDispatcher() {
		return this.dispatcher;
	}
	
	/**
	 * Get youtube search
	 * @return
	 */
	public YoutubeSearch getYoutubeSearch() {
		return this.youtubeSearch;
	}
	
	/**
	 * Get trigger script
	 * @return
	 */
	public Trigger getTrigger() {
		return this.trigger;
	}
	
	/**
	 * Get permission script
	 * @return
	 */
	public Permission getPermission() {
		return this.permission;
	}

	
	/**
	 * Get server
	 * @return
	 */
	public String getServer() {
		return this.server;
	}
	
	/**
	 * Get server port
	 * @return
	 */
	public int getPort() {
		return this.port;
	}
	
	/**
	 * Determine whether connection is ssl
	 * @return
	 */
	public boolean isSecureConnection() {
		return this.secureConnection;
	}
	
	/**
	 * Get IRCBot nickname
	 * @return
	 */
	public String getNickname() {
		return this.nickname;
	}
	
	/**
	 * Get IRCBot alternative nickname
	 * @return
	 */
	public String getAltNickname() {
		return this.altNickname;
	}
	
	/**
	 * Get IRCBot nickserv username
	 * @return
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Get IRCBot nickserv password
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Get IRCBot Real name
	 * @return
	 */
	public String getRealname() {
		return this.realName;
	}
	
	/**
	 * Get IRCBot auto join channels
	 * @return
	 */
	public String[] getChannels() {
		return this.channels;
	}
	
	
	
	/**
	 * Configuration Builder
	 * @author xdevnull
	 * @date Dec 30, 2016
	 */
	public static class Builder {
		
		/**
		 * IRCBot daemon
		 */
		private File daemon;
		
		/**
		 * IRCBot XML Configuration
		 */
		private File config;
		
		/**
		 * IRCBot Brain
		 */
		private Brain brain;
		
		/**
		 * IRCBot Logger
		 */
		private Logger logger;
		
		/**
		 * IRCBot Event Dispatcher
		 */
		private EventDispatcher dispatcher;
		
		/**
		 * IRCBot YoutubeSearch Script
		 */
		private YoutubeSearch youtubeSearch;
		
		/**
		 * IRCBot Triggers Script
		 */
		private Trigger trigger;
		
		/**
		 * IRCBot permissions Script
		 */
		private Permission permission;
		
		/**
		 * IRC Server
		 */
		private final String server;
		
		/**
		 * IRC Server Port
		 */
		private int port = 6667;
		
		/**
		 * Determine whether a SSL Connection used
		 */
		private boolean secureConnection = false;
		
		/**
		 * IRCBot nickname
		 */
		private final String nickname;
		
		/**
		 * IRCBot alternative nickname
		 */
		private String altNickname;
		
		/**
		 * IRCBot username
		 */
		private String username;
		
		/**
		 * IRCBot password
		 */
		private String password;
		
		/**
		 * IRCBot realname
		 */
		private String realName = "IRCBot v1 xdevnull";
		
		/**
		 * IRCBot channels
		 */
		private String[] channels;
		
		/**
		 * Builder (Live config)
		 * @param server
		 * @param nickname
		 */
		public Builder(String server, String nickname) {
			this.server = server;
			this.nickname = nickname;
			this.username = nickname;
			this.altNickname = "_" + nickname;
		}
		
		/**
		 * Builder (External Config file)
		 * @param config
		 */
		public Builder(XMLConfigurationReader config, File daemon) {
			this.server = config.getServer();
			this.nickname = config.getNickname();
			this.altNickname = config.getAlternative();
			this.port = config.getPort();
			this.secureConnection = config.isSecureConnection();
			this.username = config.getUsername();
			this.password = config.getPassword();
			this.channels = config.getChannels();
			this.realName = config.getRealName();
			this.channels = config.getChannels();
			this.brain = config.getBrain();
			this.dispatcher = config.getDispatcher();
			this.logger = config.getLogger();
			this.youtubeSearch = config.getYoutubeSearch();
			this.trigger = config.getTrigger();
			this.permission = config.getPermission();
			this.daemon = daemon;
		}	
		
		/**
		 * Set daemon
		 * @param daemon
		 * @return
		 */
		public Builder setDaemon(File daemon) {
			this.daemon = daemon;
			return this;
		}
		
		/**
		 * Set xml config
		 * @param config
		 * @return
		 */
		public Builder setConfig(File config) {
			this.config = config;
			return this;
		}
		
		/**
		 * Set brain
		 * @param brain
		 * @return
		 */
		public Builder setBrain(Brain brain) {
			this.brain = brain;
			return this;
		}
		
		/**
		 * Set logger
		 * @param logger
		 * @return
		 */
		public Builder setLogger(Logger logger) {
			this.logger = logger;
			return this;
		}
		
		/**
		 * Set event dispatcher
		 * @param dispatcher
		 * @return
		 */
		public Builder setEventDispatcher(EventDispatcher dispatcher) {
			this.dispatcher = dispatcher;
			return this;
		}
		
		/**
		 * Set youtube search
		 * @param youtube
		 * @return
		 */
		public Builder setYoutubeSearch(YoutubeSearch youtube) {
			this.youtubeSearch = youtube;
			return this;
		}
		
		/**
		 * Set trigger script
		 * @param trigger
		 * @return
		 */
		public Builder setTrigger(Trigger trigger) {
			this.trigger = trigger;
			return this;
		}
		
		/**
		 * Set Permission Script
		 * @param permission
		 * @return
		 */
		public Builder setPermission(Permission permission) {
			this.permission = permission;
			return this;
		}
		
		/**
		 * Set port
		 * @param port
		 * @return
		 */
		public Builder setPort(int port) {
			this.port = port;
			return this;
		}
		
		/**
		 * Set secure connection
		 * @param ssl
		 * @return
		 */
		public Builder setSecureConnection(boolean ssl) {
			this.secureConnection = ssl;
			return this;
		}
		
		/**
		 * Set alternative nickname
		 * @param nickname
		 * @return
		 */
		public Builder setAltNickname(String nickname) {
			this.altNickname = nickname;
			return this;
		}
		
		/**
		 * Set username
		 * @param username
		 * @return
		 */
		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		/**
		 * Set password
		 * @param password
		 * @return
		 */
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		/**
		 * Set real name
		 * @param realname
		 * @return
		 */
		public Builder setRealName(String realname) {
			this.realName = realname;
			return this;
		}
		
		/**
		 * Set channels
		 * @param channels
		 * @return
		 */
		public Builder setChannels(String... channels) {
			this.channels = channels;
			return this;
		}
		
		/**
		 * Build Configuration
		 * @return
		 */
		public Configuration build() {
			return new Configuration(this);
		}
		
		
		
	}
}
