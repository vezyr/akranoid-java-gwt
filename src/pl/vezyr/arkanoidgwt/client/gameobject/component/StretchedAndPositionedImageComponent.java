package pl.vezyr.arkanoidgwt.client.gameobject.component;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Positioned Image Component that is also stretched to 
 * given size.
 * @author vezyr
 *
 */
public class StretchedAndPositionedImageComponent extends PositionedImageComponent {

	private Vector2<Integer> size;
	
	public StretchedAndPositionedImageComponent(GameObject gameObject, Image image, Vector2<Integer> position, Vector2<Integer> size) {
		super(gameObject, image, position);
		setSize(size);
	}
	
	@Override
	public void draw(Context2d context) {
		ImageElement imageElement = ImageElement.as(getImage().getElement());
		Vector2<Integer> globalPosition = getGlobalPosition();
		imageElement.setWidth(size.getX());
		imageElement.setHeight(size.getY());
		context.drawImage(imageElement, globalPosition.getX(), globalPosition.getY(), size.getX(), size.getY());
	}

	/**
	 * Returns the defined size.
	 * @return Vector2<Integer> Size.
	 */
	public Vector2<Integer> getSize() {
		return new Vector2<Integer>(size);
	}

	/**
	 * Sets the size (target to stretch).
	 * @param size Vector2<Integer> New size.
	 */
	public void setSize(Vector2<Integer> size) {
		this.size = new Vector2<Integer>(size);
	}
}
