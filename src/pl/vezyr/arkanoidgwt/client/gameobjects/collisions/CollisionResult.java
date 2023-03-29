package pl.vezyr.arkanoidgwt.client.gameobjects.collisions;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class CollisionResult {

	private boolean collided;
	private Vector2<Float> hitPoint;
	
	public CollisionResult(boolean collided, Vector2<Float> hitPoint) {
		this.collided = collided;
		this.hitPoint = hitPoint;
	}

	public boolean isCollided() {
		return collided;
	}

	public Vector2<Float> getHitPoint() {
		return hitPoint;
	}
}
