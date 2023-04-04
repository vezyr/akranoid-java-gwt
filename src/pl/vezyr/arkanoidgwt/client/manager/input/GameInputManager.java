package pl.vezyr.arkanoidgwt.client.manager.input;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;

/**
 * Input manager implementation for Gameplay.
 * @author vezyr
 *
 */
public class GameInputManager implements InputManager {

	private CanvasWrapper canvas;
	
	private MouseMoveGameplayInputHandler mouseMoveHandler;
	private Vector2<Integer> mousePositionLast;
	private Vector2<Integer> mousePositionCurrent;
	private Vector2<Integer> mousePositionDelta;
	private MouseDownGameInputHandler mouseDownHandler;
	private MouseUpGameInputHandler mouseUpHandler;
	private HashSet<Integer> pressedButtons;
	private int lastPressedMouseButton;
	private int lastReleasedMouseButton;
	
	private KeyboardKeyDownGameplayInputHandler keyboardKeyDownHandler;
	private KeyboardKeyUpGameplayInputHandler keyboardKeyUpHandler;
	private HashSet<Integer> pressedKeys;
	private int lastReleasedKey;
	
	private static final Logger logger = Logger.getLogger(GameInputManager.class.getName());
	
	public GameInputManager(CanvasWrapper canvas) {
		this.canvas = canvas;
		
		mouseMoveHandler = new MouseMoveGameplayInputHandler();
		mousePositionLast = new Vector2<Integer>(0, 0);
		mousePositionCurrent = new Vector2<Integer>(0, 0);
		mousePositionDelta = new Vector2<Integer>(
			mousePositionCurrent.getX() - mousePositionLast.getX(),
			mousePositionCurrent.getY() - mousePositionLast.getY()
		);
		
		mouseDownHandler = new MouseDownGameInputHandler();
		mouseUpHandler = new MouseUpGameInputHandler();
		pressedButtons = new HashSet<Integer>();
		
		lastPressedMouseButton = -1;
		lastReleasedMouseButton = -1;
		
		keyboardKeyDownHandler = new KeyboardKeyDownGameplayInputHandler();
		keyboardKeyUpHandler = new KeyboardKeyUpGameplayInputHandler();
		pressedKeys = new HashSet<Integer>();
		
		lastReleasedKey = -1;
	}
	
	@Override
	public void registerHandlers() {
		canvas.getCanvas().addMouseMoveHandler((MouseMoveHandler)mouseMoveHandler.getHandler());
		canvas.getCanvas().addMouseDownHandler((MouseDownHandler)mouseDownHandler.getHandler());
		canvas.getCanvas().addMouseUpHandler((MouseUpHandler)mouseUpHandler.getHandler());
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
	public Vector2<Integer> getMousePositionDelta() {
		return mousePositionDelta;
	}
	
	@Override
	public boolean isMouseButtonPressed(int button) {
		return pressedButtons.contains(button);
	}

	@Override
	public boolean isKeyPressed(int key) {
		return pressedKeys.contains(key);
	}
	
	@Override
	public int getLastReleasedButton() {
		return lastReleasedMouseButton;
	}
	
	@Override
	public boolean isButtonJustReleased(int button) {
		return lastReleasedMouseButton == button;
	}
	
	@Override
	public int getLastPressedButton() {
		return lastPressedMouseButton;
	}
	
	@Override
	public boolean isButtonJustPressed(int button) {
		return lastPressedMouseButton == button;
	}
	
	@Override
	public int getLastReleasedKey() {
		return lastReleasedKey;
	}
	
	@Override
	public boolean isKeyJustReleased(int key) {
		return lastReleasedKey == key;
	}
	
	@Override
	public Set<Integer> getAllPressedKeys() {
		return new HashSet<Integer>(pressedKeys);
	}
	
	@Override
	public void processInput() {
		mousePositionCurrent = mouseMoveHandler.getMousePosition();
		mousePositionDelta.set(
			mousePositionCurrent.getX() - mousePositionLast.getX(),
			mousePositionCurrent.getY() - mousePositionLast.getY()
		);
		mousePositionLast = new Vector2<Integer>(mousePositionCurrent);
		
		int pressedButton = mouseDownHandler.getLastPressedButton();
		lastPressedMouseButton = pressedButton;
		if (pressedButton != -1 && !pressedButtons.contains(pressedButton)) {
			pressedButtons.add(pressedButton);
		}
		
		int releasedButton = mouseUpHandler.getLastReleasedButton();
		lastReleasedMouseButton = releasedButton;
		if (releasedButton != -1 && pressedButtons.contains(releasedButton)) {
			pressedButtons.remove(releasedButton);
		}
		
		mouseDownHandler.clearLastPressedButton();
		mouseUpHandler.clearLastReleasedButton();
		
		int pressedKey = keyboardKeyDownHandler.getLastPressedKey();
		if (pressedKey != -1 && !pressedKeys.contains(pressedKey)) {
			logger.info("Pressed key: " + pressedKey);
			pressedKeys.add(pressedKey);
			
		}
		
		int releasedKey = keyboardKeyUpHandler.getLastReleasedKey();
		lastReleasedKey = releasedKey;
		if (releasedKey != -1 && pressedKeys.contains(releasedKey)) {
			logger.info("Released key: " + releasedKey);
			pressedKeys.remove(releasedKey);
		}
		
		keyboardKeyDownHandler.clearLastPressedKey();
		keyboardKeyUpHandler.clearLastReleasedKey();
	}	
}
