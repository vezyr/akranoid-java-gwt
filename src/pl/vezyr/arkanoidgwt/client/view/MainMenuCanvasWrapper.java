package pl.vezyr.arkanoidgwt.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobject.GameplayBackground;
import pl.vezyr.arkanoidgwt.client.manager.CanvasManager;
import pl.vezyr.arkanoidgwt.client.view.ui.MainMenuUiManager;

public class MainMenuCanvasWrapper extends BaseCanvasWrapper {

	public MainMenuCanvasWrapper() {
		staticObjects = new ArrayList<GameObject>();
		staticObjects.add(new GameplayBackground());
	}
	
	@Override
	public void load() {
		RootPanel.get(CanvasManager.CANVAS_CONTAINER_ID).add(canvas);
		uiManager = new MainMenuUiManager();
	}
}
