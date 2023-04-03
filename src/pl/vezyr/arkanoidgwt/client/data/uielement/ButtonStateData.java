package pl.vezyr.arkanoidgwt.client.data.uielement;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class ButtonStateData {

	private final Image image;
	private final Vector2<Integer> offset;
	
	public ButtonStateData(Image image, Vector2<Integer> offset) {
		this.image = image;
		this.offset = offset;
	}

	public Image getImage() {
		return image;
	}

	public Vector2<Integer> getOffset() {
		return offset;
	}	
}
