package pl.vezyr.arkanoidgwt.client.view;

import java.util.List;

import com.google.gwt.canvas.client.Canvas;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;

public interface CanvasWrapper {
	public void load();
	public void redraw(List<GameObject> dynamicObjects);
	public Canvas getCanvas();
}
