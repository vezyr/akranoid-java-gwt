package pl.vezyr.arkanoidgwt.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Images' pool that can be used anywhere in the code. 
 * All images are added to {@code IMAGES_CONTAINER_NAME} DOM node 
 * on pool initialization.
 * @author vezyr
 * @see ImagesPool.IMAGES_CONTAINER_NAME
 */
public class ImagesPool {

	public static final String IMAGE_BALL = "ball";
	public static final String IMAGE_PADDLE = "paddle";
	public static final String IMAGE_BLOCK_WEAK = "block_weak";
	public static final String IMAGE_BLOCK_MEDIUM_NORMAL = "block_medium_normal";
	public static final String IMAGE_BLOCK_MEDIUM_DAMAGED = "block_medium_damaged";
	public static final String IMAGE_BLOCK_STRONG_NORMAL = "block_strong_normal";
	public static final String IMAGE_BLOCK_STRONG_LIGHT_DAMAGE = "block_strong_light_damage";
	public static final String IMAGE_BLOCK_STRONG_HEAVY_DAMAGE = "block_strong_heavy_damage";
	public static final String IMAGE_BACKGROUND_GAMEPLAY = "background_gameplay";
	
	public static final String UI_BUTTON_BLUE_NORMAL = "ui_button_blue_normal";
	public static final String UI_BUTTON_YELLOW_NORMAL = "ui_button_yellow_normal";
	public static final String UI_BUTTON_YELLOW_PRESSED = "ui_button_yellow_pressed";
	
	private static final String IMAGES_CONTAINER_NAME = "images_container";
	
	private static Map<String, Image> images = new HashMap<String, Image>();
	
	/**
	 * Initialize the pool. 
	 * After initialization the dictionary that maps ID to image object is created.
	 * All images are added to DOM node and are set to be invisible.
	 */
	public static void init() {
		if (images.size() > 0) {
			// Already initialized.
			return;
		}
		
		images.put(IMAGE_BALL, new Image("images/ballBlue.png"));
		images.put(IMAGE_PADDLE, new Image("images/paddleBlu.png"));
		images.put(IMAGE_BLOCK_WEAK, new Image("images/blockYellow.png"));
		images.put(IMAGE_BLOCK_MEDIUM_NORMAL, new Image("images/blockBlue.png"));
		images.put(IMAGE_BLOCK_MEDIUM_DAMAGED, new Image("images/blockBlueHeavyDamage.png"));
		images.put(IMAGE_BLOCK_STRONG_NORMAL, new Image("images/blockRed.png"));
		images.put(IMAGE_BLOCK_STRONG_LIGHT_DAMAGE, new Image("images/blockRedLightDamage.png"));
		images.put(IMAGE_BLOCK_STRONG_HEAVY_DAMAGE, new Image("images/blockRedHeavyDamage.png"));
		images.put(IMAGE_BACKGROUND_GAMEPLAY, new Image("images/bg.png"));
		images.put(UI_BUTTON_BLUE_NORMAL, new Image("images/ui/button_blue_normal.png"));
		images.put(UI_BUTTON_YELLOW_NORMAL, new Image("images/ui/button_yellow_normal.png"));
		images.put(UI_BUTTON_YELLOW_PRESSED, new Image("images/ui/button_yellow_pressed.png"));
		
		images.forEach((String key, Image value) -> {
			value.setVisible(false);
			RootPanel.get(IMAGES_CONTAINER_NAME).add(value);
		});
	}
	
	/**
	 * Checks if all images are already attached to DOM node.
	 * @return true if all images are attached to DOM node, false otherwise
	 */
	public static boolean isPoolAttached() {
		for (Image image : images.values()) {
			if (!image.isAttached()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets the images object base on image id.
	 * All available ids are definded as public static fields of this class.
	 * @param id Id of the image to return.
	 * @return Image object attached to given id or null if there is no Image attached to given id.
	 */
	public static Image getImage(String id) {
		return images.get(id);
	}
}
