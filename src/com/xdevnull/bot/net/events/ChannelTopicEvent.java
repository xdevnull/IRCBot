package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * ChannelTopicEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ChannelTopicEvent extends Event {
	
	/**
	 * Operator who changed topic
	 */
	private String operator;
	
	/**
	 * Channel
	 */
	private String channel;
	
	/**
	 * Topic
	 */
	private String topic;
	
	/**
	 * ChannelTopicEvent
	 * @param source
	 * @param bot
	 */
	public ChannelTopicEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.operator = IRCUtil.getNickFromMask(source.getSource());
		this.channel = source.getDestination();
		this.topic = source.getContent();
	}
	
	/**
	 * Get Operator
	 * @return
	 */
	public String getOperator() {
		return this.operator;
	}
	
	/**
	 * Get Channel
	 * @return
	 */
	public String getChannel() {
		return this.channel;
	}
	
	/**
	 * Get topic
	 * @return
	 */
	public String getTopic() {
		return this.topic;
	}
	
}
