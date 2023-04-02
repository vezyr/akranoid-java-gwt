package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CircleCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

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
	
	public Ball(Vector2<Integer> position, Image image) {
		super(position, image);
		direction = new Vector2<Float>();
		collider = new CircleCollider(this);
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
		
		if (hitPoint.getX() == (float)block.getPosition().getX() || hitPoint.getX() == (float)block.getPosition().getX() + block.getImage().getWidth()) {
			getDirection().setX(getDirection().getX() * -1);
		} else if (hitPoint.getY() == (float)block.getPosition().getY() || hitPoint.getY() == (float)block.getPosition().getY() + block.getImage().getHeight()) {
			getDirection().setY(getDirection().getY() * -1);
		}
	}
}
