package pl.vezyr.arkanoidgwt.client.gameobject;

/**
 * An interface that determines the GameObject could be destroyed.
 * Provides methods definitions connected to the health of GameObject.
 * @author vezyr
 *
 */
public interface Destroyable {

	/**
	 * Deals damage to the {@code GameObject}.
	 * @param attackStrength int Number of points of damage to deal.
	 */
	public void takeDamage(int attackStrength);
	/**
	 * Restores specified amount of health points to the {@code GameObject}.
	 * @param healPoints int Number of points to restore.
	 */
	public void heal(int healPoints);
	/**
	 * Returns the current health of {@code GameObject}.
	 * @return int Number of health points of {@code GameObject}.
	 */
	public int currentHealth();
	/**
	 * Returns the information if {@code GameObject} is still considered as alive.
	 * @return boolean True if {@code GameObject} is still alive, false otherwise.
	 */
	public boolean isAlive();
	
}
