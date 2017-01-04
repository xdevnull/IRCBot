package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * PrivateMessageEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class PrivateMessageEvent extends Event {
	
	/**
	 * Get nickname
	 */
	private String nickname;
	
	/**
	 * Get message
	 */
	private String message;
	
	/**
	 * PrivateMessageEvent
	 * @param source
	 * @param data
	 */
	public PrivateMessageEvent(IRCMessage source, IRCBot bot) {
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
	 * Get Message
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

}
