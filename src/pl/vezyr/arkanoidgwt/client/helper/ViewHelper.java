package pl.vezyr.arkanoidgwt.client.helper;

import java.util.Map;

import com.google.gwt.event.dom.client.KeyCodes;

import pl.vezyr.arkanoidgwt.client.gameobject.ui.Button;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;

public class ViewHelper {	
	
	public static boolean isOverUiElement(Vector2<Integer> point, UiElement element) {
		if (!element.isActive())
			return false;
		return point.getX() >= element.getPosition().getX() &&
				point.getX() <= element.getPosition().getX() + element.getSize().getX() &&
				point.getY() >= element.getPosition().getY() &&
				point.getY() <= element.getPosition().getY() + element.getSize().getY();
	}
	
	public static int handleKeyboardInputOnVerticalMenu(int justReleasedKey, int currentSelectedButton, Map<Integer, Button> selectionToButtonMap) {
		boolean hasAnyButtonActive = false;
		for (Button button : selectionToButtonMap.values()) {
			if (button.isActive()) hasAnyButtonActive = true;
		}
		if (!hasAnyButtonActive) {
			return -1;
		}
		
		if (justReleasedKey == KeyCodes.KEY_UP) {
			selectionToButtonMap.values().forEach(button -> button.setSelected(false));
			if (currentSelectedButton == -1) {
				currentSelectedButton = 1;
			}
			currentSelectedButton--;
			if (currentSelectedButton < 1) {
				currentSelectedButton = selectionToButtonMap.size();
			}
		} else if (justReleasedKey == KeyCodes.KEY_DOWN) {
			selectionToButtonMap.values().forEach(button -> button.setSelected(false));
			if (currentSelectedButton == -1) {
				currentSelectedButton = selectionToButtonMap.size();
			}
			currentSelectedButton++;
			if (currentSelectedButton > selectionToButtonMap.size()) {
				currentSelectedButton = 1;
			}
		} else if (justReleasedKey == KeyCodes.KEY_ENTER) {
			if (selectionToButtonMap.get(currentSelectedButton) != null) {
				selectionToButtonMap.get(currentSelectedButton).onClick();
			}
		}
		return currentSelectedButton;
	}
}