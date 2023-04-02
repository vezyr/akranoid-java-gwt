package pl.vezyr.arkanoidgwt.client.manager.input;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Input manager which handles input for specified scene.
 * Each scene should have implemented one input manger.
 * Each input manager can have more than input handlers.
 * @author vezyr
 *
 */
public interface InputManager {
	
	/**
	 * Register all necessary input handlers to canvas.
	 */
	public void registerHandlers();
	
	/**
	 * Returns the current mouse position on Canvas
	 * @return Vector2<Integer> Current mouse position.
	 * @see pl.vezyr.arkanoidgwt.client.helper.Vector2
	 */
	public Vector2<Integer> getMousePosition();
	
	/**
	 * Checks if mouse has moved science last frame. 
	 * @return boolean True if mouse position has changed, false otherwise.
	 */
	public boolean hasMouseMoved();
	
	/**
	 * Checks if mouse button is currently pressed (down).
	 * @param button Button's code.
	 * @return boolean True if button is pressed, false otherwise.
	 */
	public boolean isMouseButtonPressed(int button);
	
	/**
	 * Checks if key is currently pressed (down).
	 * @return boolean True if key is pressed, false otherwise.
	 * 
	 */
	public boolean isKeyPressed(int key);
	
	/**
	 * Returns the code of last released mouse button.
	 * @return int Code of released button.
	 */
	public int getLastReleasedButton();
	
	/**
	 * Checks if specified button was released in current frame.
	 * @param button Button's code.
	 * @return boolean True if button was released in the current frame, false otherwise.
	 */
	public boolean isButtonJustReleased(int button);
	
	/**
	 * Do any specific calculations on input.
	 */
	public void processInput();
	
}
