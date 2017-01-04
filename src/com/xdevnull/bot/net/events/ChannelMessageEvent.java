package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * ChannelMessageEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ChannelMessageEvent extends Event {
	
	/**
	 * IRC Nickname
	 */
	private String nickname;
	
	/**
	 * IRC Channel
	 */
	private String channel;
	
	/**
	 * IRC Message
	 */
	private String message;
	
	/**
	 * ChannelMessageEvent
	 * @param source
	 * @param data
	 */
	public ChannelMessageEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.nickname = IRCUtil.getNickFromMask(source.getSource());
		this.channel = source.getDestination();
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
	 * Get channel
	 * @return
	 */
	public String getChannel() {
		return this.channel;
	}
	
	/**
	 * Get message
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}
	
}
