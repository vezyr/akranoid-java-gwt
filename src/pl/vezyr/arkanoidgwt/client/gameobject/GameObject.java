package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public abstract class GameObject {

	private Vector2<Integer> position;
	private Image image;
	
	public GameObject(Vector2<Integer> position, Image image) {
		this.position = position;
		this.image = image;
	}
	
	public Vector2<Integer> getPosition() {
		return position;
	}
	public Image getImage() {
		return image;
	}
}
