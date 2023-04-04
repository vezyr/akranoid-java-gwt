package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.uielement.ButtonStateData;
import pl.vezyr.arkanoidgwt.client.event.LeaveGameButtonClickEvent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

public class LeaveGameButton extends Button {

	public LeaveGameButton(Vector2<Integer> position) {
		super(
			position, 
			new ButtonStateData(ImagesPool.getImage(ImagesPool.UI_BUTTON_BLUE_NORMAL), new Vector2<Integer>(0, 0)), 
			new ButtonStateData(ImagesPool.getImage(ImagesPool.UI_BUTTON_YELLOW_NORMAL), new Vector2<Integer>(0, 0)), 
			new ButtonStateData(ImagesPool.getImage(ImagesPool.UI_BUTTON_YELLOW_PRESSED), new Vector2<Integer>(0, 5)),
			"Leave Game"
		);
	}
	
	@Override
	public void onClick() {
		super.onClick();
		GameManager.dispatchEvent(new LeaveGameButtonClickEvent());
	}
	
}
