package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

/**
 * An interface that determines the {@code GameObject} could collide with other
 * {@code GameObject} which implements {@code Collidable} interface.
 * Provides methods' definitions to retrive the collider attached to {@code GameObject}
 * and a method to handle some specific action on collision.
 * @author vezyr
 *
 */
public interface Collidable {

	/**
	 * Returns the collider attached to the {@code GameObject}.
	 * @return Collider Collider attached to {@code GameObject}
	 */
	public Collider getCollider();
	
	/**
	 * Handle any specific action to take on a collision.
	 * @param collision CollisionResult The result of detected collision between objects.
	 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult
	 */
	public void handleCollision(CollisionResult collision);
	
}
