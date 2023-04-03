package pl.vezyr.arkanoidgwt.client.view.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.data.GameplayUiData;
import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay.LifeIndicator;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameplayState;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;

/**
 * Manager class to handle UI during gameplay.
 * @author vezyr
 *
 */
public class GameplayUiManager implements UiManager {

	private static final String TIME_SEPARATOR = ":";
	private static final String ZERO = "0";
	private CanvasWrapper canvas;
	
	private LifeIndicator lifesIndicator;
	
	public GameplayUiManager(CanvasWrapper gameplayCanvas) {
		this.canvas = gameplayCanvas;
		lifesIndicator = new LifeIndicator(new Vector2<Integer>(30, canvas.getCanvas().getCoordinateSpaceHeight() - 50));

				
	}
	
	@Override
	public void updateUi(UiData data) {
		Context2d context = canvas.getCanvas().getContext2d();
		if (!(data instanceof GameplayUiData)) {
			return;
		}
		GameplayUiData gameplayUiData = (GameplayUiData)data;
		context.setTextAlign(TextAlign.CENTER);
		
		drawTimer(context, gameplayUiData);
		drawLifesIndicator(context, gameplayUiData);
		
		
		if (gameplayUiData.getState() == GameplayState.GAME_LOST) {
			context.fillText("You Lost! Press R to play again.", canvas.getCanvas().getCoordinateSpaceWidth() / 2, canvas.getCanvas().getCoordinateSpaceHeight() / 2);
		}
	}
	
	/**
	 * Draws timer with remaining time.
	 */
	private void drawTimer(Context2d context, GameplayUiData gameplayUiData) {
		context.setFont("40px " + UiConsts.UI_FONT_NAME);
		if (gameplayUiData.getRemainingTime() < 30 * 1000) {
			context.setFillStyle(UiConsts.UI_FONT_COLOR_WARNING);
		} else {
			context.setFillStyle(UiConsts.UI_FONT_COLOR_LIGHT);
		}
		context.fillText(formatRemainingTime(gameplayUiData.getRemainingTime()), 
				canvas.getCanvas().getCoordinateSpaceWidth() - 100, 
				canvas.getCanvas().getCoordinateSpaceHeight() - 25
		);
	}
	
	private void drawLifesIndicator(Context2d context, GameplayUiData gameplayUiData) {
		lifesIndicator.setNumberOfLives(gameplayUiData.getPlayersNumberOfLives());
		lifesIndicator.draw(context);
	}
	
	/**
	 * Formats time in miliseconds to human readable format 00:00.
	 * @param remainingTime Time in miliseconds
	 * @return String Formatted time.
	 */
	private String formatRemainingTime(long remainingTime) {
		int totalNumberOfSeconds = (int)remainingTime / 1000;
		
		int numberOfMinutes = (int)totalNumberOfSeconds / 60;
		int numberOfSeconds = (int)totalNumberOfSeconds % 60;
		
		// Because GWT dosen't handle Java's String.format
		// we need to do this manually.
		StringBuffer result = new StringBuffer();
		if (numberOfMinutes < 10) {
			result.append(ZERO).append(numberOfMinutes);
		} else {
			result.append(numberOfMinutes);
		}
		result.append(TIME_SEPARATOR);
		if (numberOfSeconds < 10) {
			result.append(ZERO).append(numberOfSeconds);
		} else {
			result.append(numberOfSeconds);
		}
		
		return result.toString();
	}
}
