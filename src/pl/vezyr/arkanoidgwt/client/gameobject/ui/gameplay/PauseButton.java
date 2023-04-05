package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.helper.ViewHelper;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.GameplayManager;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.register.Registrable;

/**
 * UI element representing pause button (mouse clickable).
 * 
 * @author vezyr
 *
 */
public class PauseButton extends UiElement implements MouseInputHandler, Registrable {

	private ImageComponent imageComponent;
	
	public PauseButton(Vector2<Integer> position) {
		super(position, new Vector2<Integer>(20, 32));
		imageComponent = new ImageComponent(this, ImagesPool.getImage(ImagesPool.UI_GAMEPLAY_BUTTON_PAUSE));
	}

	@Override
	public void draw(Context2d context) {
		super.draw(context);
		imageComponent.draw(context);
	}
	
	@Override
	public void handleMouseInput(Vector2<Integer> mousePosition, boolean isLeftButtonPressed,
			boolean isLeftButtonJustReleased) {
		
		if (ViewHelper.isOverUiElement(mousePosition, this) && isLeftButtonJustReleased) {
			if (GameManager.getSceneManager() instanceof GameplayManager) {
				((GameplayManager)GameManager.getSceneManager()).pause();
			}
		}
		
	}
	
	@Override
	public void register() {
		ObjectsRegister.register(this);
	}
	
	@Override
	public void unregister() {
		ObjectsRegister.unregister(this);
	}
}
