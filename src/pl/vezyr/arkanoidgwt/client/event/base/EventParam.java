package pl.vezyr.arkanoidgwt.client.event.base;

/**
 * A container class for event to be passed as a 
 * parameter of the event invocation.
 * 
 * @author vezyr
 *
 * @param <T> Class of the event.
 */
public class EventParam<T extends BaseEvent> {

	private T event;
	
	public EventParam(T event) {
		this.event = event;
	}

	public T getEvent() {
		return event;
	}

	public void setEvent(T event) {
		this.event = event;
	}
}
