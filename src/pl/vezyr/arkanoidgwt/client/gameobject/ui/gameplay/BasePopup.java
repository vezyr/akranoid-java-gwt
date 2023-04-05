package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.PositionedImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.StretchedAndPositionedImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.helper.ViewHelper;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.input.KeyboardInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.register.Registrable;

public abstract class BasePopup extends UiElement implements KeyboardInputHandler, Registrable {

	private PositionedImageComponent blendImage;
	private List<ImageComponent> imageComponents;
	
	protected int currentSelectedButton;
	protected Map<Integer, Button> selectionToButtonMap;
	
	public BasePopup(Vector2<Integer> position, Vector2<Integer> size) {
		super(position, size);
		
		currentSelectedButton = -1;
		selectionToButtonMap = new HashMap<Integer, Button>();
		
		imageComponents = new ArrayList<ImageComponent>();
		
		int cornerWidth = ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_TOP_LEFT).getWidth();
		int cornerHeight = ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_TOP_LEFT).getHeight();
		
		blendImage = new PositionedImageComponent(this, ImagesPool.getImage(ImagesPool.UI_BLEND), position);
		blendImage.setGlobalPosition(new Vector2<Integer>(0, 0));
		
		imageComponents.add(new PositionedImageComponent(this, 				ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_TOP_LEFT), 		new Vector2<Integer>(0, 0)));
		imageComponents.add(new StretchedAndPositionedImageComponent(this, 	ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_TOP), 			new Vector2<Integer>(cornerWidth, 0), new Vector2<Integer>(getSize().getX() - (2 * cornerWidth), cornerHeight)));
		imageComponents.add(new PositionedImageComponent(this, 				ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_TOP_RIGHT),		new Vector2<Integer>(getSize().getX() - cornerWidth, 0)));
		imageComponents.add(new StretchedAndPositionedImageComponent(this, 	ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_LEFT), 			new Vector2<Integer>(0, cornerWidth), new Vector2<Integer>(cornerWidth, getSize().getY() - (2 * cornerHeight))));
		imageComponents.add(new StretchedAndPositionedImageComponent(this, 	ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_RIGHT), 			new Vector2<Integer>(getSize().getX() - cornerWidth, cornerHeight), new Vector2<Integer>(cornerWidth, getSize().getY() - (2 * cornerHeight))));
		imageComponents.add(new PositionedImageComponent(this, 				ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_BOTTOM_LEFT), 	new Vector2<Integer>(0, getSize().getY() - cornerHeight)));
		imageComponents.add(new StretchedAndPositionedImageComponent(this, 	ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_BOTTOM), 			new Vector2<Integer>(cornerWidth, getSize().getY() - cornerHeight), new Vector2<Integer>(getSize().getX() - (2 * cornerWidth), cornerHeight)));
		imageComponents.add(new PositionedImageComponent(this, 				ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_BOTTOM_RIGHT), 	new Vector2<Integer>(getSize().getX() - cornerWidth, getSize().getY() - cornerHeight)));
		imageComponents.add(new StretchedAndPositionedImageComponent(this, 	ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_POPUP_GREY_FILL), 			new Vector2<Integer>(cornerWidth, cornerHeight), new Vector2<Integer>(getSize().getX() - (2 * cornerWidth), getSize().getY() - (2 * cornerHeight))));
		
	}
	
	@Override
	public void draw(Context2d context) {
		if (GameManager.getInputManager().hasMouseMoved()) {
			currentSelectedButton = -1;
		} else if (currentSelectedButton != -1 && selectionToButtonMap.containsKey(currentSelectedButton)) {
			selectionToButtonMap.get(currentSelectedButton).setSelected(true);
		}
		blendImage.draw(context);
		imageComponents.forEach(component -> component.draw(context));
	}
	
	@Override
	public void handleKeyboardInput(Set<Integer> pressedKeys, int justReleasedKey) {
		currentSelectedButton = ViewHelper.handleKeyboardInputOnVerticalMenu(justReleasedKey, currentSelectedButton, selectionToButtonMap);
	}
	
	@Override
	public void register() {
		ObjectsRegister.register(this);
	}
	
	@Override
	public void unregister() {
		ObjectsRegister.unregister(this);
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			register();
		} else {
			unregister();
		}
	}
}
