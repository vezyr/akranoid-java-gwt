package pl.vezyr.arkanoidgwt.client.data;

/**
 * Handles player's data like current score, number of lives etc during gameplay.
 * @author vezyr
 *
 */
public class PlayerData {

	private int numberOfLives;
	private int score;
	
	public PlayerData() {
		numberOfLives = 3;
		score = 0;
	}
	
	/**
	 * Handles the lost of live.
	 * @return boolean True if number of lives is still > 0, false otherwise.
	 */
	public boolean liveLost() {
		numberOfLives--;
		return numberOfLives > 0;
	}
	
	/**
	 * Checks if player still has lives.
	 * @return boolean True if number of lives > 0, false otherwise.
	 */
	public boolean hasLives() {
		return numberOfLives > 0;
	}
	
	/**
	 * Returns number of player's lives.
	 * @return int Number of lives.
	 */
	public int getNumberOfLives() {
		return numberOfLives;
	}
}
