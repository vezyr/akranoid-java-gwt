package pl.vezyr.arkanoidgwt.client.gameobject.ui;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.register.Registrable;

/**
 * A base class for every UI element.
 * @author vezyr
 *
 */
public abstract class UiElement extends GameObject {

	public UiElement(Vector2<Integer> position, Vector2<Integer> size) {
		super(position, size);
		if (this instanceof Registrable) {
			((Registrable)this).register();
		}
	}

}
