package pl.vezyr.arkanoidgwt.client.view;

import java.util.List;

import com.google.gwt.canvas.client.Canvas;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;

/**
 * An interface of the wrapper around GWT's Canvas.
 * @author vezyr
 *
 */
public interface CanvasWrapper {
	/**
	 * Adds the canvas to the DOM node. 
	 */
	public void load();
	/**
	 * Redraw the canvas.
	 * @param dynamicObjects List of dynamic objects to draw on canvas.
	 */
	public void redraw(List<GameObject> dynamicObjects);
	/**
	 * Returns the wrapped canvas.
	 * @return Canvas
	 */
	public Canvas getCanvas();
}
