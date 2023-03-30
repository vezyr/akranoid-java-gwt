package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class BaseBlock extends GameObject implements Destroyable, Collidable {

	private BoxCollider collider;
	private HealthComponent health;
	
	public BaseBlock(Vector2<Integer> position, Image image, int initialHealth) {
		super(position, image);
		collider = new BoxCollider(this);
		health = new HealthComponent(this, initialHealth);
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
