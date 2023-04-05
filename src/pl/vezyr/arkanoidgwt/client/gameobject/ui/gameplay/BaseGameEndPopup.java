package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.gameobject.component.TextComponent;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public abstract class BaseGameEndPopup extends BasePopup {

	private TextComponent headerText;

	private RestartButton restartButton;
	private LeaveGameButton leaveGameButton;
	
	public BaseGameEndPopup(Vector2<Integer> position, Vector2<Integer> size, String header) {
		super(position, size);
		
		headerText = new TextComponent(
				this, 
				new Vector2<Integer>(getSize().getX() / 2, 60), 
				header, 
				UiConsts.UI_FONT_SIZE_HEADER, 
				UiConsts.UI_FONT_NAME, 
				UiConsts.UI_FONT_COLOR_DARK
		);
		
		restartButton = new RestartButton(new Vector2<Integer>(position.getX() + 65, position.getY() + 270));
		leaveGameButton = new LeaveGameButton(new Vector2<Integer>(position.getX() + 65, position.getY() + 340));
		
		selectionToButtonMap.put(1, restartButton);
		selectionToButtonMap.put(2, leaveGameButton);
		
		currentSelectedButton = 1;
	}

	@Override
	public void draw(Context2d context) {
		super.draw(context);
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
