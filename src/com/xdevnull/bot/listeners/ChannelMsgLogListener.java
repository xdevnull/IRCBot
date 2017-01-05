package com.xdevnull.bot.listeners;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.net.events.ChannelJoinEvent;
import com.xdevnull.bot.net.events.ChannelLeaveEvent;
import com.xdevnull.bot.net.events.ChannelMessageEvent;
import com.xdevnull.bot.script.ChannelLog;

/**
 * Log channel message
 * @author xdevnull
 * @date Jan 3, 2017
 */
public class ChannelMsgLogListener extends EventListener {

	/**
	 * Log message
	 */
	public void onChannelMessageEvent(ChannelMessageEvent e) {
		if(e == null)
			System.out.println("Event is null");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(e.getSource().getTime().getTime());
		String date = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
		String filename = date + "-" + e.getChannel() + ".txt";
		String messageTime = "[" + (new SimpleDateFormat("hh:mm:ss")).format(cal.getTime()) + "]";
		String userAndMessage = e.getNickname() + ": " + e.getMessage();
		synchronized(this) {
			for(ChannelLog log : e.getBot().getChannelLog())
				if(log.getChannel().equals(e.getChannel()))
					log.log(date, messageTime + " " + userAndMessage);
		}
	}
	
	/**
	 * Register new ChannelLog
	 */
	public void onChannelJoinEvent(ChannelJoinEvent e) {
		if(e.getNickName().equals(e.getBot().getConfiguration().getNickname())) {
			e.getBot().getChannelLog().add(new ChannelLog(e.getChannel()));
		}
	}
	
	/**
	 * Remove ChannelLog
	 */
	public void onChannelLeaveEvent(ChannelLeaveEvent e) {
		if(e.getNickname().equals(e.getBot().getConfiguration().getNickname())) {
			for(int i = 0; i < e.getBot().getChannelLog().size(); i++) {
				if(e.getBot().getChannelLog().get(i).getChannel().equals(e.getChannel())) {
					e.getBot().getChannelLog().remove(i);
					return;
				}
			}
		}
	}
}
