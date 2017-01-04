package com.xdevnull.bot.listeners;

import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.net.events.PrivateMessageEvent;
import com.xdevnull.bot.script.Permission;

/**
 * 
 * @author xdevnull
 * @date Jan 5, 2017
 */
public class JoinPartMasterListener extends EventListener {

	public void onPrivateMessageEvent(PrivateMessageEvent e) {
		Permission permission = e.getBot().getConfiguration().getPermission();
		if (permission == null)
			return;
		if(permission.has(e.getNickname())) {
			if(e.getMessage().toLowerCase().startsWith("--join")) {
				String[] split = e.getMessage().split(" ");
				if(split.length == 0 && split[1].charAt(0) == '#') {
					e.getBot().sendRaw("JOIN " + split[1]);
				}
			}
			else if(e.getMessage().toLowerCase().startsWith("--part")) {
				String[] split = e.getMessage().split(" ");
				if(split.length == 0 && split[1].charAt(0) == '#') {
					e.getBot().sendRaw("PART " + split[1]);
				}
			}
		}
	}
}
