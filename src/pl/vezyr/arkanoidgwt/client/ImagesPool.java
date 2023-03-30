package pl.vezyr.arkanoidgwt.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ImagesPool {

	public static final String IMAGE_BALL = "ball";
	public static final String IMAGE_PADDLE = "paddle";
	public static final String IMAGE_BLOCK_WEAK = "block_weak";
	public static final String IMAGE_BLOCK_MEDIUM = "block_medium";
	public static final String IMAGE_BLOCK_STRONG = "block_strong";
	
	private static final String IMAGES_CONTAINER_NAME = "images_container";
	
	private static Map<String, Image> images = new HashMap<String, Image>();
	
	public static void init() {
		if (images.size() > 0) {
			// Already initialized.
			return;
		}
		
		images.put(IMAGE_BALL, new Image("images/ballBlue.png"));
		images.put(IMAGE_PADDLE, new Image("images/paddleBlu.png"));
		images.put(IMAGE_BLOCK_WEAK, new Image("images/blockYellow.png"));
		images.put(IMAGE_BLOCK_MEDIUM, new Image("images/blockBlue.png"));
		images.put(IMAGE_BLOCK_STRONG, new Image("images/blockRed.png"));
		
		images.forEach((String key, Image value) -> {
			value.setVisible(false);
			RootPanel.get(IMAGES_CONTAINER_NAME).add(value);
		});
	}
	
	public static boolean isPoolAttached() {
		for (Image image : images.values()) {
			if (!image.isAttached()) {
				return false;
			}
		}
		return true;
	}
	
	public static Image getImage(String id) {
		return images.get(id);
	}
}
