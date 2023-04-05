package pl.vezyr.arkanoidgwt.client.event.audio;

import pl.vezyr.arkanoidgwt.client.event.base.BaseEvent;

public class StopSoundEvent extends BaseEvent {

	private final String audioId;
	
	public StopSoundEvent(String audioId) {
		this.audioId = audioId;
	}
	
	public String getAudioId() {
		return audioId;
	}
}
