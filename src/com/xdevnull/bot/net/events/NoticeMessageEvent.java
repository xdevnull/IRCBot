package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * NoticeMessageEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class NoticeMessageEvent extends Event {
	
	/**
	 * Nickname who sent the notice
	 */
	private String nickname;
	
	/**
	 * Notice Destination whether channel or private
	 */
	private String destination;
	
	/**
	 * Notice message
	 */
	private String message;
	
	/**
	 * NoticeEvent
	 * @param source
	 * @param data
	 */
	public NoticeMessageEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.nickname = IRCUtil.getNickFromMask(source.getSource());
		this.destination = source.getDestination();
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
	 * Get notice destination
	 * @return
	 */
	public String getDestination() {
		return this.destination;
	}
	
	/**
	 * Get notice message
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

}
