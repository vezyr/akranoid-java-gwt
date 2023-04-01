package pl.vezyr.arkanoidgwt.client.manager.input;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.EventHandler;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Mouse input handler when on gameplay state.
 * @author vezyr
 *
 */
public class MouseMoveGameplayInputHandler implements InputHandler {

	private EventHandler handler;
	private Vector2<Integer> mousePosition;
	
	public MouseMoveGameplayInputHandler() {
		mousePosition = new Vector2<Integer>(0, 0);
		handler = new MouseMoveHandler() {
			
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				mousePosition.set(event.getX(), event.getY());
			}
		};
	}
	
	@Override
	public EventHandler getHandler() {
		return handler;
	}

	/**
	 * Return the mouse position on scene (on Canvas).
	 * @return Vector2<Integer> Coordinates of the mouse cursor.
	 */
	public Vector2<Integer> getMousePosition() {
		return mousePosition;
	}
}
