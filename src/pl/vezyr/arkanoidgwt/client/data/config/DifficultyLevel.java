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
	private final int paddleSeepd;
	private final long timeLimit;
	
	public DifficultyLevel(int level, String levelName, int ballSpeed, int paddleSpeed, long timeLimit) {
		this.level = level;
		this.levelName = levelName;
		this.ballSpeed = ballSpeed;
		this.paddleSeepd = paddleSpeed;
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
	public int getPaddleSpeed() {
		return paddleSeepd;
	}
}
