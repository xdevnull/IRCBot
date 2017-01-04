package com.xdevnull.bot.net;

import com.xdevnull.bot.net.events.*;

/**
 * EventListener
 * @author xdevnull
 * @date Dec 30, 2016
 */
public abstract class EventListener {
	
	/**
	 * OnEvent
	 * @param e
	 */
	public final void onEvent(Event e) {
		
		if(e == null)
			return;
		
		//Most common events comes first to reduce checking
		if(e instanceof ChannelJoinEvent)
			onChannelJoinEvent((ChannelJoinEvent) e);
		
		else if(e instanceof ChannelLeaveEvent)
			onChannelLeaveEvent((ChannelLeaveEvent) e);
		
		else if(e instanceof UserQuitEvent)
			onUserQuitEvent((UserQuitEvent) e);
		
		else if(e instanceof ChannelMessageEvent)
			onChannelMessageEvent((ChannelMessageEvent) e);
		
		else if(e instanceof PrivateMessageEvent)
			onPrivateMessageEvent((PrivateMessageEvent) e);
		
		else if(e instanceof ChannelKickEvent)
			onChannelKickEvent((ChannelKickEvent) e);
		
		else if(e instanceof ChannelModeEvent)
			onChannelModeEvent((ChannelModeEvent) e);
		
		else if(e instanceof NoticeMessageEvent)
			onNoticeMessageEvent((NoticeMessageEvent) e);
		else if(e instanceof ChannelTopicEvent)
			onChannelTopicEvent((ChannelTopicEvent) e); 
	
		
	}
	
	/**
	 * OnPrivateMessageEvent
	 * @param e
	 */
	public void onPrivateMessageEvent(PrivateMessageEvent e){}
	
	/**
	 * On Channel Message Event
	 * @param e
	 */
	public void onChannelMessageEvent(ChannelMessageEvent e){}
	
	/**
	 * On Channel Kick event
	 * @param e
	 */
	public void onChannelKickEvent(ChannelKickEvent e) {}
	
	/**
	 * On Channel Join event
	 * @param e
	 */
	public void onChannelJoinEvent(ChannelJoinEvent e) {}
	
	/**
	 * On Channel Leave Event
	 * @param e
	 */
	public void onChannelLeaveEvent(ChannelLeaveEvent e) {}
	
	/**
	 * On Channel Mode event
	 * @param e
	 */
	public void onChannelModeEvent(ChannelModeEvent e) {}
	
	/**
	 * On Channel Topic event
	 * @param e
	 */
	public void onChannelTopicEvent(ChannelTopicEvent e) {}
	
	/**
	 * On Notice Message Event
	 * @param e
	 */
	public void onNoticeMessageEvent(NoticeMessageEvent e) {}
	
	/**
	 * On user quit event
	 * @param e
	 */
	public void onUserQuitEvent(UserQuitEvent e) {}
}