package pl.vezyr.arkanoidgwt.client.view;

import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.RootPanel;

import pl.vezyr.arkanoidgwt.client.exception.CanvasNotSupportedException;
import pl.vezyr.arkanoidgwt.client.gameobjects.GameObject;

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
		try {
			RootPanel.get("canvas").remove(0);
		} catch (IndexOutOfBoundsException ex) {
			// do nothing - it's the first canvas (there is no previous one
			// so we do not need to remove previous).
		}
		RootPanel.get("canvas").add(canvas);
	}

	@Override
	public void redraw(List<GameObject> dynamicObjects) {
		context.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
		dynamicObjects.forEach(dynamicObject -> {
			context.drawImage(ImageElement.as(dynamicObject.getImage().getElement()), dynamicObject.getPosition().getX(), dynamicObject.getPosition().getY());
		});
	}

	@Override
	public Canvas getCanvas() {
		return canvas;
	}
	
	
}
