package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class LifeIndicator extends UiElement {
	
	private int numberOfLives;
	
	public LifeIndicator(Vector2<Integer> position) {
		super(
			position, 
			new Vector2<Integer>(
				ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_HEART_FILLED).getWidth() * 3 + (38 * 2),
				ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_HEART_FILLED).getHeight()
			)
		);
		numberOfLives = 0;
	}

	public void setNumberOfLives(int numberOfLives) {
		this.numberOfLives = numberOfLives;
	}
	
	@Override
	public void draw(Context2d context) {
		for (int i = 0; i < numberOfLives; i++) {
			ImageElement imageElement = ImageElement.as(ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_HEART_FILLED).getElement());
			context.drawImage(imageElement, getPosition().getX() + (38 * i), getPosition().getY());
		}
		for (int i = numberOfLives; i < 3; i++) {
			ImageElement imageElement = ImageElement.as(ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_HEART_STROKED).getElement());
			context.drawImage(imageElement, getPosition().getX() + (38 * i), getPosition().getY());
		}
	}
}
