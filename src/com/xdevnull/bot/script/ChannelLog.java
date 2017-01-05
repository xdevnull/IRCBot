package com.xdevnull.bot.script;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ChannelLog
 * @author xdevnull
 * @date Jan 5, 2017
 */
public class ChannelLog {
	
	/**
	 * Channel
	 */
	private final String channel;
	
	/**
	 * Log date
	 */
	private String date = "";
	
	/**
	 * File writer
	 */
	private PrintWriter writer;
	
	/**
	 * Channel
	 * @param channel
	 */
	public ChannelLog(String channel) {
		this.channel = channel;
	}
	
	/**
	 * Write to log
	 * @param date
	 * @param message
	 */
	public synchronized void log(String date, String message) {
		this.validateAndCreate(date);
		if(this.writer != null) {
			this.writer.println(message);
			this.writer.flush();
		}
	}
	
	/**
	 * Get channel
	 * @return
	 */
	public String getChannel() {
		return this.channel;
	}
	
	/**
	 * Validate and create
	 * @param date
	 */
	private void validateAndCreate(String date) {
		if(date == null)
			return;
		if(this.date.equals(date))
			return;
		String filename = date + "-" + this.channel + ".txt";
		this.date = date;
			try {
				FileWriter fw = new FileWriter(filename, true);
				BufferedWriter bw = new BufferedWriter(fw);
				this.writer = new PrintWriter(bw);				
			}
			catch(IOException e) {
				System.out.println(e);
			}
	}
}
