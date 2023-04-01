package pl.vezyr.arkanoidgwt.client.data;

/**
 * Simple class to transport data to gameplay's UI manager.
 * @author vezyr
 *
 */
public class GameplayUiData implements UiData {

	private int playersNumberOfLives;
	private long remainingTime;
	
	public void updateData(int playersNumberOfLives, long remainingTime) {
		this.playersNumberOfLives = playersNumberOfLives;
		this.remainingTime = remainingTime;
	}

	public int getPlayersNumberOfLives() {
		return playersNumberOfLives;
	}

	public void setPlayersNumberOfLives(int playersNumberOfLives) {
		this.playersNumberOfLives = playersNumberOfLives;
	}

	public long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(long remainingTime) {
		this.remainingTime = remainingTime;
	}
	
}
