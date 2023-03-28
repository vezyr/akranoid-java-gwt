package pl.vezyr.arkanoidgwt.client;

import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.GameState;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ArkanoidGwt implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		ImagesPool.init();
		
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				if (ImagesPool.isPoolAttached()) {
					GameManager gameManager = new GameManager();
					gameManager.changeState(GameState.GAMEPLAY);
				} else {
					Scheduler.get().scheduleDeferred(this);
				}
			}
		});
	}
}
