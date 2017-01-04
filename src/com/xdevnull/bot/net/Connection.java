package com.xdevnull.bot.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Socket Connection
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class Connection {
	
	/**
	 * Connection Socket
	 */
	private final Socket socket;
	
	/**
	 * Stream Reader
	 */
	private final BufferedReader reader;
	
	/**
	 * Stream Writer
	 */
	private final BufferedWriter writer;
	
	/**
	 * Connection
	 * @param socket
	 * @throws IOException
	 */
	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	/**
	 * Get reader
	 * @return
	 */
	public BufferedReader getReader() {
		return this.reader;
	}
	
	/**
	 * Get Writer
	 * @return
	 */
	public BufferedWriter getWriter() {
		return this.writer;
	}
	
	/**
	 * Close connection
	 * @throws IOException 
	 */
	public void close() throws IOException {
		this.reader.close();
		this.writer.close();
		this.socket.close();
	}
}
