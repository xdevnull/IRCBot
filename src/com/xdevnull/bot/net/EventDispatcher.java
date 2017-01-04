package com.xdevnull.bot.net;

import java.util.HashSet;

/**
 * EventDispatcher
 * @author xdevnull
 * @date Dec 30, 2016
 */
public final class EventDispatcher {
	
	/**
	 * Listeners
	 */
	private final HashSet<EventListener> listeners = new HashSet<EventListener>();
	
	/**
	 * FireEvent
	 * @param e
	 */
	public void fire(Event e) {
		for(EventListener listener : listeners)
			if(listener != null)
				listener.onEvent(e);
	}
	
	/**
	 * Create Listener by class
	 * @param listener
	 */
	public void addListener(Class<? extends EventListener> listener) {
		try {
			this.listeners.add(listener.newInstance());
			System.out.println("New listener added!");
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Unable to create listener");
		}
	}
	
	/**
	 * Add Listener
	 * @param listener
	 */
	public void addListener(EventListener listener) {
		listeners.add(listener);
	}
}
