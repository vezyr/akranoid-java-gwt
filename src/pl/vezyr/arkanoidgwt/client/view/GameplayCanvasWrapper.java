package pl.vezyr.arkanoidgwt.client.view;

import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.exception.CanvasNotSupportedException;
import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.manager.CanvasManager;

/**
 * Gameplay canvas wrapper.
 * Construct and hold the canvas for the gameplay scene.
 * @author vezyr
 *
 */
public class GameplayCanvasWrapper implements CanvasWrapper {

	private Canvas canvas;
	private Context2d context;
	
	public GameplayCanvasWrapper() {
		canvas = Canvas.createIfSupported();
		if (canvas == null) {
			throw new CanvasNotSupportedException("Can not run - canvas is not supported on you system.");
		}
		canvas.setSize("1280px", "800px");
		canvas.setCoordinateSpaceWidth(1280);
		canvas.setCoordinateSpaceHeight(800);
		context = canvas.getContext2d();
	}

	@Override
	public void load() {
		RootPanel.get(CanvasManager.CANVAS_CONTAINER_ID).add(canvas);
	}

	@Override
	public void redraw(List<GameObject> dynamicObjects) {
		context.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
		dynamicObjects.forEach(dynamicObject -> {
			ImageElement imageElement = ImageElement.as(dynamicObject.getImage().getElement());
			context.drawImage(imageElement, dynamicObject.getPosition().getX(), dynamicObject.getPosition().getY());
		});
	}

	@Override
	public Canvas getCanvas() {
		return canvas;
	}
}
