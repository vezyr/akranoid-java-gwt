package pl.vezyr.arkanoidgwt.client.manager.input;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface of the input handler's class.
 * @author vezyr
 *
 */
public interface InputHandler {

	/**
	 * Returns the event handler of the specified input.
	 * @return EventHandler
	 */
	public EventHandler getHandler();
	
}
