package pl.vezyr.arkanoidgwt.client.manager;

import java.util.List;

import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;
import pl.vezyr.arkanoidgwt.client.view.GameplayCanvasWrapper;
import pl.vezyr.arkanoidgwt.client.view.MainMenuCanvasWrapper;

/**
 * Manager class to handle different canvas, depends on different game's states.
 * @author vezyr
 *
 */
public class CanvasManager {

	public static final String CANVAS_CONTAINER_ID = "canvas";
	
	private CanvasWrapper gameplayCanvas;
	private CanvasWrapper mainMenuCanvas;
	private CanvasWrapper currentLoadedCanvas;
	
	public CanvasManager() {
		gameplayCanvas = new GameplayCanvasWrapper();
		mainMenuCanvas = new MainMenuCanvasWrapper();
	}
	
	/**
	 * Unloads all canvas and loads new canvas for  
	 * specified game's state.
	 * @param gameState GameState
	 * @see pl.vezyr.arkanoidgwt.client.manager.GameState
	 */
	public void loadCanvasFor(GameState gameState) {
		switch(gameState) {
			case MAIN_MENU:
				unloadAllFromCanvasContainer();
				mainMenuCanvas.load();
				currentLoadedCanvas = mainMenuCanvas;
			break;
			case GAMEPLAY:
				unloadAllFromCanvasContainer();
				gameplayCanvas.load();
				currentLoadedCanvas = gameplayCanvas;
			break;
		}
	}
	
	/**
	 * Unloads all canvas.
	 */
	public void unloadAllFromCanvasContainer() {
		for (int i = (RootPanel.get(CANVAS_CONTAINER_ID).getWidgetCount() - 1); i >= 0; i--) {
			RootPanel.get(CANVAS_CONTAINER_ID).remove(i);
		}
	}
	
	/**
	 * Returns currently loaded canvas.
	 * @return CanvasWrapper Currently loaded canvas.
	 */
	public CanvasWrapper getCurrentLoadedCanvas() {
		return currentLoadedCanvas;
	}
	
	/**
	 * Invokes redraw on currently loaded canvas.
	 * @param dynamicObjects List of dynaminc objects to be drawn on canvas.
	 */
	public void redrawCurrentLoadedCanvas(List<GameObject> dynamicObjects, UiData uiData) {
		currentLoadedCanvas.redraw(dynamicObjects, uiData);
	}
}
