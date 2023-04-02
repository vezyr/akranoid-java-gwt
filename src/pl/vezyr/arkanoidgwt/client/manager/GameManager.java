package pl.vezyr.arkanoidgwt.client.manager;

import pl.vezyr.arkanoidgwt.client.manager.input.GameplayInputManager;
import pl.vezyr.arkanoidgwt.client.manager.input.InputManager;
import pl.vezyr.arkanoidgwt.client.view.ui.GameplayUiManager;
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
	
	private static CanvasManager canvasManager;
	private static InputManager inputManager;
	private static UiManager uiManager;
	private static SceneManager sceneManager;
	
	public GameManager() {
		state = GameState.MAIN_MENU;
		canvasManager = new CanvasManager();
		gameplayManager = new GameplayManager(canvasManager);
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
		inputManager = new GameplayInputManager(canvasManager.getCurrentLoadedCanvas());
		uiManager = new GameplayUiManager(canvasManager.getCurrentLoadedCanvas());
		sceneManager = gameplayManager;
		gameplayManager.run();
	}
	
	/**
	 * Action to perform when loading MainMenu state.
	 */
	private void onStateChangeToMainMenu() {
		
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
	 * Return currently loaded Canvas manager.
	 * @return CanvasManager Currently loaded Canvas manager.
	 */
	public static CanvasManager getCanvasManager() {
		return canvasManager;
	}
}
