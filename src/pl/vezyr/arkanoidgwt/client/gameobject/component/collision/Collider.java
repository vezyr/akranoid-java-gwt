package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public interface Collider {

	public Vector2<Integer> getLeftUpCorner();
	public Vector2<Integer> getSize();
	public Vector2<Float> getHalfOfSize();
	public Vector2<Float> getCenter();
	
}
