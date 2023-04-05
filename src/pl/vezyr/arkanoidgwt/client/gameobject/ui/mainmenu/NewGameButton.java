package pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.uielement.ButtonStateData;
import pl.vezyr.arkanoidgwt.client.event.gameplay.NewGameButtonClickEvent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class NewGameButton extends Button {

	public NewGameButton(Vector2<Integer> position) {
		super(
			position, 
			new ButtonStateData(ImagesPool.getImage(ImagesPool.UI_BUTTON_BLUE_NORMAL), new Vector2<Integer>(0, 0)), 
			new ButtonStateData(ImagesPool.getImage(ImagesPool.UI_BUTTON_YELLOW_NORMAL), new Vector2<Integer>(0, 0)), 
			new ButtonStateData(ImagesPool.getImage(ImagesPool.UI_BUTTON_YELLOW_PRESSED), new Vector2<Integer>(0, 5)),
			"New Game"
		);
	}

	@Override
	public void onClick() {
		super.onClick();
		(new NewGameButtonClickEvent()).fire();
	}
}
