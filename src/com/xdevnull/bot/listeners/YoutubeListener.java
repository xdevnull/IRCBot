package com.xdevnull.bot.listeners;

import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.net.events.ChannelMessageEvent;
import com.xdevnull.bot.net.events.PrivateMessageEvent;

/**
 * YoutubeSearch Listener
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class YoutubeListener extends EventListener {
	
	/**
	 * Private message
	 */
	public void onPrivateMessageEvent(PrivateMessageEvent e) {
		if(e.getBot().getConfiguration().getYoutubeSearch() != null) {
			if(e.getMessage().toLowerCase().startsWith("yt -q")) {
				String message = e.getMessage().substring(6);
				
				//Links
				String[] links = e.getBot().getConfiguration().getYoutubeSearch().search(message);
				if(links.length == 0)
					e.getBot().sendPrivate(e.getNickname(), "Sorry mate! I've found nothing for " + message);
				else
					e.getBot().sendPrivate(e.getNickname(), "Youtube search " + message + " result: " + links[0]);
			}
		}
	}
	
	/**
	 * Channel message
	 */
	public void onChannelMessageEvent(ChannelMessageEvent e) {
		if(e.getBot().getConfiguration().getYoutubeSearch() != null) {
			if(e.getMessage().toLowerCase().startsWith("yt -q")) {
				String message = e.getMessage().substring(6);
				
				//Links
				String[] links = e.getBot().getConfiguration().getYoutubeSearch().search(message);
				if(links.length == 0)
					e.getBot().sendPrivate(e.getChannel(), e.getNickname() + ": Sorry mate! I've found nothing for " + message);
				else
					e.getBot().sendPrivate(e.getChannel(), e.getNickname() + ": Youtube search " + message + " result: " + links[0]);
			}
		}
	}
}
