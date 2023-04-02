package pl.vezyr.arkanoidgwt.client.view.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.NativeEvent;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.ButtonState;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.NewGameButton;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.view.ViewHelper;

public class MainMenuUiManager implements UiManager {

	private Button newGameButton;
	
	public MainMenuUiManager() {
		newGameButton = new NewGameButton(new Vector2<Integer>(540, 300));
	}
	
	@Override
	public void updateUi(UiData data) {
		Context2d context = GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getContext2d();
		Vector2<Integer> mousePosition = GameManager.getInputManager().getMousePosition();
		if (ViewHelper.isOverUiElement(mousePosition, newGameButton)) {
			if (GameManager.getInputManager().isMouseButtonPressed(NativeEvent.BUTTON_LEFT)) {
				newGameButton.setState(ButtonState.PRESSED);
			} else {
				if (GameManager.getInputManager().isButtonJustReleased(NativeEvent.BUTTON_LEFT)) {
					newGameButton.onClick();
				}
				newGameButton.setState(ButtonState.HOVER);
			}
		} else {
			newGameButton.setState(ButtonState.NORMAL);
		}
		
		ViewHelper.drawGameObject(context, newGameButton);
	}

}
