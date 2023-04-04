package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Class representing the collider of box (rectangle) shape.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BaseCollider
 */
public class BoxCollider extends BaseCollider {

	public BoxCollider(GameObject gameObject, Vector2<Integer> size) {
		super(gameObject, size);
	}
	
}
