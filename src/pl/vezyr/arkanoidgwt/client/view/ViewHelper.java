package pl.vezyr.arkanoidgwt.client.view;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class ViewHelper {

	public static void drawGameObject(Context2d context, GameObject gameObject) {
		ImageElement imageElement = ImageElement.as(gameObject.getImage().getElement());
		context.drawImage(imageElement, gameObject.getPosition().getX(), gameObject.getPosition().getY());
	}
	
	public static boolean isOverUiElement(Vector2<Integer> point, UiElement element) {
		return point.getX() >= element.getPosition().getX() &&
				point.getX() <= element.getPosition().getX() + element.getImage().getWidth() &&
				point.getY() >= element.getPosition().getY() &&
				point.getY() <= element.getPosition().getY() + element.getImage().getHeight();
	}
}
