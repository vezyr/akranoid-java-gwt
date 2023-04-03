package pl.vezyr.arkanoidgwt.client.gameobject.component;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * A GameObject's component that contains Image.
 * @author vezyr
 *
 */
public class ImageComponent extends BaseComponent implements Drawable {

	private Image image;
	private int width;
	private int height;
	
	public ImageComponent(GameObject gameObject, Image image) {
		super(gameObject);
		this.image = image;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	@Override
	public void draw(Context2d context) {
		ImageElement imageElement = ImageElement.as(image.getElement());
		context.drawImage(imageElement, getGameObject().getPosition().getX(), getGameObject().getPosition().getY());
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Vector2<Integer> getSize() {
		return new Vector2<Integer>(width, height);
	}
}
