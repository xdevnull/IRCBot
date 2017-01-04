package com.xdevnull.bot.listeners;

import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.net.events.ChannelMessageEvent;
import com.xdevnull.bot.net.events.PrivateMessageEvent;

/**
 * TriigersListener
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class TriggerListener extends EventListener {
	
	/**
	 * Private message
	 */
	public void onPrivateMessageEvent(PrivateMessageEvent e) {
		
		if(e.getBot().getConfiguration().getTrigger() == null)
			return;
		
		//Learn trigger
		if(e.getMessage().startsWith("--learn trigger")) {
			if(e.getBot().getConfiguration().getPermission() == null)
				return;
			//Check if nickname permitted
			if(e.getBot().getConfiguration().getPermission().has(e.getNickname())) {
				String[] split = e.getMessage().split(" ");
				if(split.length >= 4) {
					if(split[2].startsWith("!")) {
						int length = split[0].length() + split[1].length() + split[2].length();
						e.getBot().getConfiguration().getTrigger().add(split[2], e.getMessage().substring(length + 3));
						e.getBot().getConfiguration().getBrain().commit(); //commit
						e.getBot().sendPrivate(e.getNickname(), "Woha I learned new trigger!");
					}
				}
				
			}
		}
		else if(e.getMessage().startsWith("!")) {
			String[] split = e.getMessage().split(" ");
			if(split.length == 1) {
				if(e.getBot().getConfiguration().getTrigger().has(split[0]))
					e.getBot().sendPrivate(e.getNickname(), e.getBot().getConfiguration().getTrigger().get(split[0]));
			}
		}
	}
	
	/**
	 * Channel message
	 */
	public void onChannelMessageEvent(ChannelMessageEvent e) {
		if(e.getMessage().startsWith("!")) {
			String[] split = e.getMessage().split(" ");
			if(split.length == 1 || split.length == 2) {
				if(e.getBot().getConfiguration().getTrigger().has(split[0])) {
					if(split.length == 1)
						e.getBot().sendChannel(e.getChannel(), e.getBot().getConfiguration().getTrigger().get(split[0]));
					else
						e.getBot().sendChannel(e.getChannel(), split[1] + ": " + e.getBot().getConfiguration().getTrigger().get(split[0]));

				}
			}
		}
	}
}
