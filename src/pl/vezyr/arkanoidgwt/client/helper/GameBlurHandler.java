package pl.vezyr.arkanoidgwt.client.helper;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;

import pl.vezyr.arkanoidgwt.client.AudioPool;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

public class GameBlurHandler implements BlurHandler {

	@Override
	public void onBlur(BlurEvent event) {
		GameManager.getAudioManager().pause(AudioPool.AUDIO_MUSIC);
	}

}
