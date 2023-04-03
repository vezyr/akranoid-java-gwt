package pl.vezyr.arkanoidgwt.client.manager.input;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.EventHandler;

public class MouseDownGameInputHandler implements InputHandler {

	private EventHandler handler;
	private int lastPressedButton;
	
	public MouseDownGameInputHandler() {
		lastPressedButton = -1;
		handler = new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				lastPressedButton = event.getNativeButton();
			}
		};
	}
	
	@Override
	public EventHandler getHandler() {
		return handler;
	}

	/**
	 * Returns the key's code of last pressed button.
	 * @return int Last pressed button's code.
	 */
	public int getLastPressedButton() {
		return lastPressedButton;
	}
	
	/**
	 * Clears information about last pressed button.
	 * Should be called after processing the input in the frame.
	 */
	public void clearLastPressedButton() {
		lastPressedButton = -1;
	}
}
