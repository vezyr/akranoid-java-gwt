package pl.vezyr.arkanoidgwt.client.data.config;

/**
 * Data container class for difficulty level.
 * @author vezyr
 *
 */
public class DifficultyLevel {

	private final int level;
	private final String levelName;
	
	private final int ballSpeed;
	private final long timeLimit;
	
	public DifficultyLevel(int level, String levelName, int ballSpeed, long timeLimit) {
		this.level = level;
		this.levelName = levelName;
		this.ballSpeed = ballSpeed;
		this.timeLimit = timeLimit;
	}
	
	public int getLevel() {
		return level;
	}
	public String getLevelName() {
		return levelName;
	}
	public int getBallSpeed() {
		return ballSpeed;
	}
	public long getTimeLimit() {
		return timeLimit;
	}	
}
