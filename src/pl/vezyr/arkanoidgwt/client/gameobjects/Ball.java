package pl.vezyr.arkanoidgwt.client.gameobjects;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.CircleCollider;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.Collider;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class Ball extends GameObject implements Collidable {

	private Vector2<Float> direction;
	private CircleCollider collider;
	
	public Ball(Vector2<Integer> position, Image image) {
		super(position, image);
		direction = new Vector2<Float>();
		collider = new CircleCollider(this);
	}

	public Vector2<Float> getDirection() {
		return direction;
	}

	@Override
	public Collider getCollider() {
		return collider;
	}
}
