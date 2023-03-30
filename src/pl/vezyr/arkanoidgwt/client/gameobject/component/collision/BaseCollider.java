package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobject.component.BaseComponent;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

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
