package pl.vezyr.arkanoidgwt.client.manager;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.MouseMoveHandler;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.gameobjects.Ball;
import pl.vezyr.arkanoidgwt.client.gameobjects.GameObject;
import pl.vezyr.arkanoidgwt.client.gameobjects.Paddle;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.input.MouseGameplayInputManager;

public class GameplayManager {

	private CanvasManager canvasManager;
	private Ball ball;
	private Paddle paddle;
	
	private List<GameObject> dynamicObjects;
	
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
				
				if(ball.getPosition().getY() >= canvas.getCoordinateSpaceHeight() - ball.getImage().getHeight()) {
					ball.getDirection().setY(ball.getDirection().getY() * -1);
				} else if(ball.getPosition().getY() <= 0) {
					ball.getDirection().setY(ball.getDirection().getY() * -1);
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
	
}
