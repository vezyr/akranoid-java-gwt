package pl.vezyr.arkanoidgwt.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.media.client.Audio;

/**
 * Audios' pool that can be used anywhere in the code. 
 * @author vezyr
 *
 */
public class AudioPool {

	private static Map<String, Audio> audios = new HashMap<String, Audio>();
	
	public static final String AUDIO_BUTTON_SELECT = "button_select";
	public static final String AUDIO_BUTTON_CLICKED = "button_clicked";
	
	public static final String AUDIO_GAMEPLAY_BLOCK_HIT = "gameplay_block_hit";
	public static final String AUDIO_GAMEPLAY_LIVE_LOST = "gameplay_live_lost";
	public static final String AUDIO_GAMEPLAY_GAME_WON = "gameplay_game_won";
	public static final String AUDIO_GAMEPLAY_GAME_LOST = "gameplay_game_lost";
	
	public static final String AUDIO_GAMEPLAY_PADDLE_STAYING = "gameplay_paddle_staying";
	public static final String AUDIO_GAMEPLAY_PADDLE_MOVING = "gameplay_paddle_moving";
	
	public static void init() {
		if (audios.size() > 0) {
			// Already initialized.
			return;
		}
		
		Audio audio;
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/button_select.ogg");
		audios.put(AUDIO_BUTTON_SELECT, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/button_clicked.ogg");
		audios.put(AUDIO_BUTTON_CLICKED, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/gameplay_block_hit.ogg");
		audios.put(AUDIO_GAMEPLAY_BLOCK_HIT, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/gameplay_live_lost.ogg");
		audios.put(AUDIO_GAMEPLAY_LIVE_LOST, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/gameplay_game_won.ogg");
		audios.put(AUDIO_GAMEPLAY_GAME_WON, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/gameplay_game_lost.ogg");
		audios.put(AUDIO_GAMEPLAY_GAME_LOST, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/gameplay_paddle_staying.ogg");
		audios.put(AUDIO_GAMEPLAY_PADDLE_STAYING, audio);
		
		audio = Audio.createIfSupported();
		audio.setSrc("audios/gameplay_paddle_moving.ogg");
		audios.put(AUDIO_GAMEPLAY_PADDLE_MOVING, audio);
	}
	
	/**
	 * Gets the Audio object base on audio id.
	 * All available ids are definded as public static fields of this class.
	 * @param id Id of the audio to return.
	 * @return Audio object attached to given id or null if there is no Audio attached to given id.
	 */
	public static Audio getAudio(String id) {
		return audios.get(id);
	}
}
