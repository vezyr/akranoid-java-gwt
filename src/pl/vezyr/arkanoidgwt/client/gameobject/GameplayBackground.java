package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobject.component.Drawable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class GameplayBackground extends GameObject implements Drawable {

	private ImageComponent image;
	
	public GameplayBackground() {
		super(
			new Vector2<Integer>(0, 0), 
			new Vector2<Integer>(
				ImagesPool.getImage(ImagesPool.IMAGE_BACKGROUND_GAMEPLAY).getWidth(), 
				ImagesPool.getImage(ImagesPool.IMAGE_BACKGROUND_GAMEPLAY).getHeight()
			)
		);
		image = new ImageComponent(this, ImagesPool.getImage(ImagesPool.IMAGE_BACKGROUND_GAMEPLAY));
	}
	
	@Override
	public void draw(Context2d context) {
		image.draw(context);
	}

	public Image getImage() {
		return image.getImage();
	}
}
