package com.xdevnull.bot.support;

import java.io.File;
import java.io.IOException;

/**
 * Execute BashScript
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class BashExecutor {

	/**
	 * Execute a bash script
	 * @param bashPath
	 * @param bashArgs
	 */
	public static void execute(String bashPath, String... bashArgs) {
		if(bashPath == null)
			return;
		
		//Check if file exists
		//If it doesn't exists or it's a directory then leave
		File file = new File(bashPath);
		if(!file.exists() || file.isDirectory())
			return;
		
		//Execute
		try {
			Runtime.getRuntime().exec(new String[]{"/bin/sh" ,"-c", bashPath + " " + String.join(" ", bashArgs)});			
		}
		catch(IOException e) {
			System.out.println("Unable to execute!");
		}	
	}

}
