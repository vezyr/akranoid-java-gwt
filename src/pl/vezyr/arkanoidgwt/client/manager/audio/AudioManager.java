package pl.vezyr.arkanoidgwt.client.manager.audio;

/**
 * Interface for audio manager.
 * Provides basic methods to play and stop sounds.
 * @author vezyr
 *
 */
public interface AudioManager {

	/**
	 * Play selected audio once.
	 * This method interrupts previous play if necessary.
	 * @param audioId Audio id.
	 * @see pl.vezyr.arkanoidgwt.client.AudioPool
	 */
	public void play(String audioId);
	
	/**
	 * Plays selected audio as new source.
	 * It provide possibility to play the same
	 * sound multiple times on the same time.
	 * @param audioId
	 */
	public void playAsNewSource(String audioId);
	
	/**
	 * Plays audio in loop.
	 * If audio is already playing, it won't be restarted,
	 * so it's safe to invoke this method multiple times.
	 * @param audioId
	 */
	public void playInLoop(String audioId);
	
	/**
	 * Stops selected audio.
	 * @param audioId Audio id.
	 * @see pl.vezyr.arkanoidgwt.client.AudioPool
	 */
	public void stop(String audioId);
	
	/**
	 * Stops all sounds.
	 */
	public void stopAll();
	
	/**
	 * Pause selected sound.
	 * @param audioId Audio id.
	 */
	public void pause(String audioId);
}
