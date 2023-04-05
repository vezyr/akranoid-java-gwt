package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.AudioPool;
import pl.vezyr.arkanoidgwt.client.event.audio.PlaySoundEvent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.GameplayManager;

/**
 * GameObject that represents Paddle.
 * Inherits from base GameObject.
 * Implements Collidable so the collisions between ball and paddle could be detected and handled.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameObject
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable
 */
public class Paddle extends GameObject implements Collidable {

	private BoxCollider collider;
	private ImageComponent image;
	
	public Paddle(Vector2<Integer> position, Image image) {
		super(position, new Vector2<Integer>(image.getWidth(), image.getHeight()));
		this.image = new ImageComponent(this, image);
		collider = new BoxCollider(this, this.image.getSize());
	}
	
	@Override
	public void update(double deltaTime) {
		super.update(deltaTime);
		if (!(GameManager.getSceneManager() instanceof GameplayManager)) {
			return;
		}
		GameplayManager gameplayManager = (GameplayManager)GameManager.getSceneManager();
		
		switch (gameplayManager.getState()) {
			case READY_TO_START:
				handleOnReadyToStart(gameplayManager, deltaTime / 1000);
			break;
			case IN_PROGRESS:
				handleOnInProgress(gameplayManager, deltaTime / 1000);
			break;
		}
	}

	private void handleOnInProgress(GameplayManager gameplayManager, double deltaTimeInSeconds) {
		move(gameplayManager, deltaTimeInSeconds);
	}

	private void handleOnReadyToStart(GameplayManager gameplayManager, double deltaTimeInSeconds) {
		move(gameplayManager, deltaTimeInSeconds);
	}
	
	private void move(GameplayManager gameplayManager, double deltaTimeInSeconds) {
		if (GameManager.getInputManager().isKeyPressed(KeyCodes.KEY_LEFT)) {
			int newPos = this.getPosition().getX() - (int)(gameplayManager.getDifficulty().getBallSpeed() * deltaTimeInSeconds);
			this.getPosition().setX(newPos > 0 ? newPos : 1);
		} else if (GameManager.getInputManager().isKeyPressed(KeyCodes.KEY_RIGHT)) {
			int newPos = this.getPosition().getX() + (int)(gameplayManager.getDifficulty().getBallSpeed() * deltaTimeInSeconds);
			this.getPosition().setX(
				newPos < GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceWidth() - this.getImage().getWidth() ? 
						newPos : 
						GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceWidth() - this.getImage().getWidth()
			);
		} else if (GameManager.getInputManager().hasMouseMoved()) { 
			if (GameManager.getInputManager().getMousePosition().getX() > (getSize().getX() / 2) && 
				GameManager.getInputManager().getMousePosition().getX() < GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceWidth() - (getSize().getX() / 2)) {
				this.getPosition().setX(GameManager.getInputManager().getMousePosition().getX() - (getSize().getX() / 2));
			}
		}
	}

	@Override
	public Collider getCollider() {
		return collider;
	}

	@Override
	public void handleCollision(CollisionResult collision) {
	}
	
	@Override
	public void draw(Context2d context) {
		image.draw(context);
	}

	public Image getImage() {
		return image.getImage();
	}
}
