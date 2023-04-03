package pl.vezyr.arkanoidgwt.client.register;

/**
 * Simple interface to mark object to be registered.
 * A little workaround because GWT doesn't support full reflactions
 * so the use of custom annotations is limited.
 * @author vezyr
 *
 */
public interface Registrable {

	/** 
	 * Register this object.
	 */
	public void register();
	
	/**
	 * Unregister this object.
	 */
	public void unregister();
}
