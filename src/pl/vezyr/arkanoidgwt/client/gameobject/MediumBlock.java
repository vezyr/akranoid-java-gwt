package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class MediumBlock extends BaseBlock {

	public MediumBlock(Vector2<Integer> position) {
		super(position, ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM), 2);
	}

}
