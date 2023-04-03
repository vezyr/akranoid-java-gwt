package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Base class that represents the Game Object.
 * A Game Object is a any object that is places on "scene" (to be drawn on canvas).
 * All Game Objects should inherit from this base class.
 * It provides position (as Vector2) of the Game Object and Image to use when draw on Canvas.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.helper.Vector2
 *
 */
public abstract class GameObject {

	private Vector2<Integer> position;
	private Image image;
	
	public GameObject(Vector2<Integer> position, Image image) {
		this.position = position;
		this.image = image;
	}
	
	/**
	 * Returns the position of this Game Object on Canvas.
	 * @return Vector2<Integer> Position on Canvas.
	 */
	public Vector2<Integer> getPosition() {
		return position;
	}
	/**
	 * Returns the Image - graphic representation of a Game Object.
	 * @return Image representation of Game Object.
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Called every frame
	 */
	public void update(double deltaTime) {
	}
	
	/**
	 * Draws the current object using passed context.
	 * Override this method if objects should be drawn in any
	 * specific way. 
	 * @param context
	 */
	public void draw(Context2d context) {
		ImageElement imageElement = ImageElement.as(getImage().getElement());
		context.drawImage(imageElement, getPosition().getX(), getPosition().getY());
	}
}
