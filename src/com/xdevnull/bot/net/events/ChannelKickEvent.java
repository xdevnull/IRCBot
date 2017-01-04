package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * ChannelKickEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ChannelKickEvent extends Event {
	
	/**
	 * User who performed kick
	 */
	private String operator;
	
	/**
	 * Channel
	 */
	private String channel;
	
	/**
	 * Kicked user
	 */
	private String kickedUser;
	
	/**
	 * Kick Message
	 */
	private String kickMessage;
	
	/**
	 * ChannelKickEvent
	 * @param source
	 * @param data
	 */
	public ChannelKickEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.operator = IRCUtil.getNickFromMask(source.getSource());
		this.channel = source.getDestination().split(" ")[0];
		this.kickedUser = source.getDestination().split(" ")[1];
		this.kickMessage = source.getContent();
	}
	
	/**
	 * Get Operator who performed kick
	 * @return
	 */
	public String getOperator() {
		return this.operator;
	}
	
	/**
	 * Get channel
	 * @return
	 */
	public String getChannel() {
		return this.channel;
	}
	
	/**
	 * User who has been kicked
	 * @return
	 */
	public String getKickedUser() {
		return this.kickedUser;
	}
	
	/**
	 * Get kick reason
	 * @return
	 */
	public String getKickReason() {
		return this.kickMessage;
	}
	
	/**
	 * Send message to the channel
	 * @param message
	 */
	public void sendChannel(String message) {
		this.getBot().sendChannel(this.channel, message);
	}
	
	/**
	 * Send message to the kicked user
	 * @param message
	 */
	public void sendKickedUser(String message) {
		this.getBot().sendPrivate(this.kickedUser, message);
	}

}
