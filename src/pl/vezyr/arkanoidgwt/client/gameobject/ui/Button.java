package pl.vezyr.arkanoidgwt.client.gameobject.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.data.uielement.ButtonStateData;
import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;
import pl.vezyr.arkanoidgwt.client.register.Registrable;
import pl.vezyr.arkanoidgwt.client.view.ViewHelper;

public class Button extends UiElement implements MouseInputHandler, Registrable {

	private Map<ButtonState, ButtonStateData> buttonStatesData;
	private ButtonState state;
	private String textOnButton;
	private ImageComponent image;
	
	public Button(Vector2<Integer> position, ButtonStateData normalStateData, ButtonStateData hoverStateData, ButtonStateData pressedStateData, String textOnButton) {
		super(position, new Vector2<Integer>(normalStateData.getImage().getWidth(), normalStateData.getImage().getHeight()));
		this.textOnButton = textOnButton;
		buttonStatesData = new HashMap<ButtonState, ButtonStateData>(3);
		buttonStatesData.put(ButtonState.NORMAL, normalStateData);
		buttonStatesData.put(ButtonState.HOVER, hoverStateData);
		buttonStatesData.put(ButtonState.PRESSED, pressedStateData);
		state = ButtonState.NORMAL;
		
		image = new ImageComponent(this, normalStateData.getImage());
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
		image.draw(context);
		context.setTextAlign(TextAlign.CENTER);
		context.setFont(UiConsts.UI_FONT_NORMAL);
		context.fillText(textOnButton, 
				getPosition().getX() + (getSize().getX() / 2), 
				getPosition().getY() + (getSize().getY() / 2) + 5);
	}
	
	/**
	 * Should be fired on mouse click on button or
	 * keyboard confimation key (ie. ENTER) when button is 
	 * selected.
	 */
	public void onClick() {
	}

	/**
	 * Sets new state to the button.
	 * @param state ButtonState New state to set.
	 */
	public void setState(ButtonState state) {
		this.state = state;
		image.setImage(buttonStatesData.get(state).getImage());
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
