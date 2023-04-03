package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.canvas.dom.client.Context2d;

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
	private Vector2<Integer> size;
	private boolean active;
	
	public GameObject(Vector2<Integer> position, Vector2<Integer> size) {
		this.position = new Vector2<Integer>(position);
		this.size = new Vector2<Integer>(size);
		active = true;
	}
	
	/**
	 * Returns the position of this Game Object on Canvas.
	 * @return Vector2<Integer> Position on Canvas.
	 */
	public Vector2<Integer> getPosition() {
		return position;
	}
	
	/**
	 * Returns the size of Game Object.
	 * @return Vector2<Integer> Size of Game Object.
	 */
	public Vector2<Integer> getSize() {
		return size;
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
		
	}

	/**
	 * Returns if this game object is active.
	 * Inactive game object won't be drawn event if
	 * draw() method is called.
	 * @return boolean True if game object is active, false otherwise.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Set active state of game object.
	 * @param active 
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
