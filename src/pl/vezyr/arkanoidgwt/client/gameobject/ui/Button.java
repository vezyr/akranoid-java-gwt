package pl.vezyr.arkanoidgwt.client.gameobject.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.data.uielement.ButtonStateData;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class Button extends UiElement {

	private Map<ButtonState, ButtonStateData> buttonStatesData;
	private ButtonState state;
	private static final Logger logger = Logger.getLogger(Button.class.getName());
	
	public Button(Vector2<Integer> position, ButtonStateData normalStateData, ButtonStateData hoverStateData, ButtonStateData pressedStateData) {
		super(position, normalStateData.getImage());
		buttonStatesData = new HashMap<ButtonState, ButtonStateData>(3);
		buttonStatesData.put(ButtonState.NORMAL, normalStateData);
		buttonStatesData.put(ButtonState.HOVER, hoverStateData);
		buttonStatesData.put(ButtonState.PRESSED, pressedStateData);
		state = ButtonState.NORMAL;
	}
	
	@Override
	public Image getImage() {
		if (buttonStatesData.get(state) == null || buttonStatesData.get(state).getImage() == null) {
			return buttonStatesData.get(ButtonState.NORMAL).getImage();
		}
		
		return buttonStatesData.get(state).getImage();
	}
	
	@Override
	public Vector2<Integer> getPosition() {
		Vector2<Integer> offset;
		if (buttonStatesData.get(state) == null) {
			offset = buttonStatesData.get(ButtonState.NORMAL).getOffset();
		} else {
			offset = buttonStatesData.get(state).getOffset();
		}
		
		return new Vector2<Integer>(
			super.getPosition().getX() + offset.getX(),
			super.getPosition().getY() + offset.getY()
		);
	}
	
	public void onClick() {
		logger.info("Button clicked");
	}

	public void setState(ButtonState state) {
		this.state = state;
	}
}
