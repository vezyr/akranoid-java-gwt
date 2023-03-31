package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobject.component.BaseComponent;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Base class of all colliders. 
 * Each collider is a {@code GameObject}'s component, so this class exteds {@code BaseComponent}.
 * 
 * Every collider has a information about upper left corner of the collider (position of 
 * upper left corner of {@code GameObject} on Canvas), the size of the collider, the half of the size of the collider and 
 * the center point (position on Canvas).
 * 
 * Collider spreads to the whole {@code GameObject}. All parameters are calculated in the class constructor.
 * As a upper left corner of the collider the upper left corner of {@code GameObject} is used.
 * As a size of the collider the size of the image representing {@code GameObject} is used.
 * 
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.BaseComponent
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider
 */
public class BaseCollider extends BaseComponent implements Collider {

	private final Vector2<Integer> leftUpCorner;
	private final Vector2<Integer> size;
	private final Vector2<Float> halfOfSize;
	private Vector2<Float> center;
	
	public BaseCollider(GameObject gameObject) {
		super(gameObject);
		leftUpCorner = gameObject.getPosition();
		size = new Vector2<Integer>(gameObject.getImage().getWidth(), gameObject.getImage().getHeight());
		halfOfSize = new Vector2<Float>((float)size.getX() / 2, (float)size.getY()/2);
	}
	
	/**
	 * Calculates the center of the collider.
	 */
	private void recalculateCenter() {
		center = new Vector2<Float>(
			leftUpCorner.getX() + halfOfSize.getX(),
			leftUpCorner.getY() + halfOfSize.getY()
		);
	}
	
	@Override
	public Vector2<Integer> getLeftUpCorner() {
		return leftUpCorner;
	}
	
	@Override
	public Vector2<Integer> getSize() {
		return size;
	}
	
	@Override
	public Vector2<Float> getHalfOfSize() {
		return halfOfSize;
	}
	
	@Override
	public Vector2<Float> getCenter() {
		recalculateCenter();
		return center;
	}
}
