package pl.vezyr.arkanoidgwt.client.manager;

import java.util.logging.Logger;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import pl.vezyr.arkanoidgwt.client.AudioPool;
import pl.vezyr.arkanoidgwt.client.data.MainMenuUiData;

/**
 * Main menu manager.
 * @author vezyr
 *
 */
public class MainMenuManager implements SceneManager {

	private boolean loaded = false;
	private MainMenuUiData uiData;
	
	/**
	 * Entry point of the main manager.
	 *
	 */
	public void run() {
		GameManager.getInputManager().registerHandlers();
		uiData = new MainMenuUiData(loaded);
		if (!loaded) {
			GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (!loaded && event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
						loaded = true;
						uiData.setLoaded(loaded);
						GameManager.getAudioManager().playInLoop(AudioPool.AUDIO_MUSIC);
					}
				}
			});
		}
	}

	@Override
	public void update(double deltaTime) {
	}

	@Override
	public void redraw() {
		GameManager.getCanvasManager().getCurrentLoadedCanvas().redraw(null, uiData);
	}
}
