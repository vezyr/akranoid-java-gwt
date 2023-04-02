package pl.vezyr.arkanoidgwt.client.data;

import pl.vezyr.arkanoidgwt.client.manager.GameplayState;

/**
 * Simple class to transport data to gameplay's UI manager.
 * @author vezyr
 *
 */
public class GameplayUiData implements UiData {

	private int playersNumberOfLives;
	private long remainingTime;
	private GameplayState state;
	
	public void updateData(int playersNumberOfLives, long remainingTime, GameplayState state) {
		this.playersNumberOfLives = playersNumberOfLives;
		this.remainingTime = remainingTime;
		this.state = state;
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

	public GameplayState getState() {
		return state;
	}

	public void setState(GameplayState state) {
		this.state = state;
	}
	
}
