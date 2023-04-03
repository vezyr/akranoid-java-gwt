package pl.vezyr.arkanoidgwt.client.manager;

/**
 * An interface for all "Scene" managers, like MainMenuManager, GameplayManager etc.
 * @author vezyr
 *
 */
public interface SceneManager {

	/**
	 * Called every frame.
	 * @param deltaTime double Time science last frame (in ms).
	 */
	public void update(double deltaTime);
	
	/**
	 * Render frame.
	 */
	public void redraw();
}
