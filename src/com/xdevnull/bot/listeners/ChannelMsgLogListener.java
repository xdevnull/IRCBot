package com.xdevnull.bot.listeners;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.net.events.ChannelMessageEvent;

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
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(e.getSource().getTime().getTime());
		String filename = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime()) + "-" + e.getChannel() + ".txt";
		String messageTime = "[" + (new SimpleDateFormat("hh:mm:ss")).format(cal.getTime()) + "]";
		String userAndMessage = e.getNickname() + ": " + e.getMessage();
		synchronized(this) {
			try(FileWriter fw = new FileWriter(filename, true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(messageTime + " " + userAndMessage);
				} catch (IOException ex) {
				    System.out.println("Unable to write log");
				}
		}
	}
}
