package com.xdevnull.bot.net;

import java.io.BufferedReader;
import java.io.IOException;

import com.xdevnull.bot.app.IRCBot;

/**
 * InputThread
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class InputThread implements Runnable {

	/**
	 * IRCBot instance
	 */
	private final IRCBot bot;
	
	/**
	 * Connection
	 */
	private final Connection connection;
	
	/**
	 * Message Parser
	 */
	private final Parser parser;
	
	/**
	 * InputThread
	 * @param bot
	 * @param connection
	 * @param parser
	 */
	public InputThread(IRCBot bot, Connection connection, Parser parser) {
		this.bot = bot;
		this.connection = connection;
		this.parser = parser;
	}

	@Override
	public void run() {
		BufferedReader reader = this.connection.getReader();
		String input = null;
		try {
			while((input = reader.readLine()) != null) {
				
				//Parse Incoming message
				IRCMessage prMsg = parser.parse(input);
				if(prMsg == null)
					continue;
				
				if(bot.getConfiguration().getLogger() != null)
					bot.getConfiguration().getLogger().log(prMsg);
				
				//PING-PONG With Server
				if(input.startsWith("PING")) {
					this.bot.sendRaw("PONG " + input.substring(5));
					continue;
				}
	
				
				//Check if bot-nickname change
				//Update it in configuration
				if(prMsg.getCommand()!= null && prMsg.getCommand().equalsIgnoreCase("NICK")) {
					String oldNickname = IRCUtil.getNickFromMask(prMsg.getSource());
					String newNickname = prMsg.getContent().trim();
					if(bot.getConfiguration().getNickname().equals(oldNickname))
						bot.getConfiguration().setNickname(newNickname);
					continue;
				}
				
				//Check if NickServ identification required
				if(prMsg.getSource() != null && IRCUtil.getNickFromMask(prMsg.getSource()).equalsIgnoreCase("NickServ")) {
					if(prMsg.getCommand() != null && prMsg.getCommand().equalsIgnoreCase("NOTICE"))
						
						//Works with freenode irc services
						//Not sure if it works with other irc services
						//Should be updated if message are different between irc services
						if(prMsg.getContent() != null && prMsg.getContent().contains("This nickname is registered")) {
							//Send current password
							//if available
							if(bot.getConfiguration().getPassword() != null) {
								//Identification uses Username which by defaults equals to IRCBot nickname
								bot.sendPrivate("NICKSERV", "IDENTIFY " + bot.getConfiguration().getUsername() + " " + bot.getConfiguration().getPassword());
								continue;
							}
						}
				}
				
				//Fire event
				if(bot.getConfiguration().getEventDispatcher() != null)
					bot.getConfiguration().getEventDispatcher().fire(EventFactory.createFromMessage(bot, prMsg));
				
			}
			connection.close();
		}
		catch(IOException e) {
			
		}
		
	}
}
