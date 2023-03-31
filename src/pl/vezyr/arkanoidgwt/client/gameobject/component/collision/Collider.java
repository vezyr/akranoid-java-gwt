package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * The interface of collidert that can be attached to {@code GameObject}.
 * @author vezyr
 *
 */
public interface Collider {

	/**
	 * Returns the coordinates of upper left corner of collider.
	 * @return Vector2<Integer> coordinates of the corner.
	 * @see pl.vezyr.arkanoidgwt.client.helper.Vector2
	 */
	public Vector2<Integer> getLeftUpCorner();
	/**
	 * Return the size of the collider.
	 * @return Vector2<Integer> Size of the collider in pixels.
	 * @see pl.vezyr.arkanoidgwt.client.helper.Vector2
	 */
	public Vector2<Integer> getSize();
	/**
	 * Return the half of the size of the collider.
	 * @return Vector2<Integer> Size of the collider in pixels.
	 * @see pl.vezyr.arkanoidgwt.client.helper.Vector2
	 */
	public Vector2<Float> getHalfOfSize();
	/**
	 * Return the center point of the collider.
	 * @return Vector2<Integer> Center point's coordinates.
	 * @see pl.vezyr.arkanoidgwt.client.helper.Vector2
	 */
	public Vector2<Float> getCenter();
	
}
