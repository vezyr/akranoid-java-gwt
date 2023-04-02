package pl.vezyr.arkanoidgwt.client.view.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;

import pl.vezyr.arkanoidgwt.client.data.GameplayUiData;
import pl.vezyr.arkanoidgwt.client.data.UiData;
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
	
	public GameplayUiManager(CanvasWrapper gameplayCanvas) {
		this.canvas = gameplayCanvas;
	}
	
	@Override
	public void updateUi(UiData data) {
		Context2d context = canvas.getCanvas().getContext2d();
		if (!(data instanceof GameplayUiData)) {
			return;
		}
		GameplayUiData gameplayUiData = (GameplayUiData)data;
		
		context.setFont("bold 20px Lato");
		context.setFillStyle("#0A0A0A");
		context.fillText("Current lives: " + gameplayUiData.getPlayersNumberOfLives(), 90, 30);
		
		context.setTextAlign(TextAlign.CENTER);
		context.fillText(formatRemainingTime(gameplayUiData.getRemainingTime()), canvas.getCanvas().getCoordinateSpaceWidth() / 2, 30);
		
		if (gameplayUiData.getState() == GameplayState.GAME_LOST) {
			context.fillText("You Lost! Press R to play again.", canvas.getCanvas().getCoordinateSpaceWidth() / 2, canvas.getCanvas().getCoordinateSpaceHeight() / 2);
		}
	}
	
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
