package pl.vezyr.arkanoidgwt.client.manager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.dom.client.NativeEvent;

import pl.vezyr.arkanoidgwt.client.event.ArkanoidGwtEvent;
import pl.vezyr.arkanoidgwt.client.event.LeaveGameButtonClickEvent;
import pl.vezyr.arkanoidgwt.client.event.NewGameButtonClickEvent;
import pl.vezyr.arkanoidgwt.client.event.QuitGameButtonClickEvent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.manager.audio.AudioManager;
import pl.vezyr.arkanoidgwt.client.manager.audio.GameAudioManager;
import pl.vezyr.arkanoidgwt.client.manager.input.GameInputManager;
import pl.vezyr.arkanoidgwt.client.manager.input.InputManager;
import pl.vezyr.arkanoidgwt.client.manager.input.KeyboardInputHandler;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.view.ui.GameplayUiManager;
import pl.vezyr.arkanoidgwt.client.view.ui.MainMenuUiManager;
import pl.vezyr.arkanoidgwt.client.view.ui.UiManager;

/**
 * Main manger of the game.
 * Handles different states of the game,
 * ie. main menu is loaded, gameplay is active etc.
 * @author vezyr
 *
 */
public class GameManager {

	private GameState state = null;
	
	private static GameplayManager gameplayManager;
	private static MainMenuManager mainMenuManager;
	
	private static CanvasManager canvasManager;
	private static InputManager inputManager;
	private static UiManager uiManager;
	private static SceneManager sceneManager;
	private static ConfigManager configManager;
	private static AudioManager audioManager;
	
	private double lastFrameTimestamp = 0;
	
	private static final Queue<ArkanoidGwtEvent> eventsQueue = new LinkedList<ArkanoidGwtEvent>();
	
	private static final Logger logger = Logger.getLogger(GameManager.class.getName());
	
	public GameManager() {
		configManager = new SimpleInMemeoryConfigManager();
		configManager.load();
		
		canvasManager = new CanvasManager();
		gameplayManager = new GameplayManager(canvasManager);
		mainMenuManager = new MainMenuManager();
		audioManager = new GameAudioManager();
		
		onStateChangeToMainMenu();
		state = GameState.MAIN_MENU;
		
		logger.info("Game Manager constructed.");
	}
	
	/**
	 * Entry point of the game.
	 * Initialize any additional objects and run main game loop.
	 */
	public void run() {
		// Animation callback used as game loop to sync
		// frame rate with platform.
		AnimationCallback gameLoop = new AnimationCallback() {
			
			@Override
			public void execute(double timestamp) {
				double deltaTime = timestamp - lastFrameTimestamp;
				
				inputManager.processInput();
				notifyAllInputHandlers();
				handleEventsQueue();
				sceneManager.update(deltaTime);
				sceneManager.redraw();
				
				lastFrameTimestamp = timestamp;
				if (state != GameState.QUIT_GAME) {
					AnimationScheduler.get().requestAnimationFrame(this);
				}
			}
		};
		
		 AnimationScheduler.get().requestAnimationFrame(gameLoop);
	}
	
	/**
	 * Changes the state of the game to the new state.
	 * If the new state is the same as old, no action is performed,
	 * so it's safe to invoke this method multiple times.
	 * @param newState
	 */
	public void changeState(GameState newState) {
		if(newState == state) {
			return;
		}
		
		switch(state) {
			case MAIN_MENU:
				if(newState == GameState.GAMEPLAY) {
					onStateChangeToGameplay();
					state = newState;
				} else if (newState == GameState.QUIT_GAME) {
					onStateChangeToQuitGame();
					state = newState;
				}
			break;
			case GAMEPLAY:
				if(newState == GameState.MAIN_MENU) {
					onStateChangeToMainMenu();
					state = newState;
				}
			break;
		}
	}
	
	/**
	 * Action to perform when loading Gameplay state.
	 */
	private void onStateChangeToGameplay() {
		canvasManager.loadCanvasFor(GameState.GAMEPLAY);
		inputManager = new GameInputManager(canvasManager.getCurrentLoadedCanvas());
		uiManager = canvasManager.getCurrentLoadedCanvas().getUiManager();
		sceneManager = gameplayManager;
		gameplayManager.run();
	}
	
	/**
	 * Action to perform when loading MainMenu state.
	 */
	private void onStateChangeToMainMenu() {
		canvasManager.loadCanvasFor(GameState.MAIN_MENU);
		inputManager = new GameInputManager(canvasManager.getCurrentLoadedCanvas());
		uiManager = canvasManager.getCurrentLoadedCanvas().getUiManager();
		sceneManager = mainMenuManager;
		mainMenuManager.run();
	}
	
	/**
	 * Action to perform when quiting game.
	 */
	private void onStateChangeToQuitGame() {
		canvasManager.unloadAllFromCanvasContainer();
	}
	
	/**
	 * Handles the queued events.
	 * Dequeue all events and take action on each.
	 */
	private void handleEventsQueue() {
		ArkanoidGwtEvent event = eventsQueue.poll();
		while (event != null) {
			if (event instanceof NewGameButtonClickEvent) {
				changeState(GameState.GAMEPLAY);
			} else if (event instanceof QuitGameButtonClickEvent) {
				changeState(GameState.QUIT_GAME);
			} else if (event instanceof LeaveGameButtonClickEvent) {
				changeState(GameState.MAIN_MENU);
			}
			event = eventsQueue.poll();
		}
	}
	
	/**
	 * Notify all input handlers register in the UiElementsRegister
	 * about the processed input.
	 * @see pl.vezyr.arkanoidgwt.client.manager.input.MouseInputHandler
	 * @see pl.vezyr.arkanoidgwt.client.register.ObjectsRegister
	 */
	private void notifyAllInputHandlers() {
		for (Object elem : ObjectsRegister.getActiveReferences()) {
			// Notify only if mouse state changed.
			// It's prevent state mismatch if player doesn't use mouse
			// and operate only by keyboard
			if (inputManager.hasMouseMoved() ||
				inputManager.isButtonJustPressed(NativeEvent.BUTTON_LEFT) || 
				inputManager.isButtonJustReleased(NativeEvent.BUTTON_LEFT)) {
				
				if (elem instanceof MouseInputHandler) {
					((MouseInputHandler)elem).handleMouseInput(
						inputManager.getMousePosition(),
						inputManager.isMouseButtonPressed(NativeEvent.BUTTON_LEFT),
						inputManager.isButtonJustReleased(NativeEvent.BUTTON_LEFT)
					);
				}
				
			}
			
			if (elem instanceof KeyboardInputHandler) {
				((KeyboardInputHandler)elem).handleKeyboardInput(
					inputManager.getAllPressedKeys(),
					inputManager.getLastReleasedKey()
				);
			}
		}
	}
	
	/**
	 * Returns currently loaded Input manager.
	 * @return InputManager Currently loaded Input manager.
	 */
	public static InputManager getInputManager() {
		return inputManager;
	}
	
	/**
	 * Returns currently loaded UI manager.
	 * @return UiManager Currently loaded UI manager.
	 */
	public static UiManager getUiManager() { 
		return uiManager;
	}
	
	/**
	 * Returns currently loaded Scene manager.
	 * @return SceneManager Currently loaded Scene manager.
	 */
	public static SceneManager getSceneManager() {
		return sceneManager;
	}
	
	/**
	 * Returns currently loaded Canvas manager.
	 * @return CanvasManager Currently loaded Canvas manager.
	 */
	public static CanvasManager getCanvasManager() {
		return canvasManager;
	}
	
	/**
	 * Returns currently loaded Config manager.
	 * @return ConfigManager Loaded Config manager.
	 */
	public static ConfigManager getConfigManager() {
		return configManager;
	}
	
	/**
	 * Returns currently loaded Audio manager.
	 * @return AudioManager Loaded Audio manager.
	 */
	public static AudioManager getAudioManager() {
		return audioManager;
	}
	
	/**
	 * Dispatch event - add event to queue to be 
	 * processed on next frame.
	 * @param event Event to queue.
	 */
	public static void dispatchEvent(ArkanoidGwtEvent event) {
		eventsQueue.add(event);
	}
}
