package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class GameplayBackground extends GameObject {

	public GameplayBackground() {
		super(new Vector2<Integer>(0, 0), ImagesPool.getImage(ImagesPool.IMAGE_BACKGROUND_GAMEPLAY));
	}

}
