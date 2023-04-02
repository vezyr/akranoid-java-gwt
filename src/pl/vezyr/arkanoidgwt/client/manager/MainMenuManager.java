package pl.vezyr.arkanoidgwt.client.manager;

public class MainMenuManager implements SceneManager {

	/**
	 * Entry point of the main manager.
	 *
	 */
	public void run() {
		GameManager.getInputManager().registerHandlers();
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redraw() {
		GameManager.getCanvasManager().getCurrentLoadedCanvas().redraw(null, null);
	}
}
