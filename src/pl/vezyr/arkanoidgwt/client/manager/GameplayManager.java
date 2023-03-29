package pl.vezyr.arkanoidgwt.client.manager;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.MouseMoveHandler;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobjects.Ball;
import pl.vezyr.arkanoidgwt.client.gameobjects.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobjects.Paddle;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.CircleCollider;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.CollisionChecker;
import pl.vezyr.arkanoidgwt.client.gameobjects.collisions.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseGameplayInputManager;

public class GameplayManager implements CollisionChecker {

	private CanvasManager canvasManager;
	private Ball ball;
	private Paddle paddle;
	
	private List<GameObject> dynamicObjects;
	
	private final static Logger logger = Logger.getLogger(GameplayManager.class.getName());
	
	public GameplayManager(CanvasManager canvasManager) {
		this.canvasManager = canvasManager;
		
		ball = new Ball(new Vector2<Integer>(40, 40), ImagesPool.getImage(ImagesPool.IMAGE_BALL));
		paddle = new Paddle(new Vector2<Integer>(640, 720), ImagesPool.getImage(ImagesPool.IMAGE_PADDLE));
		
		dynamicObjects = Arrays.asList(ball, paddle);
	}
	
	public void run() {
		MouseGameplayInputManager mouseInput = new MouseGameplayInputManager();
		canvasManager.getCurrentLoadedCanvas().getCanvas().addMouseMoveHandler((MouseMoveHandler) mouseInput.getHandler());
		
		ball.getDirection().set(1.0f, 1.0f);		
		
		AnimationCallback gameplayAnimationCallback = new AnimationCallback() {
			
			@Override
			public void execute(double timestamp) {
				Canvas canvas = canvasManager.getCurrentLoadedCanvas().getCanvas();
				
				if(ball.getPosition().getX() >= canvas.getCoordinateSpaceWidth() - ball.getImage().getWidth()) {
					ball.getDirection().setX(ball.getDirection().getX() * -1);
				} else if(ball.getPosition().getX() <= 0) {
					ball.getDirection().setX(ball.getDirection().getX() * -1);
				} 
				
				CollisionResult collision = checkCollision(ball, paddle);
				if(ball.getPosition().getY() >= canvas.getCoordinateSpaceHeight() - ball.getImage().getHeight()) {
					ball.getDirection().setY(ball.getDirection().getY() * -1);
				} else if(ball.getPosition().getY() <= 0) {
					ball.getDirection().setY(ball.getDirection().getY() * -1);
				} 
				
				if (collision.isCollided()) {
					if (collision.getHitPoint().getX() == paddle.getCollider().getCenter().getX()) {
						ball.getDirection().setX(0.0f);
					} else {
						float angle = ((collision.getHitPoint().getX() - paddle.getCollider().getCenter().getX()) / paddle.getCollider().getHalfOfSize().getX()) * 60;
						ball.getDirection().set((float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(Math.abs(angle))) * -1);
					}
				} 
				
				ball.getPosition().setX((int)(ball.getPosition().getX() + ball.getDirection().getX() * 5));
				ball.getPosition().setY((int)(ball.getPosition().getY() + ball.getDirection().getY() * 5));
				
				if (mouseInput.getMousePosition().getX() > 0 && mouseInput.getMousePosition().getX() < canvas.getCoordinateSpaceWidth() - paddle.getImage().getWidth()) {
					paddle.getPosition().setX(mouseInput.getMousePosition().getX());
				}
				
				canvasManager.getCurrentLoadedCanvas().redraw(dynamicObjects);
				
				AnimationScheduler.get().requestAnimationFrame(this);
			}
		};
		
		AnimationScheduler.get().requestAnimationFrame(gameplayAnimationCallback);
	}

	@Override
	public CollisionResult checkCollision(Collidable ball, Collidable rect) {
		if(!(ball.getCollider() instanceof CircleCollider) || !(rect.getCollider() instanceof BoxCollider)) {
			return new CollisionResult(false, null);
		}
		
		Vector2<Float> centersDiff = new Vector2<Float>(
			ball.getCollider().getCenter().getX() - rect.getCollider().getCenter().getX(),
			ball.getCollider().getCenter().getY() - rect.getCollider().getCenter().getY()
		);
		
		Vector2<Float> clampedDiff = new Vector2<Float>(
			Math.max(-rect.getCollider().getHalfOfSize().getX(), Math.min(rect.getCollider().getHalfOfSize().getX(), centersDiff.getX())),
			Math.max(-rect.getCollider().getHalfOfSize().getY(), Math.min(rect.getCollider().getHalfOfSize().getY(), centersDiff.getY()))
		);
		
		Vector2<Float> pointOnRectClosestToBall = new Vector2<Float>(
			rect.getCollider().getCenter().getX() + clampedDiff.getX(),
			rect.getCollider().getCenter().getY() + clampedDiff.getY()	
		);

		double distanceBetweenRectAndBall = Math.sqrt(
			Math.pow(pointOnRectClosestToBall.getX() - ball.getCollider().getCenter().getX(), 2) +
			Math.pow(pointOnRectClosestToBall.getY() - ball.getCollider().getCenter().getY(), 2)
		);
		
		//logger.info("Distance: " + distanceBetweenRectAndBall);
		boolean collision = distanceBetweenRectAndBall < ((CircleCollider)ball.getCollider()).getRadius();
		
		return new CollisionResult(collision, collision ? pointOnRectClosestToBall : null);
	}
	
}
