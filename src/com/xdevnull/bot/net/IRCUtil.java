package com.xdevnull.bot.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IRCUtil
 * @author xdevnull
 * @date Dec 30, 2016
 */
public final class IRCUtil {

    /**
     * Hostmask Pattern
     */
    private static Pattern hostMaskPattern = Pattern.compile("(?<nick>[^ ]+?)\\!(?<user>[^ ]+?)@(?<host>[^ ]+)");
    
	/**
	 * Get nickname from hostmask
	 * @param hostmask
	 * @return
	 */
	public static String getNickFromMask(String hostmask) {
		Matcher matcher = hostMaskPattern.matcher(hostmask);
		if(matcher.find())
			return matcher.group("nick").trim();
		return hostmask;
	}
	
	/**
	 * Determine whether message is private message
	 * @param message
	 * @param myNickname
	 * @return
	 */
	public static boolean isPrivateMessage(IRCMessage message, String myNickname) {
		return message.getDestination().trim().equals(myNickname);
	}
	
}
