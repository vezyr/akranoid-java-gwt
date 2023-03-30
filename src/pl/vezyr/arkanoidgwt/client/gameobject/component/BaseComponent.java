package pl.vezyr.arkanoidgwt.client.gameobject.component;

import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;

/***
 * Base of all GameObject's components.
 * Provides linking between Component and GameObject. 
 * @author vezyr
 *
 */
public abstract class BaseComponent {

	private GameObject gameObject;
	
	public BaseComponent(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	protected GameObject getGameObject() {
		return gameObject;
	}
}
