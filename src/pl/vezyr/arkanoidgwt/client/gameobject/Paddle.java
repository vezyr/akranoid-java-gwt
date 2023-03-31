package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * GameObject that represents Paddle.
 * Inherits from base GameObject.
 * Implements Collidable so the collisions between ball and paddle could be detected and handled.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameObject
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable
 */
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

	@Override
	public void handleCollision(CollisionResult collision) {	
	}
}
