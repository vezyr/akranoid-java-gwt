package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Base class of all blocks.
 * Inherits from base GameObject.
 * Implements Destroyable and adds HealthCompoment to handle the health points of the block.
 * Implements Collidable so the collisions between ball and block could be detected and handled.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameObject
 * @see pl.vezyr.arkanoidgwt.client.gameobject.Destroyable
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable
 */
public class BaseBlock extends GameObject implements Destroyable, Collidable {

	private BoxCollider collider;
	private HealthComponent health;
	
	public BaseBlock(Vector2<Integer> position, Image image, int initialHealth) {
		super(position, image);
		health = new HealthComponent(this, initialHealth);
		collider = new BoxCollider(this);
	}

	@Override
	public Collider getCollider() {
		return collider;
	}

	@Override
	public void takeDamage(int attackStrength) {
		health.takeDamage(attackStrength);		
		
	}

	@Override
	public void heal(int healPoints) {
		health.heal(healPoints);
	}
	
	@Override
	public int currentHealth() {
		return health.getHealth();
	}

	@Override
	public boolean isAlive() {
		return health.isAlive();
	}
	
	@Override
	public void handleCollision(CollisionResult collision) {
	}
}
