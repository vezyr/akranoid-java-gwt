package pl.vezyr.arkanoidgwt.client.gameobject.ui.mainmenu;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.UiConsts;
import pl.vezyr.arkanoidgwt.client.gameobject.component.TextComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

public class LoadingCompletedElement extends UiElement {

	private TextComponent mainLoadingText;
	private TextComponent additionalText;
	private TextComponent disclaimerText;
	
	public LoadingCompletedElement() {
		super(new Vector2<Integer>(0, 0), GameManager.getConfigManager().getTechnicalConfig().getScreenResolution());
		
		mainLoadingText = new TextComponent(this, 
				new Vector2<Integer>(getSize().getX() / 2, getSize().getY() / 2), 
				"Loading completed!", 
				UiConsts.UI_FONT_SIZE_HEADER, 
				UiConsts.UI_FONT_NAME, 
				UiConsts.UI_FONT_COLOR_LIGHT
		);
		additionalText = new TextComponent(this, 
				new Vector2<Integer>(getSize().getX() / 2, getSize().getY() / 2 + 80), 
				"Click with left mouse button anywhere inside the canvas to begin", 
				UiConsts.UI_FONT_SIZE_BASE, 
				UiConsts.UI_FONT_NAME, 
				UiConsts.UI_FONT_COLOR_LIGHT
		);
		disclaimerText = new TextComponent(this, 
				new Vector2<Integer>(590, getSize().getY() - 20), 
				"Modern web browsers prevent playing sounds before user interacts with element on site (it's protection mechanism).", 
				UiConsts.UI_FONT_SIZE_SMALL, 
				UiConsts.UI_FONT_NAME, 
				UiConsts.UI_FONT_COLOR_PURPLE
		);
	}

	@Override
	public void draw(Context2d context) {
		super.draw(context);
		mainLoadingText.draw(context);
		additionalText.draw(context);
		disclaimerText.draw(context);
	}
}
