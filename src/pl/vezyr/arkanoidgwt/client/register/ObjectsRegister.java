package pl.vezyr.arkanoidgwt.client.register;

import java.util.HashSet;
import java.util.Set;

public class ObjectsRegister {

	// It could be better to use WeakReference<UiElement> 
	// but GWT dosen't support it.
	private static final Set<Object> references = new HashSet<Object>();
	
	public static final void register(Object object) {
		references.add(object);
	}
	
	public static final Set<Object> getActiveReferences() {
		Set<Object> result = new HashSet<Object>();
		
		for(Object ref : references) {
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
