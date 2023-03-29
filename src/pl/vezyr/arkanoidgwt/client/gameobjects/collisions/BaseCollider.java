package pl.vezyr.arkanoidgwt.client.gameobjects.collisions;

import pl.vezyr.arkanoidgwt.client.gameobjects.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class BaseCollider implements Collider {

	private final Vector2<Integer> leftUpCorner;
	private final Vector2<Integer> size;
	private final Vector2<Float> halfOfSize;
	private Vector2<Float> center;
	
	public BaseCollider(GameObject gameObject) {
		leftUpCorner = gameObject.getPosition();
		size = new Vector2<Integer>(gameObject.getImage().getWidth(), gameObject.getImage().getHeight());
		halfOfSize = new Vector2<Float>((float)size.getX() / 2, (float)size.getY()/2);
	}
	
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
