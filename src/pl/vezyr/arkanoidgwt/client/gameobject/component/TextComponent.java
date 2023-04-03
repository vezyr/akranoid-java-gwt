package pl.vezyr.arkanoidgwt.client.gameobject.component;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * GameObject's component that contains text to display.
 * @author vezyr
 *
 */
public class TextComponent extends BaseComponent implements Drawable {

	private String text;
	private int fontSize;
	private String fontName;
	private String color;
	private Vector2<Integer> localPosition;
	
	public TextComponent(GameObject gameObject, Vector2<Integer> localPosition, String text, int fontSize, String fontName, String color) {
		super(gameObject);
		this.localPosition = new Vector2<Integer>(localPosition);
		this.text = text;
		this.fontSize = fontSize;
		this.fontName = fontName;
		this.color = color;
	}
	
	@Override
	public void draw(Context2d context) {
		String originalFont = context.getFont();
		FillStrokeStyle originalColor = context.getFillStyle();
		
		context.setTextAlign(TextAlign.CENTER);
		context.setFont(fontSize + "px " + fontName);
		context.setFillStyle(color);
		context.fillText(text, getGameObject().getPosition().getX() + localPosition.getX(), getGameObject().getPosition().getY() + localPosition.getY());
		
		context.setFont(originalFont);
		context.setFillStyle(originalColor);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Vector2<Integer> getLocalPosition() {
		return localPosition;
	}

	public void setLocalPosition(Vector2<Integer> localPosition) {
		this.localPosition = localPosition;
	}
}
