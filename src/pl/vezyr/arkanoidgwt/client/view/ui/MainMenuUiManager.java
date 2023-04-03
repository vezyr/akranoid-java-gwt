package pl.vezyr.arkanoidgwt.client.view.ui;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.NewGameButton;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu.QuitGameButton;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

public class MainMenuUiManager implements UiManager {

	private Button newGameButton;
	private Button quitGameButton;
	
	public MainMenuUiManager() {
		newGameButton = new NewGameButton(new Vector2<Integer>(540, 400));
		quitGameButton = new QuitGameButton(new Vector2<Integer>(540, 470));
	}
	
	@Override
	public void updateUi(UiData data) {
		Context2d context = GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getContext2d();
		
		newGameButton.draw(context);
		quitGameButton.draw(context);
	}

}
