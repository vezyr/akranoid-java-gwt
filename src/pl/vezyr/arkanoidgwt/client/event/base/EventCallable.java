package pl.vezyr.arkanoidgwt.client.event.base;

/**
 * A callable interface for event's action.
 * All actions that are added to event as listener
 * should inherit from this class.
 * 
 * @author vezyr
 *
 * @param <T> Class of the event.
 */
public interface EventCallable<T extends BaseEvent> {

	public void call(EventParam<T> event);
	
}
