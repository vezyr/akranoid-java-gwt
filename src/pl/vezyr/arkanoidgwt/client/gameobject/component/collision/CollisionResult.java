package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class CollisionResult {

	private boolean collided;
	private Vector2<Float> otherObjHitPoint;
	private Collidable otherObject;
	
	public CollisionResult(boolean collided, Vector2<Float> otherObjHitPoint, Collidable otherObject) {
		this.collided = collided;
		this.otherObjHitPoint = otherObjHitPoint;
		this.otherObject = otherObject;
	}

	public boolean isCollided() {
		return collided;
	}

	public Vector2<Float> getOtherObjHitPoint() {
		return otherObjHitPoint;
	}
	
	public Collidable getOtherObject() {
		return otherObject;
	}
}
