package pl.vezyr.arkanoidgwt.client.manager;

import java.util.List;

import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;
import pl.vezyr.arkanoidgwt.client.view.GameplayCanvasWrapper;

public class CanvasManager {

	public static final String CANVAS_CONTAINER_ID = "canvas";
	
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
				unloadAllFromCanvasContainer();
				gameplayCanvas.load();
				currentLoadedCanvas = gameplayCanvas;
			break;
		}
	}
	
	public void unloadAllFromCanvasContainer() {
		for (int i = (RootPanel.get(CANVAS_CONTAINER_ID).getWidgetCount() - 1); i >= 0; i--) {
			RootPanel.get(CANVAS_CONTAINER_ID).remove(i);
		}
	}
	
	public CanvasWrapper getCurrentLoadedCanvas() {
		return currentLoadedCanvas;
	}
	
	public void redrawCurrentLoadedCanvas(List<GameObject> dynamicObjects) {
		currentLoadedCanvas.redraw(dynamicObjects);
	}
}
