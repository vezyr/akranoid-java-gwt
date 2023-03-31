package pl.vezyr.arkanoidgwt.client.manager;

/**
 * Main manger of the game.
 * Handles different states of the game,
 * ie. main menu is loaded, gameplay is active etc.
 * @author vezyr
 *
 */
public class GameManager {

	private GameState state = null;
	private CanvasManager canvasManager;
	private GameplayManager gameplayManager;
	
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
		gameplayManager.run();
	}
	
	/**
	 * Action to perform when loading MainMenu state.
	 */
	private void onStateChangeToMainMenu() {
		
	}
}
