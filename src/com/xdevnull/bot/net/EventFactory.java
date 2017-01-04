package com.xdevnull.bot.net;

import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.net.events.*;

/**
 * EventFactory
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class EventFactory {

	/**
	 * Create Event From IRCMessage
	 * @param bot
	 * @param msg
	 * @return
	 */
	public static Event createFromMessage(IRCBot bot, IRCMessage msg) {
		
		switch(msg.getCommand().toLowerCase()) {
		
		case "privmsg":
			if(msg.getDestination().equals(bot.getConfiguration().getNickname()))
				return new PrivateMessageEvent(msg, bot);
			return new ChannelMessageEvent(msg, bot);
			
		case "join":
			return new ChannelJoinEvent(msg, bot);
		case "part":
			return new ChannelLeaveEvent(msg, bot);
		case "kick":
			return new ChannelKickEvent(msg, bot);
		
		case "topic":
			return new ChannelTopicEvent(msg, bot);
		
		case "mode":
			return new ChannelModeEvent(msg, bot);
		case "quit":
			return new UserQuitEvent(msg, bot);
		case "notice":
			return new NoticeMessageEvent(msg, bot);
		}
		return null;
	}

}
