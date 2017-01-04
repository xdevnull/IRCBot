package com.xdevnull.bot.app;

import java.io.IOException;

import com.xdevnull.bot.net.Connection;
import com.xdevnull.bot.net.ConnectionFactory;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.InputThread;
import com.xdevnull.bot.net.Parser;

/**
 * IRCBot
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class IRCBot {
	
	/**
	 * IRCBot Configuration
	 */
	private final Configuration configuration;
	
	/**
	 * IRCBot Socket Connection
	 */
	private Connection connection;
	
	/**
	 * IRCBot Constructor
	 * @param configuration
	 */
	public IRCBot(Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * Send Message
	 * @param message
	 * @throws IOException 
	 */
	public void sendRaw(String message) {
		try {
			this.connection.getWriter().write(message + "\r\n");
			this.connection.getWriter().flush();	
		}
		catch(IOException e) {
			System.out.println("Unable to send message!");
		}
	}
	
	/**
	 * Send private message
	 * @param nickname
	 * @param message
	 * @throws IOException 
	 */
	public void sendPrivate(String nickname, String message) {
		sendRaw("PRIVMSG " + nickname + " :" + message);
	}
	
	/**
	 * Send channel message
	 * @param channel
	 * @param message
	 * @throws IOException 
	 */
	public void sendChannel(String channel, String message) {
		if(channel.startsWith("#"))
			this.sendPrivate(channel, message);
	}
	
	/**
	 * Connect IRCBot
	 */
	public void connect() {
		//Create Connection
		this.connection = ConnectionFactory.create(this.configuration);
		if(this.connection == null) {
			System.out.println("Unable to connect!");
			System.exit(1);
		}
		
		try {
			//Register Nickname for server
			//Check if nickname not in use
			this.sendRaw("NICK " + this.configuration.getNickname());
			this.sendRaw("USER " + this.configuration.getUsername() + " 1 * : " + this.configuration.getRealname());
			
			String input = null;
			Parser msgParser = new Parser();
			int nickInUseAttempts = 0;
			while((input = this.connection.getReader().readLine()) != null) {
				
				//Parse Incoming message
				IRCMessage prMsg = msgParser.parse(input);
				if(prMsg == null)
					continue;
				
				if(this.configuration.getLogger() != null)
					this.configuration.getLogger().log(prMsg);
				
				//PING-PONG With Server
				if(prMsg.getCommand().equalsIgnoreCase("PING")) {
					this.sendRaw("PONG " + input.substring(5));
					continue;
				}
				
				//Check Nickname already in use
				if(prMsg.getCommand()!= null && (prMsg.getCommand().equals("433") || prMsg.getCommand().equals("437"))) {
					
					//Check if 
					if(nickInUseAttempts == 1) {
						System.out.println("Nickname & Alternative Nickname are both in use!");
						System.out.println("Please update configs and run again!");
						System.exit(1000);
					}
					
					//Register with alternative nickname
					//Update nickname in configurations
					this.configuration.setNickname(this.configuration.getAltNickname());
					this.sendRaw("NICK " + this.configuration.getAltNickname());
					nickInUseAttempts++;
					
					continue;
				}
				
				//Check if connection successful
				if(prMsg.getCommand() != null && prMsg.getCommand().equals("004")) {
					
					String server = prMsg.getSource().trim();
					String nickname = prMsg.getDestination().trim().split(" ")[0];
					
					if(this.configuration.getChannels() != null)
						this.sendRaw("JOIN " + String.join(",", this.configuration.getChannels()));
					
					//Update connected server
					if(!this.configuration.getServer().equals(server))
						this.configuration.setServer(server);
					
					//Update connected nickname
					//Not-required, However. Not sure if some IRC Server generate nicknames for 433 response
					//Nickname already in use
					if(!this.configuration.getNickname().equals(nickname))
						this.configuration.setNickname(nickname);
					
					break;
				}
				
			}
			
			//Start thread
			new Thread(new InputThread(this, this.connection, msgParser)).start();	
		}
		catch(IOException e) {
			System.out.println("Unable to read/write from stream!");
			System.exit(1);
		}
	}
	
	/**
	 * Get IRCBot Configuration
	 * @return
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}
}
