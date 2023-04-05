package pl.vezyr.arkanoidgwt.client.event.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Base event class.
 * It provides static methods to add and remove listener for different events.
 * It also provides method to fire the event. 
 * 
 * All events should inherit from this class.
 * 
 * @author vezyr
 *
 */
public abstract class BaseEvent {

	private static final Logger logger = Logger.getLogger(BaseEvent.class.getName());
	private static Map<Class<? extends BaseEvent>, List<EventCallable<? extends BaseEvent>>> listeners = new HashMap<Class<? extends BaseEvent>, List<EventCallable<? extends BaseEvent>>>(); 
	
	/**
	 * Add listener to specific event.
	 * @param clazz Class of the event.
	 * @param action Action to be called when event is fired.
	 */
	public static void addListener(Class<? extends BaseEvent> clazz, EventCallable<? extends BaseEvent> action) {
		if (!listeners.containsKey(clazz)) {
			listeners.put(clazz, new ArrayList<EventCallable<? extends BaseEvent>>());
		}
		listeners.get(clazz).add(action);
	}
	
	/**
	 * Remove listener from specific event.
	 * @param clazz Class of the event.
	 * @param action Action to be removed.
	 */
	public static void removeListener(Class<? extends BaseEvent> clazz, EventCallable<? extends BaseEvent> action) {
		if (!listeners.containsKey(clazz)) {
			return;
		}
		listeners.get(clazz).remove(action);
	}
	
	/**
	 * Fires the event.
	 * The event (instance of the class of event) is also 
	 * passed as a parameter of the call action, wrapped by
	 * EventParam.
	 * @param <T> Class of the event.
	 * @see pl.vezyr.arkanoidgwt.client.event.base.EventParam
	 */
	public <T extends BaseEvent> void fire() {
		listeners.get(this.getClass()).forEach(action -> {
			try {
				action.call(new EventParam((T)this));
			} catch (Exception e) {
				logger.severe("Could not call action " + action + ". Reason: " + e.getMessage());
			}
		});
	}
}
