package pl.vezyr.arkanoidgwt.client.view.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;

import pl.vezyr.arkanoidgwt.client.data.UiData;
import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;

public abstract class BaseUiManager implements UiManager  {

	protected List<UiElement> allElements;
	
	public BaseUiManager() {
		allElements = new ArrayList<UiElement>();
	}
	
	private void beforeUpdateUi(UiData data) {
		allElements.forEach(element -> element.setActive(false));
	}
	
	private void afterUpdateUi(UiData data) {
		allElements.forEach(element -> {
			if (element.isActive()) {
				element.draw(getContext());
			}
		});
	}
	
	protected abstract void mainUpdateUi(UiData data);
	
	@Override
	public final void updateUi(UiData data) {
		beforeUpdateUi(data);
		mainUpdateUi(data);
		afterUpdateUi(data);	
	}

	protected abstract Context2d getContext();
}
