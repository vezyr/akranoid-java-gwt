package pl.vezyr.arkanoidgwt.client.view.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.KeyCodes;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.NewGameButton;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.QuitGameButton;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.input.KeyboardInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.register.Registrable;

public class MainMenuUiManager implements UiManager, KeyboardInputHandler, Registrable {

	private Button newGameButton;
	private Button quitGameButton;
	
	private int currentSelectedButton;
	
	private Map<Integer, Button> selectionToButtonMap;
	
	public MainMenuUiManager() {
		newGameButton = new NewGameButton(new Vector2<Integer>(540, 400));
		quitGameButton = new QuitGameButton(new Vector2<Integer>(540, 470));
		
		selectionToButtonMap = new HashMap<Integer, Button>(2);
		selectionToButtonMap.put(1, newGameButton);
		selectionToButtonMap.put(2, quitGameButton);
		
		currentSelectedButton = 1;
		
		if (this instanceof Registrable) {
			((Registrable)this).register();
		}
	}
	
	@Override
	public void updateUi(UiData data) {
		updateState();
		Context2d context = GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getContext2d();
		if (currentSelectedButton != -1) {
			selectionToButtonMap.get(currentSelectedButton).setSelected(true);
		}
		
		newGameButton.draw(context);
		quitGameButton.draw(context);
	}
	
	@Override
	public void handleKeyboardInput(Set<Integer> pressedKeys, int justReleasedKey) {
		if (justReleasedKey == KeyCodes.KEY_UP) {
			deselectAllButtons();
			if (currentSelectedButton == -1) {
				currentSelectedButton = 1;
			}
			currentSelectedButton--;
			if (currentSelectedButton < 1) {
				currentSelectedButton = selectionToButtonMap.size();
			}
		} else if (justReleasedKey == KeyCodes.KEY_DOWN) {
			deselectAllButtons();
			if (currentSelectedButton == -1) {
				currentSelectedButton = selectionToButtonMap.size();
			}
			currentSelectedButton++;
			if (currentSelectedButton > selectionToButtonMap.size()) {
				currentSelectedButton = 1;
			}
		} else if (justReleasedKey == KeyCodes.KEY_ENTER) {
			selectionToButtonMap.get(currentSelectedButton).onClick();
		}
	}
	
	@Override
	public void register() {
		ObjectsRegister.register(this);	
	}

	/**
	 * Updates the state on any specific conditions,
	 * ie. deselect button if mouse has moved.
	 */
	private void updateState() {
		if (GameManager.getInputManager().hasMouseMoved()) {
			currentSelectedButton = -1;
		}
	}
	
	private void deselectAllButtons() {
		for (Button button : selectionToButtonMap.values()) {
			button.setSelected(false);
		}
	}
}
