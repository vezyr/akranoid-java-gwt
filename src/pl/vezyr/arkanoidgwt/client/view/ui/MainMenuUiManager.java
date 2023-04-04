package pl.vezyr.arkanoidgwt.client.view.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.NewGameButton;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.QuitGameButton;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.input.KeyboardInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.register.Registrable;
import pl.vezyr.arkanoidgwt.client.view.ViewHelper;

public class MainMenuUiManager extends BaseUiManager implements KeyboardInputHandler, Registrable {

	private Button newGameButton;
	private Button quitGameButton;
	
	private int currentSelectedButton;
	
	private Map<Integer, Button> selectionToButtonMap;
	
	public MainMenuUiManager() {
		newGameButton = new NewGameButton(new Vector2<Integer>(540, 400));
		quitGameButton = new QuitGameButton(new Vector2<Integer>(540, 470));
		
		allElements.add(newGameButton);
		allElements.add(quitGameButton);
		
		selectionToButtonMap = new HashMap<Integer, Button>(2);
		selectionToButtonMap.put(1, newGameButton);
		selectionToButtonMap.put(2, quitGameButton);
		
		currentSelectedButton = 1;
		
		if (this instanceof Registrable) {
			((Registrable)this).register();
		}
	}
	
	@Override
	public void mainUpdateUi(UiData data) {
		updateState();
		
		
		newGameButton.setActive(true);
		quitGameButton.setActive(true);
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

	/**
	 * Updates the state on any specific conditions,
	 * ie. deselect button if mouse has moved.
	 */
	private void updateState() {
		if (GameManager.getInputManager().hasMouseMoved()) {
			currentSelectedButton = -1;
		} else if (currentSelectedButton != -1) {
			selectionToButtonMap.get(currentSelectedButton).setSelected(true);
		}
	}

	@Override
	protected Context2d getContext() {
		return GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getContext2d();
	}
}
