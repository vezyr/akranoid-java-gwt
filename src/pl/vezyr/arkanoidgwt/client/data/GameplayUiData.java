package pl.vezyr.arkanoidgwt.client.data;

/**
 * Simple class to transport data to gameplay's UI manager.
 * @author vezyr
 *
 */
public class GameplayUiData implements UiData {

	private int playersNumberOfLives;
	
	public void updateData(int playersNumberOfLives) {
		this.playersNumberOfLives = playersNumberOfLives;
	}

	public int getPlayersNumberOfLives() {
		return playersNumberOfLives;
	}

	public void setPlayersNumberOfLives(int playersNumberOfLives) {
		this.playersNumberOfLives = playersNumberOfLives;
	}
	
}
