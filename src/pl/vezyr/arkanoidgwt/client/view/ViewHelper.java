package pl.vezyr.arkanoidgwt.client.view;

import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class ViewHelper {	
	public static boolean isOverUiElement(Vector2<Integer> point, UiElement element) {
		return point.getX() >= element.getPosition().getX() &&
				point.getX() <= element.getPosition().getX() + element.getSize().getX() &&
				point.getY() >= element.getPosition().getY() &&
				point.getY() <= element.getPosition().getY() + element.getSize().getY();
	}
}
