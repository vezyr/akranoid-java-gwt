package pl.vezyr.arkanoidgwt.client.manager.input;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventHandler;

/**
 * Input handler implementation to handle keyboard's key release events.
 * @author vezyr
 *
 */
public class KeyboardKeyUpGameplayInputHandler implements InputHandler {

	private EventHandler handler;
	private int lastReleasedKey;
	
	public KeyboardKeyUpGameplayInputHandler() {
		lastReleasedKey = -1;
		handler = new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				lastReleasedKey = event.getNativeKeyCode();
			}
		};
	}
	
	@Override
	public EventHandler getHandler() {
		return handler;
	}
	
	/**
	 * Returns the key's code of last released key.
	 * @return int Last released key's code.
	 */
	public int getLastReleasedKey() {
		return lastReleasedKey;
	}
	
	/**
	 * Clears information about last released key.
	 * Should be called after processing the input in the frame.
	 */
	public void clearLastReleasedKey() {
		lastReleasedKey = -1;
	}
}
