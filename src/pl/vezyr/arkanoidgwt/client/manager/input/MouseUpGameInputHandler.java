package pl.vezyr.arkanoidgwt.client.manager.input;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.EventHandler;

public class MouseUpGameInputHandler implements InputHandler {

	private EventHandler handler;
	private int lastReleasedButton;
	
	public MouseUpGameInputHandler() {
		lastReleasedButton = -1;
		handler = new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				lastReleasedButton = event.getNativeButton();
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
	public int getLastReleasedButton() {
		return lastReleasedButton;
	}
	
	/**
	 * Clears information about last pressed button.
	 * Should be called after processing the input in the frame.
	 */
	public void clearLastReleasedButton() {
		lastReleasedButton = -1;
	}
}
