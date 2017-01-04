package com.xdevnull.bot.log;

import com.xdevnull.bot.net.IRCMessage;

/**
 * IRCBot Logger
 * @author xdevnull
 * @date Dec 30, 2016
 */
public interface Logger {
	
	/**
	 * Log Message
	 * @param message
	 */
	public void log(IRCMessage message);
}
