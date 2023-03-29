package pl.vezyr.arkanoidgwt.client.gameobjects.collisions;

import pl.vezyr.arkanoidgwt.client.gameobjects.GameObject;

public class CircleCollider extends BaseCollider {

	private final Integer radius;
	
	public CircleCollider(GameObject gameObject) {
		super(gameObject);
		radius = gameObject.getImage().getWidth();
	}

	public Integer getRadius() {
		return radius;
	}
}
