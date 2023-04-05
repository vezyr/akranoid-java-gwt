package pl.vezyr.arkanoidgwt.client.event.audio;

import pl.vezyr.arkanoidgwt.client.event.base.BaseEvent;

public class PlaySoundEvent extends BaseEvent {
	
	private final String audioId;
	
	public PlaySoundEvent(String audioId) {
		this.audioId = audioId;
	}
	
	public String getAudioId() {
		return audioId;
	}
}
