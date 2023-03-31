package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;

/**
 * Class representing the collider of box (rectangle) shape.
 * Adds an information about the radius of the collider.
 * As a radius the halth of the width of image representing {@code GameObject} is used.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BaseCollider
 */
public class CircleCollider extends BaseCollider {

	private final Integer radius;
	
	public CircleCollider(GameObject gameObject) {
		super(gameObject);
		radius = gameObject.getImage().getWidth() / 2;
	}

	/**
	 * Return the radius of the circle collider.
	 * @return Integer Radius of a collider.
	 */
	public Integer getRadius() {
		return radius;
	}
}
