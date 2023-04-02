package pl.vezyr.arkanoidgwt.client.manager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.event.shared.HandlerManager;

import pl.vezyr.arkanoidgwt.client.event.ArkanoidGwtEvent;
import pl.vezyr.arkanoidgwt.client.event.NewGameButtonClickEvent;
import pl.vezyr.arkanoidgwt.client.manager.input.GameInputManager;
import pl.vezyr.arkanoidgwt.client.manager.input.InputManager;
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
	
	private double lastFrameTimestamp = 0;
	
	private static final Queue<ArkanoidGwtEvent> eventsQueue = new LinkedList<ArkanoidGwtEvent>();
	
	private static final Logger logger = Logger.getLogger(GameManager.class.getName());
	
	public GameManager() {
		configManager = new SimpleInMemeoryConfigManager();
		configManager.load();
		
		canvasManager = new CanvasManager();
		gameplayManager = new GameplayManager(canvasManager);
		mainMenuManager = new MainMenuManager();
		
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
				handleEventsQueue();				
				sceneManager.update(deltaTime);
				sceneManager.redraw();
				
				lastFrameTimestamp = timestamp;
				AnimationScheduler.get().requestAnimationFrame(this);
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
		uiManager = new GameplayUiManager(canvasManager.getCurrentLoadedCanvas());
		sceneManager = gameplayManager;
		gameplayManager.run();
	}
	
	/**
	 * Action to perform when loading MainMenu state.
	 */
	private void onStateChangeToMainMenu() {
		canvasManager.loadCanvasFor(GameState.MAIN_MENU);
		inputManager = new GameInputManager(canvasManager.getCurrentLoadedCanvas());
		uiManager = new MainMenuUiManager();
		sceneManager = mainMenuManager;
		mainMenuManager.run();
	}
	
	private void handleEventsQueue() {
		ArkanoidGwtEvent event = eventsQueue.poll();
		while (event != null) {
			if (event instanceof NewGameButtonClickEvent) {
				changeState(GameState.GAMEPLAY);
			}
			event = eventsQueue.poll();
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
	
	public static void dispatchEvent(ArkanoidGwtEvent event) {
		eventsQueue.add(event);
	}
}
