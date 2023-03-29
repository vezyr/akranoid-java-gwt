package pl.vezyr.arkanoidgwt.client.gameobjects;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.Collider;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class Paddle extends GameObject implements Collidable {

	private BoxCollider collider;
	
	public Paddle(Vector2<Integer> position, Image image) {
		super(position, image);
		collider = new BoxCollider(this);
	}

	@Override
	public Collider getCollider() {
		return collider;
	}

}
