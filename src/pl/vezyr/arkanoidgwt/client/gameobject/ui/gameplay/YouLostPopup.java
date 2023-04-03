package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.PositionedImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.StretchedAndPositionedImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.TextComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class YouLostPopup extends UiElement {

	private PositionedImageComponent blendImage;
	private List<ImageComponent> imageComponents;
	private TextComponent headerText;
	
	private RestartButton restartButton;
	private LeaveGameButton leaveGameButton;
	
	public YouLostPopup(Vector2<Integer> position) {
		super(new Vector2<Integer>(position.getX() - (320 / 2), position.getY() - (460 / 2)), new Vector2<Integer>(320, 460));
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
		
		headerText = new TextComponent(
				this, 
				new Vector2<Integer>(getSize().getX() / 2, 60), 
				"Game Lost", 
				UiConsts.UI_FONT_SIZE_HEADER, 
				UiConsts.UI_FONT_NAME, 
				UiConsts.UI_FONT_COLOR_DARK
		);
		
		restartButton = new RestartButton(new Vector2<Integer>(position.getX() - (getSize().getX() / 2) + 65, position.getY()));
		leaveGameButton = new LeaveGameButton(new Vector2<Integer>(position.getX() - (getSize().getX() / 2) + 65, position.getY() + 70));
	}

	@Override
	public void draw(Context2d context) {
		blendImage.draw(context);
		imageComponents.forEach(component -> component.draw(context));
		headerText.draw(context);
		restartButton.draw(context);
		leaveGameButton.draw(context);
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		restartButton.setActive(active);
		leaveGameButton.setActive(active);
	}
}
