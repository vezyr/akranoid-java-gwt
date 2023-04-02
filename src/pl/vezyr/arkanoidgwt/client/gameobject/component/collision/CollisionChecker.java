package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

/**
 * The interface of the collision checker.
 * 
 * The collision checker should handle collision checks between selected objects.
 * The responsibility of the checker is to perform check operation between 
 * two given {@code GameObjects} which implements {@code Collidable} interface.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameObject
 *
 */
public interface CollisionChecker {
	
	/**
	 * Choose and check collisions between selected objects.
	 * Usually implementation of this method 
	 * chooses the objects to check and then invokes
	 * {@code checkCollision} on each pair.
	 */
	public void checkCollisions();
	
	/**
	 * Performs check the one objects collides with other.
	 * @param obj1 GameObject First object.
	 * @param obj2 GameObject Second object.
	 * @return CollisionResult The result of the check.
	 */
	public CollisionResult checkCollision(Collidable obj1, Collidable obj2);
	
}
