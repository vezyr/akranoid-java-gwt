package pl.vezyr.arkanoidgwt.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.exception.CanvasNotSupportedException;
import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobject.GameplayBackground;
import pl.vezyr.arkanoidgwt.client.manager.CanvasManager;
import pl.vezyr.arkanoidgwt.client.view.ui.GameplayUiManager;

/**
 * Gameplay canvas wrapper.
 * Construct and hold the canvas for the gameplay scene.
 * @author vezyr
 *
 */
public class GameplayCanvasWrapper implements CanvasWrapper {

	private Canvas canvas;
	private Context2d context;
	private GameplayUiManager uiManager;
	private List<GameObject> staticObjects;
	
	public GameplayCanvasWrapper() {
		canvas = Canvas.createIfSupported();
		if (canvas == null) {
			throw new CanvasNotSupportedException("Can not run - canvas is not supported on you system.");
		}
		canvas.setSize("1280px", "800px");
		canvas.setCoordinateSpaceWidth(1280);
		canvas.setCoordinateSpaceHeight(800);
		context = canvas.getContext2d();
		
		staticObjects = new ArrayList<GameObject>();
		staticObjects.add(new GameplayBackground());
	}

	@Override
	public void load() {
		RootPanel.get(CanvasManager.CANVAS_CONTAINER_ID).add(canvas);
		uiManager = new GameplayUiManager(this);
	}

	@Override
	public void redraw(List<GameObject> dynamicObjects, UiData uiData) {
		context.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
		staticObjects.forEach(staticObject -> drawGameObject(staticObject));
		dynamicObjects.forEach(dynamicObject -> drawGameObject(dynamicObject));
		uiManager.updateUi(uiData);
	}

	@Override
	public Canvas getCanvas() {
		return canvas;
	}
	
	private void drawGameObject(GameObject gameObject) {
		ImageElement imageElement = ImageElement.as(gameObject.getImage().getElement());
		context.drawImage(imageElement, gameObject.getPosition().getX(), gameObject.getPosition().getY());
	}
}
