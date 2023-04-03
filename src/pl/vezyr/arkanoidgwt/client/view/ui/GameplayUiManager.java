package pl.vezyr.arkanoidgwt.client.view.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.data.GameplayUiData;
import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.data.config.DifficultyLevel;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay.ChooseDifficultyButton;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay.LifeIndicator;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay.Timer;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay.YouLostPopup;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.GameplayState;
import pl.vezyr.arkanoidgwt.client.manager.input.KeyboardInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.register.Registrable;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;
import pl.vezyr.arkanoidgwt.client.view.ViewHelper;

/**
 * Manager class to handle UI during gameplay.
 * @author vezyr
 *
 */
public class GameplayUiManager extends BaseUiManager implements KeyboardInputHandler, Registrable {
	
	private LifeIndicator lifesIndicator;
	private Timer timer;
	private YouLostPopup youLostPopup;
	private List<ChooseDifficultyButton> difficultyButtons;
	
	private int currentSelectedButton;
	private Map<Integer, Button> selectionToButtonMap;
	
	public GameplayUiManager(CanvasWrapper gameplayCanvas) {
		CanvasWrapper canvas = GameManager.getCanvasManager().getCurrentLoadedCanvas();
		
		lifesIndicator = new LifeIndicator(new Vector2<Integer>(30, canvas.getCanvas().getCoordinateSpaceHeight() - 50));
		youLostPopup = new YouLostPopup(new Vector2<Integer>(
				canvas.getCanvas().getCoordinateSpaceWidth() / 2, 
				canvas.getCanvas().getCoordinateSpaceHeight() / 2
		));
		
		
		difficultyButtons = new ArrayList<ChooseDifficultyButton>();
		for(int i = 0; i < GameManager.getConfigManager().getDifficultyLevels().size(); i++) {
			DifficultyLevel level = GameManager.getConfigManager().getDifficultyLevels().get(i);
			
			difficultyButtons.add(
				new ChooseDifficultyButton(
					new Vector2<Integer>(540, 200 + (i * 70)), 
					level
				)
			);
		}
		
		selectionToButtonMap = new HashMap<Integer, Button>(5);
		selectionToButtonMap.put(1, difficultyButtons.get(0));
		selectionToButtonMap.put(2, difficultyButtons.get(1));
		selectionToButtonMap.put(3, difficultyButtons.get(2));
		selectionToButtonMap.put(4, difficultyButtons.get(3));
		selectionToButtonMap.put(5, difficultyButtons.get(4));
		
		currentSelectedButton = 1;
		
		timer = new Timer(new Vector2<Integer>(
				canvas.getCanvas().getCoordinateSpaceWidth() - 100, canvas.getCanvas().getCoordinateSpaceHeight() - 25
			), UiConsts.UI_FONT_COLOR_LIGHT);
		
		allElements.add(lifesIndicator);
		allElements.add(timer);
		allElements.add(youLostPopup);
		allElements.addAll(difficultyButtons);
		
		if (this instanceof Registrable) {
			((Registrable)this).register();
		}
	}
	
	@Override
	public void mainUpdateUi(UiData data) {
		if (!(data instanceof GameplayUiData)) {
			return;
		}
		GameplayUiData gameplayUiData = (GameplayUiData)data;
		
		updateState();
		
		if (gameplayUiData.getState() != GameplayState.CHOOSE_DIFFICULTY) {
			timer.updateTimer(gameplayUiData.getRemainingTime());
			timer.setActive(true);
			lifesIndicator.setNumberOfLives(gameplayUiData.getPlayersNumberOfLives());
			lifesIndicator.setActive(true);
		} else {
			difficultyButtons.forEach(button -> button.setActive(true));
		}
		if (gameplayUiData.getState() == GameplayState.GAME_LOST) {
			youLostPopup.setActive(true);
		}
	}
	
	@Override
	protected Context2d getContext() {
		return GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getContext2d();
	}

	@Override
	public void register() {
		ObjectsRegister.register(this);
	}

	@Override
	public void unregister() {
		ObjectsRegister.unregister(this);
	}

	@Override
	public void handleKeyboardInput(Set<Integer> pressedKeys, int justReleasedKey) {
		currentSelectedButton = ViewHelper.handleKeyboardInputOnVerticalMenu(justReleasedKey, currentSelectedButton, selectionToButtonMap);		
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
	
}
