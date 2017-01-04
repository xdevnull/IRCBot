package com.xdevnull.bot.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IRCMessage Parser
 * @author xdevnull
 * @date Dec 30, 2016
 */
public final class Parser {
	
    /**
     * Regular Expression Pattern
     */
    private final Pattern pattern = Pattern.compile("(?::(?<prefix>[^ ]+) +)?(?<command>[^ :]+)(?<middle>(?: +[^ :]+)*)(?<coda> +:(?<trailing>.*)?)?");

    /**
     * Pattern Matcher
     */
    private Matcher m;
    
    /**
     * Parse Incoming Message
     * @param message
     * @return
     */
    public IRCMessage parse(String message) {
    	if(message == null || message.equals(""))
    		return null;
    	
        m = pattern.matcher(message);

        //If any matches
        //Return instance of Message
        if(m.find())
            return new IRCMessage(m.group("prefix"),m.group("command"),m.group("middle"),m.group("trailing"), message);
    	return null;
    }

}
