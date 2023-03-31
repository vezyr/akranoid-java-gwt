package pl.vezyr.arkanoidgwt.client.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.MouseMoveHandler;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobject.Ball;
import pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.Destroyable;
import pl.vezyr.arkanoidgwt.client.gameobject.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobject.MediumBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.Paddle;
import pl.vezyr.arkanoidgwt.client.gameobject.StrongBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.WeakBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.BoxCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CircleCollider;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.Collidable;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionChecker;
import pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionResult;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseGameplayInputHandler;

/**
 * Main gameplay manager.
 * Handles all operations of the gameplay,
 * ie. invokes InputManager's methods to handle input,
 * retrives object and invokes collision check on them,
 * invokes redraw on canvas etc.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionChecker
 */
public class GameplayManager implements CollisionChecker {

	private CanvasManager canvasManager;
	private Ball ball;
	private Paddle paddle;
	
	private List<GameObject> dynamicObjects;
	
	private final static Logger logger = Logger.getLogger(GameplayManager.class.getName());
	
	/**
	 * Contructor of the GameplayManager with reference to the CanvasManager as parameter.
	 * Constructs all the dynamic objects. These objects will be later controlled by this 
	 * manager and passed to the CanvasManager to draw.
	 * @param canvasManager Reference to the CanvasManager.
	 */
	public GameplayManager(CanvasManager canvasManager) {
		this.canvasManager = canvasManager;
		
		ball = new Ball(new Vector2<Integer>(700, 700), ImagesPool.getImage(ImagesPool.IMAGE_BALL));
		paddle = new Paddle(new Vector2<Integer>(640, 720), ImagesPool.getImage(ImagesPool.IMAGE_PADDLE));
		
		dynamicObjects = new ArrayList<GameObject>(Arrays.asList(ball, paddle));
		
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 5; j++) {
				Vector2<Integer> pos = new Vector2<Integer>(50 + (i * 64), 60 + (j * 32));
				BaseBlock blockToAdd;
				if (j == 0 || j == 4) {
					blockToAdd = new WeakBlock(pos);
				} else if (j == 1 || j == 3) {
					blockToAdd = new MediumBlock(pos);
				} else {
					blockToAdd = new StrongBlock(pos);
				}
				dynamicObjects.add(blockToAdd);
			}
		}
	}
	
	/**
	 * Entry point of the gameplay.
	 * Initialize any additional objects and run main game loop.
	 */
	public void run() {
		MouseGameplayInputHandler mouseInput = new MouseGameplayInputHandler();
		canvasManager.getCurrentLoadedCanvas().getCanvas().addMouseMoveHandler((MouseMoveHandler) mouseInput.getHandler());
		
		ball.getDirection().set((float)Math.sin(Math.toRadians(-45)), (float)Math.sin(Math.toRadians(45)));		
		
		AnimationCallback gameplayAnimationCallback = new AnimationCallback() {
			
			@Override
			public void execute(double timestamp) {
				Canvas canvas = canvasManager.getCurrentLoadedCanvas().getCanvas();
				
				ball.getPosition().setX((int)(ball.getPosition().getX() + ball.getDirection().getX() * 6));
				ball.getPosition().setY((int)(ball.getPosition().getY() + ball.getDirection().getY() * 6));
				
				if (mouseInput.getMousePosition().getX() > 0 && mouseInput.getMousePosition().getX() < canvas.getCoordinateSpaceWidth() - paddle.getImage().getWidth()) {
					paddle.getPosition().setX(mouseInput.getMousePosition().getX());
				}
				
				if(ball.getPosition().getX() >= canvas.getCoordinateSpaceWidth() - ball.getImage().getWidth()) {
					ball.getPosition().setX(canvas.getCoordinateSpaceWidth() - ball.getImage().getWidth() - 1);
					ball.getDirection().setX(ball.getDirection().getX() * -1);
				} else if(ball.getPosition().getX() <= 0) {
					ball.getPosition().setX(1);
					ball.getDirection().setX(ball.getDirection().getX() * -1);
				} 
				
				if(ball.getPosition().getY() <= 0) {
					ball.getPosition().setY(1);
					ball.getDirection().setY(ball.getDirection().getY() * -1);
				}
				
				List<GameObject> objectsToRemove = new ArrayList<GameObject>();
				CollisionResult paddleCollision = checkCollision(ball, (Collidable)paddle);
				ball.handleCollision(paddleCollision);
				
				dynamicObjects.forEach(dynamicObj -> {
					if (dynamicObj != ball && dynamicObj instanceof BaseBlock && dynamicObj instanceof Collidable) {
						CollisionResult collision = checkCollision(ball, (Collidable)dynamicObj);
						ball.handleCollision(collision);
						
						if (dynamicObj instanceof Destroyable && !((Destroyable)dynamicObj).isAlive()) {
							objectsToRemove.add(dynamicObj);
						}
						
						if (collision.isCollided()) {
							return;
						}
					}
				});
				
				dynamicObjects.removeAll(objectsToRemove);
				
				canvasManager.getCurrentLoadedCanvas().redraw(dynamicObjects);
				
				AnimationScheduler.get().requestAnimationFrame(this);
			}
		};
		
		AnimationScheduler.get().requestAnimationFrame(gameplayAnimationCallback);
	}

	@Override
	public CollisionResult checkCollision(Collidable ball, Collidable rect) {
		if(!(ball.getCollider() instanceof CircleCollider) || !(rect.getCollider() instanceof BoxCollider)) {
			return new CollisionResult(false, null, null);
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
		
		boolean collision = distanceBetweenRectAndBall < ((CircleCollider)ball.getCollider()).getRadius();
		if (collision) {
			logger.info("Ball hits " + rect + ". Distance: " + distanceBetweenRectAndBall);
		}
		
		return new CollisionResult(collision, collision ? pointOnRectClosestToBall : null, collision ? rect : null);
	}
	
}
