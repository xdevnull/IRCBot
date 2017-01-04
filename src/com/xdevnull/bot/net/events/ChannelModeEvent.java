package com.xdevnull.bot.net.events;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.Event;
import com.xdevnull.bot.net.IRCMessage;
import com.xdevnull.bot.net.IRCUtil;

/**
 * ChannelModeEvent
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ChannelModeEvent extends Event {
	
	/**
	 * Operator who performed the mode
	 */
	private String operator;
	
	/**
	 * Channel
	 */
	private String channel;
	
	/**
	 * Mode
	 */
	private String mode;
	
	/**
	 * Mode Parameters
	 */
	private String paramaters;
	
	/**
	 * ChannelModeEvent
	 * @param source
	 * @param data
	 */
	public ChannelModeEvent(IRCMessage source, IRCBot bot) {
		super(source, bot);
		this.operator = IRCUtil.getNickFromMask(source.getSource());
		String[] splits = source.getDestination().split(" ");
		if(splits.length >= 1)
			this.channel = source.getDestination().split(" ")[0];
		if(splits.length >= 2) //Useless if statement
			this.mode = source.getDestination().split(" ")[1];
		if(splits.length >= 3)
			this.paramaters = source.getDestination().split(" ")[2];
	}
	
	/**
	 * Get Operator
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
	 * Get mode
	 * @return
	 */
	public String getMode() {
		return this.mode;
	}
	
	/**
	 * Get parameters
	 * @return
	 */
	public String getParameters() {
		return this.paramaters;
	}
}
