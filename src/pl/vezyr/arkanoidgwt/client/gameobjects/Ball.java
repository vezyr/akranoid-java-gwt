package pl.vezyr.arkanoidgwt.client.gameobjects;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class Ball extends GameObject {

	private Vector2<Float> direction;
	
	public Ball(Vector2<Integer> position, Image image) {
		super(position, image);
		direction = new Vector2<Float>();
	}

	public Vector2<Float> getDirection() {
		return direction;
	}
}
