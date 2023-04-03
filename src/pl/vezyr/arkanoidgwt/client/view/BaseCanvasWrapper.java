package pl.vezyr.arkanoidgwt.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.exception.CanvasNotSupportedException;
import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.view.ui.UiManager;

public abstract class BaseCanvasWrapper implements CanvasWrapper {

	protected Canvas canvas;
	protected Context2d context;
	protected List<GameObject> staticObjects;
	protected UiManager uiManager;
	
	public BaseCanvasWrapper() {
		canvas = Canvas.createIfSupported();
		if (canvas == null) {
			throw new CanvasNotSupportedException("Can not run - canvas is not supported on you system.");
		}
		Vector2<Integer> screenResolution = GameManager.getConfigManager().getTechnicalConfig().getScreenResolution();
		canvas.setSize(screenResolution.getX() + "px", screenResolution.getY() + "px");
		canvas.setCoordinateSpaceWidth(screenResolution.getX());
		canvas.setCoordinateSpaceHeight(screenResolution.getY());
		context = canvas.getContext2d();
		
		staticObjects = new ArrayList<GameObject>();
	}
	
	@Override
	public void redraw(List<GameObject> dynamicObjects, UiData uiData) {
		context.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
		staticObjects.forEach(staticObject -> staticObject.draw(context));
		if (dynamicObjects != null) {
			dynamicObjects.forEach(dynamicObject -> dynamicObject.draw(context));
		}
		uiManager.updateUi(uiData);
	}
	
	@Override
	public Canvas getCanvas() {
		return canvas;
	}
}
