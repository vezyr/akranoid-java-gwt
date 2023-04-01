package pl.vezyr.arkanoidgwt.client.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.KeyCodes;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.GameplayUiData;
import pl.vezyr.arkanoidgwt.client.data.PlayerData;
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
import pl.vezyr.arkanoidgwt.client.manager.input.GameplayInputManager;
import pl.vezyr.arkanoidgwt.client.manager.input.InputManager;

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

	private static final long DEFAULT_TIME_LIMIT = 30*1000;
	
	private CanvasManager canvasManager;
	private InputManager inputManager;
	
	private Ball ball;
	private Paddle paddle;
	private List<GameObject> dynamicObjects;
	
	private double lastFrameTimestamp = 0;
	private long gameStartTimestamp;
	private long remainingTime;
	private GameplayState state;
	private PlayerData playerData;
	
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
		this.inputManager = new GameplayInputManager(this.canvasManager.getCurrentLoadedCanvas());
		inputManager.registerHandlers();
		
		gameStartTimestamp = (new Date()).getTime();
		lastFrameTimestamp = gameStartTimestamp;
		remainingTime = DEFAULT_TIME_LIMIT;
		playerData = new PlayerData();		
		GameplayUiData uiData = new GameplayUiData();
		
		state = GameplayState.READY_TO_START;
		
		AnimationCallback gameplayAnimationCallback = new AnimationCallback() {
			
			@Override
			public void execute(double timestamp) {
				double deltaTime = timestamp - lastFrameTimestamp;
				
				inputManager.processInput();
				update(deltaTime);
				uiData.updateData(playerData.getNumberOfLives(), remainingTime);
				canvasManager.getCurrentLoadedCanvas().redraw(dynamicObjects, uiData);
				
				
				lastFrameTimestamp = timestamp;
				AnimationScheduler.get().requestAnimationFrame(this);
			}
		};
		
		AnimationScheduler.get().requestAnimationFrame(gameplayAnimationCallback);
	}
	
	private void changeState(GameplayState newState) {
		if(newState == state) {
			return;
		}
		
		switch(state) {
			case READY_TO_START:
				if (newState == GameplayState.IN_PROGRESS) {
					state = newState;
					onStateChangeToInProgress();
				}
			break;
			case IN_PROGRESS:
				if (newState == GameplayState.LOST_LIVE) {
					state = newState;
					onStateChangeToLostLive();
				}
			break;
			case LOST_LIVE:
				switch (newState) {
					case READY_TO_START:
						state = newState;
					break;
					case GAME_LOST:
					break;
				}
			break;
			case GAME_WIN:
				
			break;
			case GAME_LOST:
			break;
		}
	}
	
	private void onStateChangeToInProgress() {
		float xAngle = 0;
		if (inputManager.isKeyPressed(KeyCodes.KEY_LEFT)) {
			xAngle = (float)Math.sin(Math.toRadians(-45));
		} else if (inputManager.isKeyPressed(KeyCodes.KEY_RIGHT)) {
			xAngle = (float)Math.sin(Math.toRadians(45));
		}
		ball.getDirection().set(xAngle, (float)Math.sin(Math.toRadians(-45)));
	}
	
	private void onStateChangeToLostLive() {
		if(playerData.liveLost()) {
			changeState(GameplayState.READY_TO_START);
		} else {
			changeState(GameplayState.GAME_LOST);
		}
	}
	
	private void update(double deltaTime) {
		Canvas canvas = canvasManager.getCurrentLoadedCanvas().getCanvas();
		
		if (remainingTime < 0) {
			changeState(GameplayState.GAME_LOST);
		} else {
			remainingTime -= deltaTime;
		}
		
		if (inputManager.isKeyPressed(KeyCodes.KEY_LEFT)) {
			int newPos = paddle.getPosition().getX() - 5;
			paddle.getPosition().setX(newPos > 0 ? newPos : 1);
		} else if (inputManager.isKeyPressed(KeyCodes.KEY_RIGHT)) {
			int newPos = paddle.getPosition().getX() + 5;
			paddle.getPosition().setX(newPos < canvas.getCoordinateSpaceWidth() - paddle.getImage().getWidth() ? newPos : canvas.getCoordinateSpaceWidth() - paddle.getImage().getWidth());
		} else if (inputManager.hasMouseMoved()) { 
			if (inputManager.getMousePosition().getX() > 0 && inputManager.getMousePosition().getX() < canvas.getCoordinateSpaceWidth() - paddle.getImage().getWidth()) {
				paddle.getPosition().setX(inputManager.getMousePosition().getX());
			}
		}
		
		if (inputManager.isKeyPressed(KeyCodes.KEY_SPACE)) {
			changeState(GameplayState.IN_PROGRESS);
		}
		
		if (state == GameplayState.READY_TO_START) {
			ball.getPosition().set(
				paddle.getPosition().getX() + (paddle.getImage().getWidth() / 2) - (ball.getImage().getWidth() / 2), 
				paddle.getPosition().getY() - ball.getImage().getHeight()
			);
		} else if (state == GameplayState.IN_PROGRESS) {
			ball.getPosition().setX((int)(ball.getPosition().getX() + ball.getDirection().getX() * 6));
			ball.getPosition().setY((int)(ball.getPosition().getY() + ball.getDirection().getY() * 6));
			
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
			
			if (ball.getPosition().getY() >= canvas.getCoordinateSpaceHeight()) {
				changeState(GameplayState.LOST_LIVE);
			}
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
		
		return new CollisionResult(collision, collision ? pointOnRectClosestToBall : null, collision ? rect : null);
	}
	
}
