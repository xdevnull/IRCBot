package com.xdevnull.bot.log;

import com.xdevnull.bot.net.IRCMessage;

/**
 * TerminalLogger
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class TerminalLogger implements Logger {

	/**
	 * Log Message 
	 */
	@Override
	public void log(IRCMessage message) {
		System.out.println(message.getOriginal());
	}
}
