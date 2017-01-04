package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * ChannelLeaveEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ChannelLeaveEvent extends Event {
	
	/**
	 * Nickname who left channel
	 */
	private String nickname;
	
	/**
	 * channel
	 */
	private String channel;
	
	/**
	 * part message
	 */
	private String message;
	
	/**
	 * ChannelLeaveEvent
	 * @param source
	 * @param data
	 */
	public ChannelLeaveEvent(IRCMessage source, IRCBot bot) {
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
	 * Get leave message
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

}
