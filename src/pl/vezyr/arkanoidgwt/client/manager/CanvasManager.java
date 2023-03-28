package pl.vezyr.arkanoidgwt.client.manager;

import java.util.List;

import pl.vezyr.arkanoidgwt.client.gameobjects.GameObject;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;
import pl.vezyr.arkanoidgwt.client.view.GameplayCanvasWrapper;

public class CanvasManager {

	private CanvasWrapper gameplayCanvas;
	private CanvasWrapper currentLoadedCanvas;
	
	public CanvasManager() {
		gameplayCanvas = new GameplayCanvasWrapper();
	}
	
	public void loadCanvasFor(GameState gameState) {
		switch(gameState) {
			case MAIN_MENU:
				
			break;
			case GAMEPLAY:
				gameplayCanvas.load();
				currentLoadedCanvas = gameplayCanvas;
			break;
		}
	}
	
	public CanvasWrapper getCurrentLoadedCanvas() {
		return currentLoadedCanvas;
	}
	
	public void RedrawCurrentLoadedCanvas(List<GameObject> dynamicObjects) {
		currentLoadedCanvas.redraw(dynamicObjects);
	}
}
