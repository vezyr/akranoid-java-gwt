package pl.vezyr.arkanoidgwt.client.gameobject.component;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Image Component that allow to set position on Game Object.
 * Position is set relatively to Game Object. 
 * @author vezyr
 *
 */
public class PositionedImageComponent extends ImageComponent {

	private Vector2<Integer> localPosition;
	
	public PositionedImageComponent(GameObject gameObject, Image image, Vector2<Integer> position) {
		super(gameObject, image);
		setLocalPosition(position);
	}

	@Override
	public void draw(Context2d context) {
		ImageElement imageElement = ImageElement.as(getImage().getElement());
		Vector2<Integer> globalPosition = getGlobalPosition();
		context.drawImage(imageElement, globalPosition.getX(), globalPosition.getY());
	}
	
	/**
	 * Sets new position of image displayed by this component
	 * using global (world) coordinates.
	 * @param position Vector2<Integer> Position in global space.
	 */
	public void setGlobalPosition(Vector2<Integer> position) {
		this.localPosition = new Vector2<Integer>(
			position.getX() - getGameObject().getPosition().getX(),
			position.getY() - getGameObject().getPosition().getY()
		);
	}
	
	/**
	 * Returns position in global space.
	 * @return Vector2<Integer> Position in global space.
	 */
	public Vector2<Integer> getGlobalPosition() {
		return new Vector2<Integer>(
			getGameObject().getPosition().getX() + localPosition.getX(),
			getGameObject().getPosition().getY() + localPosition.getY()
		);
	}
	
	/**
	 * Sets new position of image displayed by this component
	 * using local (relatively to game object) coordinates.
	 * @param position Vector2<Integer> Position in global space.
	 */
	public void setLocalPosition(Vector2<Integer> position) {
		this.localPosition = position;
	}
	
	/**
	 * Returns position in local space.
	 * @return Vector2<Integer> Position in local space.
	 */
	public Vector2<Integer> getLocalPosition() {
		return localPosition;
	}
}