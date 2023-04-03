package pl.vezyr.arkanoidgwt.client.manager.input;

import java.util.Set;

/**
 * An interface defining keyboard input handler.
 * Any class can implement this interface to define that 
 * it can handle keyboard input.
 * 
 * If object of the class that implements this interface
 * is added to one of the supported Register, the {@code handleKeyboardInput}
 * will be called automatically on every frame.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.register.ObjectsRegister
 *
 */
public interface KeyboardInputHandler {

	/**
	 * Method to handle the keyboard input.
	 * 
	 * @param pressedKeys Set of currently pressed keys (as int codes)
	 * @param justReleasedKey int The code of just released key (last released key on this frame).
	 */
	public void handleKeyboardInput(Set<Integer> pressedKeys, int justReleasedKey);
	
}
