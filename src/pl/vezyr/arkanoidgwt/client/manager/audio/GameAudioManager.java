package pl.vezyr.arkanoidgwt.client.manager.audio;

import com.google.gwt.media.client.Audio;

import pl.vezyr.arkanoidgwt.client.AudioPool;

public class GameAudioManager implements AudioManager {

	@Override
	public void play(String audioId) {
		if (AudioPool.getAudio(audioId) != null) {
			stop(audioId);
			AudioPool.getAudio(audioId).setLoop(false);
			AudioPool.getAudio(audioId).play();
		}
	}
	
	@Override
	public void playAsNewSource(String audioId) {
		if (AudioPool.getAudio(audioId) != null) {
			Audio audio = Audio.createIfSupported();
			audio.setSrc(AudioPool.getAudio(audioId).getSrc());
			audio.play();
		}
	}
	
	@Override
	public void playInLoop(String audioId) {
		if (AudioPool.getAudio(audioId) != null) {
			AudioPool.getAudio(audioId).setLoop(true);
			AudioPool.getAudio(audioId).play();
		}
	}

	@Override
	public void stop(String audioId) {
		if (AudioPool.getAudio(audioId) != null) {
			AudioPool.getAudio(audioId).pause();
			AudioPool.getAudio(audioId).setCurrentTime(0);
		}
	}

}
