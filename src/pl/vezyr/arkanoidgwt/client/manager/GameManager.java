package pl.vezyr.arkanoidgwt.client.manager;

public class GameManager {

	private GameState state = null;
	private CanvasManager canvasManager;
	private GameplayManager gameplayManager;
	
	public GameManager() {
		state = GameState.MAIN_MENU;
		canvasManager = new CanvasManager();
		gameplayManager = new GameplayManager(canvasManager);
	}
	
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
	
	private void onStateChangeToGameplay() {
		canvasManager.loadCanvasFor(GameState.GAMEPLAY);
		gameplayManager.run();
	}
	
	private void onStateChangeToMainMenu() {
		
	}
}
