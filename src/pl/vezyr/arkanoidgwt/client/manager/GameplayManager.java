package pl.vezyr.arkanoidgwt.client.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyCodes;

import pl.vezyr.arkanoidgwt.client.AudioPool;
import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.GameplayUiData;
import pl.vezyr.arkanoidgwt.client.data.PlayerData;
import pl.vezyr.arkanoidgwt.client.data.config.DifficultyLevel;
import pl.vezyr.arkanoidgwt.client.data.config.LevelDefinition;
import pl.vezyr.arkanoidgwt.client.event.audio.PlaySoundEvent;
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
import pl.vezyr.arkanoidgwt.client.helper.GameplayFocusHandler;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseInputHandler;
import pl.vezyr.arkanoidgwt.client.register.ObjectsRegister;

/**
 * Main gameplay manager.
 * Handles all operations of the gameplay,
 * ie. invokes InputManager's methods to handle input,
 * retrives object and invokes collision check on them,
 * invokes redraw on canvas etc.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.component.collision.CollisionChecker
 */
public class GameplayManager implements SceneManager, CollisionChecker, MouseInputHandler {
	
	private CanvasManager canvasManager;
	
	private Ball ball;
	private Paddle paddle;
	private List<GameObject> dynamicObjects;
	
	private long gameStartTimestamp;
	private long remainingTime;
	private GameplayState state;
	private GameplayState stateBeforePause;
	private PlayerData playerData;
	private GameplayUiData uiData;
	private DifficultyLevel difficulty;
	
	private BlurHandler focusHandler;
	
	/**
	 * Contructor of the GameplayManager with reference to the CanvasManager as parameter.
	 * Constructs all the dynamic objects. These objects will be later controlled by this 
	 * manager and passed to the CanvasManager to draw.
	 * @param canvasManager Reference to the CanvasManager.
	 */
	public GameplayManager(CanvasManager canvasManager) {
		this.canvasManager = canvasManager;
		focusHandler = new GameplayFocusHandler();
	}
	
	/**
	 * Entry point of the gameplay.
	 * Initialize any additional objects and run main game loop.
	 */
	public void run() {
		GameManager.getInputManager().registerHandlers();
		if (dynamicObjects != null) {
			dynamicObjects.clear();
		}
		
		playerData = new PlayerData();
		uiData = new GameplayUiData();
		state = GameplayState.CHOOSE_DIFFICULTY;
		
		canvasManager.getCurrentLoadedCanvas().getCanvas().addBlurHandler(focusHandler);
	}
	
	private void resetState() {
		ball = new Ball(new Vector2<Integer>(700, 700), ImagesPool.getImage(ImagesPool.IMAGE_BALL));
		paddle = new Paddle(
				new Vector2<Integer>(
						canvasManager.getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceWidth() / 2, 
						canvasManager.getCurrentLoadedCanvas().getCanvas().getCoordinateSpaceHeight() - 100), 
				ImagesPool.getImage(ImagesPool.IMAGE_PADDLE)
		);
		
		dynamicObjects = new ArrayList<GameObject>(Arrays.asList(ball, paddle));
		
		LevelDefinition levelDefinition = GameManager.getConfigManager().getLevelDefinition();
		
		for (int i = 0; i < levelDefinition.getNumberOfRows(); i++) {
			BaseBlock blockToAdd;
			
			for (int j = 0; j < 18; j++) {
				Vector2<Integer> pos = new Vector2<Integer>(50 + (j * 64), 60 + (i * 32));
			
				if (levelDefinition.getBlockTypeOnRows().get(i) == WeakBlock.class) {
					blockToAdd = new WeakBlock(pos);
				} else if (levelDefinition.getBlockTypeOnRows().get(i) == MediumBlock.class) {
					blockToAdd = new MediumBlock(pos);
				} else {
					blockToAdd = new StrongBlock(pos);
				}
				
				dynamicObjects.add(blockToAdd);
			}
		}
		
		gameStartTimestamp = (new Date()).getTime();
		remainingTime = difficulty.getTimeLimit();
		playerData = new PlayerData();
	}
	
	/**
	 * Changes the state of the gameplay to the new state.
	 * If the new state is the same as old, no action is performed,
	 * so it's safe to invoke this method multiple times.
	 * @param newState GameplayState New state to set.
	 */
	public void changeState(GameplayState newState) {
		if(newState == state) {
			return;
		}
		
		switch(state) {
			case CHOOSE_DIFFICULTY:
				if (newState == GameplayState.READY_TO_START) {
					state = newState;
					onStateChangeToReadyToStart();
				}
			case READY_TO_START:
				switch (newState) {
					case IN_PROGRESS:
						state = newState;
						onStateChangeToInProgress();
					break;
					case GAME_LOST:
						state = newState;
						ObjectsRegister.unregister(this);
						onStateChangeToGameLost();
					break;
					case GAME_WIN:
						state = newState;
						onStateChangeToGameWon();
					break;
					case GAME_PAUSED:
						stateBeforePause = state;
						state = newState;
					break;
				}
			break;
			case IN_PROGRESS:
				switch (newState) {
					case LOST_LIVE:
						state = newState;
						onStateChangeToLostLive();
					break;
					case GAME_LOST:
						state = newState;
						onStateChangeToGameLost();
					break;
					case GAME_WIN:
						state = newState;
						onStateChangeToGameWon();
					break;
					case GAME_PAUSED:
						stateBeforePause = state;
						state = newState;
					break;
				}
			break;
			case LOST_LIVE:
				switch (newState) {
					case READY_TO_START:
						state = newState;
					break;
					case GAME_LOST:
						state = newState;
						onStateChangeToGameLost();
					break;
				}
			break;
			case GAME_WIN:
				if (newState == GameplayState.READY_TO_START) {
					state = newState;
					onStateChangeToReadyToStart();
				}
			break;
			case GAME_LOST:
				if (newState == GameplayState.READY_TO_START) {
					state = newState;
					onStateChangeToReadyToStart();
				}
			break;
			case GAME_PAUSED:
				switch (newState) {
					case READY_TO_START:
						state = newState;
						stateBeforePause = null;
					break;
					case IN_PROGRESS:
						state = newState;
						stateBeforePause = null;
					break;
				}
			break;
		}
	}
	
	private void onStateChangeToReadyToStart() {
		ObjectsRegister.register(this);
		resetState();
	}
	
	private void onStateChangeToInProgress() {
		float xAngle = 0;
		if (GameManager.getInputManager().isKeyPressed(KeyCodes.KEY_LEFT)) {
			xAngle = -45.0f;
		} else if (GameManager.getInputManager().isKeyPressed(KeyCodes.KEY_RIGHT)) {
			xAngle = 45.0f;
		}
		ball.getDirection().set((float)Math.sin(Math.toRadians(xAngle)), (float)(Math.abs(Math.cos(Math.toRadians(xAngle)) * -1.0f)));
		ObjectsRegister.unregister(this);
	}
	
	private void onStateChangeToLostLive() {
		(new PlaySoundEvent(AudioPool.AUDIO_GAMEPLAY_LIVE_LOST)).fire();
		if(playerData.liveLost()) {
			changeState(GameplayState.READY_TO_START);
			ObjectsRegister.register(this);
		} else {
			changeState(GameplayState.GAME_LOST);
		}
	}
	
	private void onStateChangeToGameWon() {
		(new PlaySoundEvent(AudioPool.AUDIO_GAMEPLAY_GAME_WON)).fire();
	}
	
	private void onStateChangeToGameLost() {
		(new PlaySoundEvent(AudioPool.AUDIO_GAMEPLAY_GAME_LOST)).fire();
	}
	
	/**
	 * Checks condition if game won (all blocks destroyed).
	 */
	private void checkWinCondition() {
		int numberOfBlocks = 0;
		for(GameObject obj : dynamicObjects) {
			if (obj instanceof BaseBlock) numberOfBlocks++;
		}
		if (numberOfBlocks == 0) {
			changeState(GameplayState.GAME_WIN);
		}
	}
	
	@Override
	public void update(double deltaTime) {
		
		if (state == GameplayState.CHOOSE_DIFFICULTY) {
			
		} else if (state == GameplayState.READY_TO_START || state == GameplayState.IN_PROGRESS) {
			if (GameManager.getInputManager().isKeyPressed(KeyCodes.KEY_ESCAPE)) {
				pause();
			}
			
			if (state == GameplayState.READY_TO_START && GameManager.getInputManager().isKeyPressed(KeyCodes.KEY_SPACE)) {
				changeState(GameplayState.IN_PROGRESS);
			}
			
			if (remainingTime < 0) {
				changeState(GameplayState.GAME_LOST);
			} else {
				remainingTime -= deltaTime;
			}
			
			paddle.update(deltaTime);
			ball.update(deltaTime);
			dynamicObjects.forEach(dynamicObj -> dynamicObj.update(deltaTime));
			
			checkCollisions();
			
			checkWinCondition();
		}
	}
	
	@Override
	public void redraw() {
		uiData.updateData(playerData.getNumberOfLives(), remainingTime, state);
		canvasManager.getCurrentLoadedCanvas().redraw(dynamicObjects, uiData);
	}
	
	@Override
	public void checkCollisions() {
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
	
	@Override
	public void handleMouseInput(Vector2<Integer> mousePosition, boolean isLeftButtonPressed,
			boolean isLeftButtonJustReleased) {
		if (state == GameplayState.READY_TO_START && isLeftButtonJustReleased) {
			changeState(GameplayState.IN_PROGRESS);
		}
	}
	
	public void chooseDifficulty(DifficultyLevel level) {
		setDifficutlyLevel(level);
		changeState(GameplayState.READY_TO_START);
	}
	
	public void restart() {
		changeState(GameplayState.READY_TO_START);
	}
	
	public void resume() {
		changeState(stateBeforePause);
	}
	
	public void pause() {
		changeState(GameplayState.GAME_PAUSED);
	}
	
	public GameplayState getState() {
		return state;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public DifficultyLevel getDifficulty() {
		return difficulty;
	}
	
	public void setDifficutlyLevel(DifficultyLevel difficultyLevel) {
		this.difficulty = difficultyLevel;
	}
}
