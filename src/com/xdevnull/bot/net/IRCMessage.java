package com.xdevnull.bot.net;

import java.sql.Timestamp;

/**
 * IRCMessage
 * @author xdevnull
 * @date Dec 30, 2016
 */
public final class IRCMessage {
	
	/**
	 * Message source
	 */
	private final String source;
	
	/**
	 * Message Command
	 */
	private final String command;
	
	/**
	 * Message Destination
	 */
	private final String destination;
	
	/**
	 * Message Content
	 */
	private final String content;
	
	/**
	 * Message Time
	 */
	private final Timestamp time;
	
	/**
	 * Original Message before parsing
	 */
	private final String original;
	
	/**
	 * ParsedMessage
	 * @param source
	 * @param command
	 * @param destination
	 * @param content
	 */
	public IRCMessage(String source, String command, String destination, String content, String original) {
		this.source = source != null ? source.trim() : source;
		this.command = command != null ? command.trim() : command;
		this.destination = destination != null ? destination.trim() : destination;
		this.content = content != null ? content.trim() : content;
		this.time = new Timestamp(System.currentTimeMillis());
		this.original = original;
	}

	/**
	 * Get message source
	 */
	public String getSource() {
		return this.source;
	}
	
	/**
	 * Get message command
	 */
	public String getCommand() {
		return this.command;
	}
	
	/**
	 * Get message destination
	 */
	public String getDestination() {
		return this.destination;
	}

	/**
	 * Get message content
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Get message time
	 * @return
	 */
	public Timestamp getTime() {
		return this.time;
	}
	
	/**
	 * Get original message before parsing
	 * @return
	 */
	public String getOriginal() {
		return this.original;
	}
	
	/**
	 * toString()
	 */
	public String toString() {
		return String.format("Source: %s\r\nCommand: %s\r\nDestination: %s\r\nContent: %s", this.source, this.command, this.destination, this.content);
	}

}