package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class YouLostPopup extends BaseGameEndPopup {
	
	public YouLostPopup(Vector2<Integer> position) {
		super(new Vector2<Integer>(position.getX() - (320 / 2), position.getY() - (460 / 2)), new Vector2<Integer>(320, 460), "Game Lost!");
		
	}
}
