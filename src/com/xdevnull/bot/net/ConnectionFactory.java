package com.xdevnull.bot.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.xdevnull.bot.app.Configuration;

/**
 * ConnectionFactory
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class ConnectionFactory {

	/**
	 * Create connection
	 * @param config
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static Connection create(Configuration config) {
		try {
			if(!config.isSecureConnection())
				return new Connection(new Socket(config.getServer().trim(), config.getPort()));	
			SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket)factory.createSocket(config.getServer().trim(), config.getPort());
			return new Connection(socket);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Unable to connect!");
			System.exit(100);
		}
		return null;
	}
}
