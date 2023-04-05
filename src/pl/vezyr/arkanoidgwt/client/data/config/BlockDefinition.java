package pl.vezyr.arkanoidgwt.client.data.config;

/**
 * Data container for block definition.
 * Contains information about block's healh points.
 * @author vezyr
 *
 */
public class BlockDefinition {

	private final int healthPoints;
	
	public BlockDefinition(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
}
