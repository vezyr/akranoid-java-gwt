package pl.vezyr.arkanoidgwt.client.gameobject;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CircleCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class Ball extends GameObject implements Collidable {

	private Vector2<Float> direction;
	private CircleCollider collider;
	
	private static final Logger logger = Logger.getLogger(Ball.class.getName());
	
	public Ball(Vector2<Integer> position, Image image) {
		super(position, image);
		direction = new Vector2<Float>();
		collider = new CircleCollider(this);
	}

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
			logger.info("Collision with " + collision.getOtherObject());
			if (collision.getOtherObject() instanceof Paddle) {
				handleCollisionWithPaddle(collision, (Paddle)collision.getOtherObject());
			} else if (collision.getOtherObject() instanceof BaseBlock) {
				handleCollisionWithBlock(collision, (BaseBlock)collision.getOtherObject());
			}
		}
	}
	
	private void handleCollisionWithPaddle(CollisionResult collision, Paddle paddle) {
		if (collision.getOtherObjHitPoint().getX() == paddle.getCollider().getCenter().getX()) {
			getDirection().setX(0.0f);
		} else {
			float angle = ((collision.getOtherObjHitPoint().getX() - paddle.getCollider().getCenter().getX()) / paddle.getCollider().getHalfOfSize().getX()) * 60;
			getDirection().set((float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(Math.abs(angle))) * -1);
		}
	}
	
	private void handleCollisionWithBlock(CollisionResult collision, BaseBlock block) {
		block.takeDamage(1);
		Vector2<Float> hitPoint = collision.getOtherObjHitPoint();
		if (hitPoint.getX() == (float)block.getPosition().getX() || hitPoint.getX() == (float)block.getPosition().getX() + block.getImage().getWidth()) {
			getDirection().setX(getDirection().getX() * -1);
		} else if (hitPoint.getY() == (float)block.getPosition().getY() || hitPoint.getY() == (float)block.getPosition().getY() + block.getImage().getHeight()) {
			getDirection().setY(getDirection().getY() * -1);
		}
	}
}
