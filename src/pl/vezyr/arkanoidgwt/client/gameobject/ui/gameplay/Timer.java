package pl.vezyr.arkanoidgwt.client.gameobject.ui.gameplay;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.gameobject.component.TextComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class Timer extends UiElement  {

	private static final String TIME_SEPARATOR = ":";
	private static final String ZERO = "0";
	
	private String color;
	private TextComponent textComponent;
	
	public Timer(Vector2<Integer> position, String color) {
		super(position, new Vector2<Integer>(100, 100));
		
		textComponent = new TextComponent(this, 
				new Vector2<Integer>(0, 0), "00:00", UiConsts.UI_FONT_SIZE_HEADER, UiConsts.UI_FONT_NAME, UiConsts.UI_FONT_COLOR_LIGHT);
	}
	
	@Override
	public void draw(Context2d context) {
		super.draw(context);
		textComponent.draw(context);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void updateTimer(long remaining) {
		textComponent.setText(formatRemainingTime(remaining));
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
