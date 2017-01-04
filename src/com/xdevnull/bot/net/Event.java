package com.xdevnull.bot.net;

import com.xdevnull.bot.app.IRCBot;

/**
 * Event
 * @author xdevnull
 * @date Dec 30, 2016
 */
public abstract class Event {

	/**
	 * Event source
	 */
	private final IRCMessage source;
	
	/**
	 * IRCBot instance
	 */
	private final IRCBot bot;
	
	/**
	 * Event
	 * @param source
	 * @param data
	 */
	public Event(IRCMessage source, IRCBot bot) {
		this.source = source;
		this.bot = bot;
	}
	
	/**
	 * Get event source
	 * @return
	 */
	public IRCMessage getSource() {
		return this.source;
	}
	
	/**
	 * Get IRCBot
	 * @return
	 */
	public IRCBot getBot() {
		return this.bot;
	}

}
