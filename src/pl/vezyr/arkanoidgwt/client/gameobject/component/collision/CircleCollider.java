package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Class representing the collider of box (rectangle) shape.
 * Adds an information about the radius of the collider.
 * As a radius the halth of the width of image representing {@code GameObject} is used.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BaseCollider
 */
public class CircleCollider extends BaseCollider {

	private final Integer radius;
	
	public CircleCollider(GameObject gameObject, Vector2<Integer> size, int radius) {
		super(gameObject, size);
		this.radius = radius;
	}

	/**
	 * Return the radius of the circle collider.
	 * @return Integer Radius of a collider.
	 */
	public Integer getRadius() {
		return radius;
	}
}
