package pl.vezyr.arkanoidgwt.client.data.config;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Data container class for technical config (ie. screen resolution).
 * @author vezyr
 *
 */
public class TechnicalConfig {

	private final Vector2<Integer> screenResolution;
	
	public TechnicalConfig(Vector2<Integer> screenResolution) {
		this.screenResolution = screenResolution;
	}
	
	public Vector2<Integer> getScreenResolution() {
		return new Vector2<Integer>(screenResolution);
	}
}
