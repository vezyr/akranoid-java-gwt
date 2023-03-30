package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;

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
