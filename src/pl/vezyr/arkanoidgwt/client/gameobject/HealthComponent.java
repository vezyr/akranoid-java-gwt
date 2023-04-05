package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.gameobject.component.BaseComponent;

/**
 * A component to handle all operations connected with "health" of GameObject.
 * Inherits from BaseComponent.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameObject
 * @see pl.vezyr.arkanoidgwt.client.gameobject.compoment.BaseComponent 
 */
public class HealthComponent extends BaseComponent {

	private int initialHealth;
	private int health;
	
	public HealthComponent(GameObject gameObject, int baseHealth) {
		super(gameObject);
		this.initialHealth = baseHealth;
		this.health = this.initialHealth;
	}
	
	/**
	 * Returns the current health.
	 * @return int Number of health points.
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns current health, normalized.
	 * The returned value will be from 0 to 1.
	 * @return float Normalized health.
	 */
	public float getHealthNormalized() {
		return (float)health / (float)initialHealth;
	}
	
	/**
	 * Deals damage to {@code GameObject} which contains this {@code HealthComponent}.
	 * Simply subtract {@code attackStrength} from {@code health}.
	 * @param attackStrength int Attack strength - number of points to subtract from health.
	 */
	public void takeDamage(int attackStrength) {
		health -= attackStrength;
	}
	
	/**
	 * Heals the {@code GameObject} which contains this {@code HealthComponent}.
	 * Simply adds {@code healPoints} to {@code health}.
	 * @param healPoints int Number of points to add to health.
	 */
	public void heal(int healPoints) {
		health += healPoints;
	}
	
	/**
	 * Checks if {@code GameObject} is still alive, which means that health is still > 0.
	 * @return boolean True if health is still > 0, false otherwise.
	 */
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Sets the number of health points, replacing the previous value.
	 * @param health int Health state (number of points) to set.
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Returns the initial health.
	 * @return int Initial health.
	 */
	public int getInitialHealth() {
		return initialHealth;
	}
}
