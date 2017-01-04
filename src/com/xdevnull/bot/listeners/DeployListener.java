package com.xdevnull.bot.listeners;

import com.xdevnull.bot.support.BashExecutor;
import com.xdevnull.bot.net.EventListener;
import com.xdevnull.bot.net.events.PrivateMessageEvent;
import com.xdevnull.bot.script.Permission;

/**
 * DeployListener
 * @author xdevnull
 * @date Dec 30, 2016
 */
public class DeployListener extends EventListener {

	/**
	 * PrivateMessage
	 */
	public void onPrivateMessageEvent(PrivateMessageEvent e) {
		
		Permission permission = e.getBot().getConfiguration().getPermission();
		if(permission == null)
			return;
		if(e.getMessage().toLowerCase().equals("--deploy new source")) {
			if(permission.has(e.getNickname())) {
				try {
					e.getBot().sendPrivate(e.getNickname(), "Deploying! Wish me luck!");
					Thread.sleep(1000);
				}
				catch(Exception ex) {
					
				}
				BashExecutor.execute(e.getBot().getConfiguration().getDaemon().getPath(), "deploy");
			}
		}
		
		else if(e.getMessage().toLowerCase().equals("--restart")) {
			if(permission.has(e.getNickname())) {
				try {
					e.getBot().sendPrivate(e.getNickname(), "Restarting! Wish me luck!");
					Thread.sleep(1000);
				}
				catch(Exception ex) {
					
				}
				BashExecutor.execute(e.getBot().getConfiguration().getDaemon().getPath(), "restart");
			}
		}
		else if(e.getMessage().toLowerCase().equals("--restart compile")) {
			if(permission.has(e.getNickname())) {
				try {
					e.getBot().sendPrivate(e.getNickname(), "Restarting with compile option! Wish me luck!");
					Thread.sleep(1000);
				}
				catch(Exception ex) {
					
				}
				BashExecutor.execute(e.getBot().getConfiguration().getDaemon().getPath(), "compile-restart");
			}
		}
		else if(e.getMessage().toLowerCase().startsWith("--sleep")) {
			if(e.getBot().getConfiguration().getPermission().has(e.getNickname())) {
				String message = e.getMessage().substring(8);
				if(message == null || message.equals(""))
					message = "I was requested to sleep!";
				e.getBot().sendRaw("QUIT :" + message);
			}
		}
		
	}
}
