package pl.vezyr.arkanoidgwt.client.gameobject.component;

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Interface of drawable components / game objects
 * @author vezyr
 *
 */
public interface Drawable {

	/**
	 * Draw element on passed context.
	 * @param context
	 */
	public void draw(Context2d context);
}
