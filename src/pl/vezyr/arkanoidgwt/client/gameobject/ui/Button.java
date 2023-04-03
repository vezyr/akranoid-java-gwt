package pl.vezyr.arkanoidgwt.client.gameobject.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.data.uielement.ButtonStateData;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseInputHandler;
import pl.vezyr.arkanoidgwt.client.register.Registrable;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.view.ViewHelper;

public class Button extends UiElement implements MouseInputHandler, Registrable {

	private Map<ButtonState, ButtonStateData> buttonStatesData;
	private ButtonState state;
	private static final Logger logger = Logger.getLogger(Button.class.getName());
	private String textOnButton;
	
	public Button(Vector2<Integer> position, ButtonStateData normalStateData, ButtonStateData hoverStateData, ButtonStateData pressedStateData, String textOnButton) {
		super(position, normalStateData.getImage());
		this.textOnButton = textOnButton;
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
	
	@Override
	public void handleMouseInput(Vector2<Integer> mousePosition, boolean isLeftButtonPressed,
			boolean isLeftButtonJustReleased) {
		if (ViewHelper.isOverUiElement(mousePosition, this)) {
			if (isLeftButtonPressed) {
				setState(ButtonState.PRESSED);
			} else {
				if (isLeftButtonJustReleased) {
					onClick();
				}
				setState(ButtonState.HOVER);
			}
		} else {
			setState(ButtonState.NORMAL);
		}	
	}
	
	@Override
	public void register() {
		ObjectsRegister.register(this);
	}
	
	@Override
	public void draw(Context2d context) {
		super.draw(context);
		context.setTextAlign(TextAlign.CENTER);
		context.setFont("20px KenvectorFuture");
		context.fillText(textOnButton, 
				getPosition().getX() + (getImage().getWidth() / 2), 
				getPosition().getY() + (getImage().getHeight() / 2) + 5);
	}
	
	/**
	 * Should be fired on mouse click on button or
	 * keyboard confimation key (ie. ENTER) when button is 
	 * selected.
	 */
	public void onClick() {
		logger.info("Button clicked");
	}

	/**
	 * Sets new state to the button.
	 * @param state ButtonState New state to set.
	 */
	public void setState(ButtonState state) {
		this.state = state;
	}
	
	/**
	 * Sets that this button is selected, not necessary 
	 * by pointing by mouse (ie. during keyboard usage).
	 * @param selected Is button selected (true) or not (false)
	 */
	public void setSelected(boolean selected) {
		setState(selected ? ButtonState.HOVER : ButtonState.NORMAL);
	}
}
