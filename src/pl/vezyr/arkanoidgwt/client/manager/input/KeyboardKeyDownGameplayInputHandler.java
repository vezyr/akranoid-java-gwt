package pl.vezyr.arkanoidgwt.client.manager.input;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.EventHandler;

/**
 * Input handler implementation to handle keyboard's key down events.
 * @author vezyr
 *
 */
public class KeyboardKeyDownGameplayInputHandler implements InputHandler {

	private EventHandler handler;
	private int lastPressedKey;
	
	public KeyboardKeyDownGameplayInputHandler() {
		lastPressedKey = -1;
		handler = new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				lastPressedKey = event.getNativeKeyCode();
			}
		};
	}
	
	@Override
	public EventHandler getHandler() {
		return handler;
	}
	
	/**
	 * Returns the key's code of last pressed key.
	 * @return int Last pressed key's code.
	 */
	public int getLastPressedKey() {
		return lastPressedKey;
	}
	
	/**
	 * Clears information about last pressed key.
	 * Should be called after processing the input in the frame.
	 */
	public void clearLastPressedKey() {
		lastPressedKey = -1;
	}

}
