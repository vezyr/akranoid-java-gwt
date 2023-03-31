package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Class that represent the result of the check of collision between to objects.
 * 
 * Contains the result of the collision check, reference to the second {@code GameObject} 
 * that was passed to the {@code handleCollision} method of the {@code CollisionChecker} 
 * and the hit point on that second {@code GameObject}.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameOBject
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionChecker
 */
public class CollisionResult {

	private boolean collided;
	private Vector2<Float> otherObjHitPoint;
	private Collidable otherObject;
	
	public CollisionResult(boolean collided, Vector2<Float> otherObjHitPoint, Collidable otherObject) {
		this.collided = collided;
		this.otherObjHitPoint = otherObjHitPoint;
		this.otherObject = otherObject;
	}

	/**
	 * Return the result of the collision check.
	 * @return boolean True if {@code GameObject}s collided with each other, false otherwise.
	 */
	public boolean isCollided() {
		return collided;
	}

	/**
	 * Returns the coordinates of hit point on the second object.
	 * The hit point is in the "world space" which means the returned 
	 * coordinates are the coordinates of the point on Canvas.
	 * @return Vector2<Float> Coordinates of the hit point.
	 */
	public Vector2<Float> getOtherObjHitPoint() {
		return otherObjHitPoint;
	}
	
	/**
	 * Returns the reference to the second {@code GameObject} of the collision.
	 * @return Collidable The second {@code GameObject}.
	 */
	public Collidable getOtherObject() {
		return otherObject;
	}
}
