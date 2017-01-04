package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * UserQuitEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class UserQuitEvent extends Event {
	
	/**
	 * IRC Nickname
	 */
	private String nickname;
	
	/**
	 * Quit Message
	 */
	private String message;
	
	
	/**
	 * UserQuitEvent
	 * @param source
	 * @param data
	 */
	public UserQuitEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.nickname = IRCUtil.getNickFromMask(source.getSource());
		this.message = source.getContent();
	}
	
	/**
	 * Get nickname
	 * @return
	 */
	public String getNickname() {
		return this.nickname;
	}
	
	/**
	 * Get Quit message
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

}
