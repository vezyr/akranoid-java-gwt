package pl.vezyr.arkanoidgwt.client.manager.input;

import java.util.HashSet;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;

/**
 * Input manager implementation for Gameplay.
 * @author vezyr
 *
 */
public class GameplayInputManager implements InputManager {

	private CanvasWrapper canvas;
	
	private MouseMoveGameplayInputHandler mouseMoveHandler;
	private Vector2<Integer> mousePositionLast;
	private Vector2<Integer> mousePositionCurrent;
	private Vector2<Integer> mousePositionDelta;
	
	private KeyboardKeyDownGameplayInputHandler keyboardKeyDownHandler;
	private KeyboardKeyUpGameplayInputHandler keyboardKeyUpHandler;
	private HashSet<Integer> pressedKeys;
	
	private static final Logger logger = Logger.getLogger(GameplayInputManager.class.getName());
	
	public GameplayInputManager(CanvasWrapper canvas) {
		this.canvas = canvas;
		
		mouseMoveHandler = new MouseMoveGameplayInputHandler();
		mousePositionLast = new Vector2<Integer>(0, 0);
		mousePositionCurrent = new Vector2<Integer>(0, 0);
		mousePositionDelta = new Vector2<Integer>(
			mousePositionCurrent.getX() - mousePositionLast.getX(),
			mousePositionCurrent.getY() - mousePositionLast.getY()
		);
		
		keyboardKeyDownHandler = new KeyboardKeyDownGameplayInputHandler();
		keyboardKeyUpHandler = new KeyboardKeyUpGameplayInputHandler();
		pressedKeys = new HashSet<Integer>();
	}
	
	@Override
	public void registerHandlers() {
		canvas.getCanvas().addMouseMoveHandler((MouseMoveHandler)mouseMoveHandler.getHandler());
		canvas.getCanvas().addKeyDownHandler((KeyDownHandler)keyboardKeyDownHandler.getHandler());
		canvas.getCanvas().addKeyUpHandler((KeyUpHandler)keyboardKeyUpHandler.getHandler());
	}

	@Override
	public Vector2<Integer> getMousePosition() {
		return mousePositionCurrent;
	}
	
	@Override
	public boolean hasMouseMoved() {
		return mousePositionDelta.getX() != 0 || mousePositionDelta.getY() != 0;
	}

	@Override
	public boolean isKeyPressed(int key) {
		return pressedKeys.contains(key);
	}
	
	@Override
	public void processInput() {
		mousePositionCurrent = mouseMoveHandler.getMousePosition();
		mousePositionDelta.set(
			mousePositionCurrent.getX() - mousePositionLast.getX(),
			mousePositionCurrent.getY() - mousePositionLast.getY()
		);
		mousePositionLast = new Vector2<Integer>(mousePositionCurrent);
		
		int pressedKey = keyboardKeyDownHandler.getLastPressedKey();
		if (pressedKey != -1 && !pressedKeys.contains(pressedKey)) {
			logger.info("Pressed key: " + pressedKey);
			pressedKeys.add(pressedKey);
			
		}
		
		int releasedKey = keyboardKeyUpHandler.getLastReleasedKey();
		if (releasedKey != -1 && pressedKeys.contains(releasedKey)) {
			logger.info("Released key: " + releasedKey);
			pressedKeys.remove(releasedKey);
		}
		
		keyboardKeyDownHandler.clearLastPressedKey();
		keyboardKeyUpHandler.clearLastReleasedKey();
	}	
}
