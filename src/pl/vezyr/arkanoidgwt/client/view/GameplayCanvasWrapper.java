package pl.vezyr.arkanoidgwt.client.view;

import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.gameobject.GameplayBackground;
import pl.vezyr.arkanoidgwt.client.manager.CanvasManager;
import pl.vezyr.arkanoidgwt.client.view.ui.GameplayUiManager;

/**
 * Gameplay canvas wrapper.
 * Construct and hold the canvas for the gameplay scene.
 * @author vezyr
 *
 */
public class GameplayCanvasWrapper extends BaseCanvasWrapper {

	public GameplayCanvasWrapper() {
		super();
		staticObjects.add(new GameplayBackground());
	}

	@Override
	public void load() {
		RootPanel.get(CanvasManager.CANVAS_CONTAINER_ID).add(canvas);
		uiManager = new GameplayUiManager(this);
		canvas.setFocus(true);
	}	
}
