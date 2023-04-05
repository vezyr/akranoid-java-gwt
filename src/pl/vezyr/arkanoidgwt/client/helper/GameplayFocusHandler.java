package pl.vezyr.arkanoidgwt.client.helper;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;

import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.GameplayManager;

public class GameplayFocusHandler implements BlurHandler {

	@Override
	public void onBlur(BlurEvent event) {
		if (!(GameManager.getSceneManager() instanceof GameplayManager)) {
			return;
		}
		GameplayManager gameplayManager = (GameplayManager)GameManager.getSceneManager();
		
		gameplayManager.pause();
	}

}
