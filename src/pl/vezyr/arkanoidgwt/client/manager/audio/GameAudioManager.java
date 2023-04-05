package pl.vezyr.arkanoidgwt.client.manager.audio;

import com.google.gwt.media.client.Audio;

import pl.vezyr.arkanoidgwt.client.AudioPool;
import pl.vezyr.arkanoidgwt.client.event.audio.PlayLoopedSoundEvent;
import pl.vezyr.arkanoidgwt.client.event.audio.PlaySoundAsNewSourceEvent;
import pl.vezyr.arkanoidgwt.client.event.audio.PlaySoundEvent;
import pl.vezyr.arkanoidgwt.client.event.audio.StopSoundEvent;
import pl.vezyr.arkanoidgwt.client.event.base.EventCallable;
import pl.vezyr.arkanoidgwt.client.event.base.EventParam;

public class GameAudioManager implements AudioManager {

	private EventCallable<PlaySoundEvent> playSoundAction;
	private EventCallable<PlaySoundAsNewSourceEvent> playSoundAsNewSourceAction;
	private EventCallable<PlayLoopedSoundEvent> playLoopedSoundAction;
	private EventCallable<StopSoundEvent> stopSoundAction;
	
	public GameAudioManager() {
		playSoundAction = new EventCallable<PlaySoundEvent>() {
			@Override
			public void call(EventParam<PlaySoundEvent> event) {
				play(event.getEvent().getAudioId());
			}
		};
		
		playSoundAsNewSourceAction = new EventCallable<PlaySoundAsNewSourceEvent>() {
			@Override
			public void call(EventParam<PlaySoundAsNewSourceEvent> event) {
				playAsNewSource(event.getEvent().getAudioId());
			}
		};
		
		playLoopedSoundAction = new EventCallable<PlayLoopedSoundEvent>() {
			@Override
			public void call(EventParam<PlayLoopedSoundEvent> event) {
				playInLoop(event.getEvent().getAudioId());
			}
		};
		
		stopSoundAction = new EventCallable<StopSoundEvent>() {
			@Override
			public void call(EventParam<StopSoundEvent> event) {
				stop(event.getEvent().getAudioId());
			}
		};
		
		PlaySoundEvent.addListener(PlaySoundEvent.class, playSoundAction);
		PlaySoundAsNewSourceEvent.addListener(PlaySoundAsNewSourceEvent.class, playSoundAsNewSourceAction);
		PlayLoopedSoundEvent.addListener(PlayLoopedSoundEvent.class, playLoopedSoundAction);
		StopSoundEvent.addListener(StopSoundEvent.class, stopSoundAction);
	}
	
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
