package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * ChannelJoinEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ChannelJoinEvent extends Event {
	
	/**
	 * IRCNickname who joined
	 */
	private final String nickname;
	
	/**
	 * IRC Channel
	 */
	private final String channel;
	
	/**
	 * Join Event
	 * @param source
	 * @param data
	 */
	public ChannelJoinEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.nickname = IRCUtil.getNickFromMask(source.getSource());
		this.channel = source.getDestination();
	}
	
	/**
	 * Send Message to Channel
	 * @param message
	 */
	public void sendToChannel(String message) {
		this.getBot().sendChannel(this.channel, message);
	}
	
	/**
	 * Send Message to Nickname
	 * @param message
	 */
	public void sendToNickname(String message) {
		this.getBot().sendPrivate(this.nickname, message);
	}
	
	/**
	 * Get Nickane
	 * @return
	 */
	public String getNickName() {
		return this.nickname;
	}
	
	/**
	 * Get channel
	 * @return
	 */
	public String getChannel() {
		return this.channel;
	}
	
}
