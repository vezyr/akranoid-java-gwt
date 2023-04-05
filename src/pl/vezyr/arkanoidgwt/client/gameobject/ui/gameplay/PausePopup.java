package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.gameobject.component.TextComponent;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class PausePopup extends BasePopup {

	private TextComponent headerText;
	
	private ResumeButton resumeButton;
	private LeaveGameButton leaveGameButton;
	
	public PausePopup(Vector2<Integer> position) {
		super(new Vector2<Integer>(position.getX() - (320 / 2), position.getY() - (460 / 2)), new Vector2<Integer>(320, 460));
		
		headerText = new TextComponent(
				this, 
				new Vector2<Integer>(getSize().getX() / 2, 60), 
				"Paused", 
				UiConsts.UI_FONT_SIZE_HEADER, 
				UiConsts.UI_FONT_NAME, 
				UiConsts.UI_FONT_COLOR_DARK
		);
		
		resumeButton = new ResumeButton(new Vector2<Integer>(position.getX() - (getSize().getX() / 2) + 65, position.getY()));
		leaveGameButton = new LeaveGameButton(new Vector2<Integer>(position.getX() - (getSize().getX() / 2) + 65, position.getY() + 70));
		
		selectionToButtonMap.put(1, resumeButton);
		selectionToButtonMap.put(2, leaveGameButton);
		
		currentSelectedButton = 1;
	}

	@Override
	public void draw(Context2d context) {
		super.draw(context);
		headerText.draw(context);
		resumeButton.draw(context);
		leaveGameButton.draw(context);
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		resumeButton.setActive(active);
		leaveGameButton.setActive(active);
	}
}
