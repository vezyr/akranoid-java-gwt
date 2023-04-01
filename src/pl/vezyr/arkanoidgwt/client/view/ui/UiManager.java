package pl.vezyr.arkanoidgwt.client.view.ui;

import pl.vezyr.arkanoidgwt.client.data.UiData;

/**
 * Interface for UI manager.
 * @author vezyr
 *
 */
public interface UiManager {
	
	/** 
	 * Updates UI - redraws UI content on canvas.
	 */
	public void updateUi(UiData data);
}
