package pl.vezyr.arkanoidgwt.client.manager.input;

import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public interface MouseInputHandler {

	public void handleMouseInput(Vector2<Integer> mousePosition, boolean isLeftButtonPressed, boolean isLeftButtonJustReleased);
	
}
