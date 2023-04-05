package pl.vezyr.arkanoidgwt.client.helper;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;

import pl.vezyr.arkanoidgwt.client.AudioPool;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

public class GameFocusHandler implements FocusHandler {

	@Override
	public void onFocus(FocusEvent event) {
		GameManager.getAudioManager().playInLoop(AudioPool.AUDIO_MUSIC);		
	}

}
