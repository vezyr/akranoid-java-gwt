package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.gameobject.component.BaseComponent;

public class HealthComponent extends BaseComponent {

	private int health;
	
	public HealthComponent(GameObject gameObject, int baseHealth) {
		super(gameObject);
		this.health = baseHealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void takeDamage(int attackStrength) {
		health -= attackStrength;
	}
	
	public void heal(int healPoints) {
		health += healPoints;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
}
