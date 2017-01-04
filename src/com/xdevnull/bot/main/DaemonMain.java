package com.xdevnull.bot.main;

import java.io.File;

import com.xdevnull.bot.app.Configuration;
import com.xdevnull.bot.app.IRCBot;
import com.xdevnull.bot.support.Factory;


/**
 * IRCBot Daemon Main
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class DaemonMain {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length != 2) {
			System.out.println("Config/Daemon files are required!");
			System.exit(500);
		}

		File config = new File(args[0]);
		File daemon = new File(args[1]);
		if(!config.exists() || !daemon.exists() || config.isDirectory() || daemon.isDirectory()) {
			System.out.println("Config/Daemon files missing!");
			System.out.println("Terminating Scrip!");
			System.exit(500);
		}
		Configuration.Builder builder = new Configuration.Builder(Factory.createXMLConfigReader(config), daemon);
		IRCBot bot = new IRCBot(builder.build());
		bot.connect();
	}

}
