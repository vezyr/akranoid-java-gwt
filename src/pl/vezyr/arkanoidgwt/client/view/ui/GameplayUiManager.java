package pl.vezyr.arkanoidgwt.client.view.ui;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.data.GameplayUiData;
import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.view.CanvasWrapper;

/**
 * Manager class to handle UI during gameplay.
 * @author vezyr
 *
 */
public class GameplayUiManager implements UiManager {

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
		context.fillText("Current lives: " + gameplayUiData.getPlayersNumberOfLives(), 15, 30);
	}
}
