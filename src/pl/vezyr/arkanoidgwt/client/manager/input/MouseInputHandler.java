package pl.vezyr.arkanoidgwt.client.manager.input;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * An interface defining mouse input handler.
 * Any class can implement this interface to define that 
 * it can handle mouse input.
 * 
 * If object of the class that implements this interface
 * is added to one of the supported Register, the {@code handleMouseInput}
 * will be called automatically on every frame.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.register.ObjectsRegister
 *
 */
public interface MouseInputHandler {

	/**
	 * Method to handle the mouse input.
	 * Usually object should check if mouse is over the object
	 * and call specified action if left mouse button is pressed or released.
	 * 
	 * @param mousePosition Current mouse position.
	 * @param isLeftButtonPressed boolean True if left mouse button is currently pressed.
	 * @param isLeftButtonJustReleased boolean True if left mouse button was released on current frame.
	 */
	public void handleMouseInput(Vector2<Integer> mousePosition, boolean isLeftButtonPressed, boolean isLeftButtonJustReleased);
	
}
