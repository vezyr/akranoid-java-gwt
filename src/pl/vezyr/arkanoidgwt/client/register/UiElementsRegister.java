package pl.vezyr.arkanoidgwt.client.register;

import java.util.HashSet;
import java.util.Set;

import pl.vezyr.arkanoidgwt.client.gameobject.ui.UiElement;

public class UiElementsRegister {

	// It could be better to use WeakReference<UiElement> 
	// but GWT dosen't support it.
	private static final Set<UiElement> references = new HashSet<UiElement>();
	
	public static final void register(UiElement object) {
		references.add(object);
	}
	
	public static final Set<UiElement> getActiveReferences() {
		Set<UiElement> result = new HashSet<UiElement>();
		
		for(UiElement ref : references) {
			if (ref != null) {
				result.add(ref);
			}
		}
		// Removes all inactive references (we know
		// that result contains only active references
		// because we checked it above).
		references.retainAll(result);
		
		return result;
	}
}
