package pl.vezyr.arkanoidgwt.client.gameobject.ui;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * A base class for every UI element.
 * @author vezyr
 *
 */
public abstract class UiElement extends GameObject {

	public UiElement(Vector2<Integer> position, Image image) {
		super(position, image);
	}

}
