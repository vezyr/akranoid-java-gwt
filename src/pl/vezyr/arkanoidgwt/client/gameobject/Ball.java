package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.component.ImageComponent;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CircleCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;
import pl.vezyr.arkanoidgwt.client.manager.GameplayManager;
import pl.vezyr.arkanoidgwt.client.manager.GameplayState;

/**
 * A Ball Game Object.
 * Inherits from base GameObject and adds direction of ball movement.
 * Also adds CircleCollider to the ball to detect collisions between ball and other elements (paddle, blocks).
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.GameObject
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable
 */
public class Ball extends GameObject implements Collidable {

	private Vector2<Float> direction;
	private CircleCollider collider;
	private ImageComponent image;
	
	public Ball(Vector2<Integer> position, Image image) {
		super(position, new Vector2<Integer>(image.getWidth(), image.getHeight()));
		this.image = new ImageComponent(this, image);
		direction = new Vector2<Float>();
		collider = new CircleCollider(this, this.image.getSize(), this.image.getWidth() / 2);
	}

	/**
	 * Returns the direction of ball movement as a (-1, 1) Vector.
	 * Returned Vector represents the direction based on circle. 
	 * Ie. ball moving left will return (-1, 0) as a direction.
	 * Ball moving upper left (45') will return (sin(45'), cos(45')).
	 * @return Vector2<Float> representing the direction of ball movement.
	 */
	public Vector2<Float> getDirection() {
		return direction;
	}
	
	@Override
	public Collider getCollider() {
		return collider;
	}
	
	@Override
	public void handleCollision(CollisionResult collision) {
		if (collision.isCollided()) {
			if (collision.getOtherObject() instanceof Paddle) {
				handleCollisionWithPaddle(collision, (Paddle)collision.getOtherObject());
			} else if (collision.getOtherObject() instanceof BaseBlock) {
				handleCollisionWithBlock(collision, (BaseBlock)collision.getOtherObject());
			}
		}
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
				handleOnReadyToStart(gameplayManager);
			break;
			case IN_PROGRESS:
				handleOnInProgress(gameplayManager);
			break;
		}
	}
	
	@Override
	public void draw(Context2d context) {
		image.draw(context);
	}
	
	private void handleOnReadyToStart(GameplayManager gameplayManager) {
		Paddle paddle = gameplayManager.getPaddle();
		getPosition().set(
			paddle.getPosition().getX() + (paddle.getImage().getWidth() / 2) - (this.getImage().getWidth() / 2), 
			paddle.getPosition().getY() - this.getImage().getHeight()
		);
	}
	
	private void handleOnInProgress(GameplayManager gameplayManager) {
		// Sets new position
		this.getPosition().setX((int)(this.getPosition().getX() + this.getDirection().getX() * gameplayManager.getDifficulty().getBallSpeed()));
		this.getPosition().setY((int)(this.getPosition().getY() + this.getDirection().getY() * gameplayManager.getDifficulty().getBallSpeed()));
		
		// Checks and corrects the position if ball is going outside the Canvas.
		if(this.getPosition().getX() >= GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceWidth() - this.getImage().getWidth()) {
			this.getPosition().setX(GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceWidth() - this.getImage().getWidth() - 1);
			this.getDirection().setX(this.getDirection().getX() * -1);
		} else if(this.getPosition().getX() <= 0) {
			this.getPosition().setX(1);
			this.getDirection().setX(this.getDirection().getX() * -1);
		} 
		if(this.getPosition().getY() <= 0) {
			this.getPosition().setY(1);
			this.getDirection().setY(this.getDirection().getY() * -1);
		}
		
		// Changes the state to LOST_LIVE if ball went down outside the Canvas
		if (this.getPosition().getY() >= GameManager.getCanvasManager().getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceHeight()) {
			gameplayManager.changeState(GameplayState.LOST_LIVE);
		}
	}
	
	/**
	 * Handle the collision between ball and a paddle. 
	 * As a result the new direction of the ball is set.
	 * New direction is calculated base on hit point on the paddle and vary from -60 degree to 60 degree. 
	 * @param collision CollisionResult 
	 * @param paddle Paddle
	 */
	private void handleCollisionWithPaddle(CollisionResult collision, Paddle paddle) {
		if (collision.getOtherObjHitPoint().getX() == paddle.getCollider().getCenter().getX()) {
			getDirection().setX(0.0f);
		} else {
			float angle = ((collision.getOtherObjHitPoint().getX() - paddle.getCollider().getCenter().getX()) / paddle.getCollider().getHalfOfSize().getX()) * 60;
			getDirection().set((float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(Math.abs(angle))) * -1);
		}
	}
	
	/**
	 * Handle the collision between ball and a block.
	 * As a result:
	 * - the hit block takes damage;
	 * - the new direction of the ball is set based on the side of the block that ball hits 
	 *   (ie. if ball moves left and hits right side of the block, the ball will be bounced from block and starts moving right).
	 * @param collision CollisionResult
	 * @param block Block that was hit
	 */
	private void handleCollisionWithBlock(CollisionResult collision, BaseBlock block) {
		block.takeDamage(1);
		Vector2<Float> hitPoint = collision.getOtherObjHitPoint();
		
		double correctionDistance = Math.sqrt(
			Math.pow(hitPoint.getX() - getCollider().getCenter().getX(), 2) +
			Math.pow(hitPoint.getY() - getCollider().getCenter().getY(), 2)
		) - ((CircleCollider)getCollider()).getRadius();
		
		getPosition().set((int)(getPosition().getX() + (correctionDistance * getDirection().getX())), (int)(getPosition().getY() + (correctionDistance * getDirection().getY())));
		
		if (hitPoint.getX() == (float)block.getPosition().getX() || hitPoint.getX() == (float)block.getPosition().getX() + block.getSize().getX()) {
			getDirection().setX(getDirection().getX() * -1);
		} else if (hitPoint.getY() == (float)block.getPosition().getY() || hitPoint.getY() == (float)block.getPosition().getY() + block.getSize().getY()) {
			getDirection().setY(getDirection().getY() * -1);
		}
	}

	public Image getImage() {
		return image.getImage();
	}
}
