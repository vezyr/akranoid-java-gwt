package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Medium block that has 2 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class MediumBlock extends BaseBlock {
	
	public MediumBlock(Vector2<Integer> position) {
		super(position, ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_NORMAL), 2);
	}
	
	@Override
	public void takeDamage(int attackStrength) {
		super.takeDamage(attackStrength);
		switch(currentHealth()) {
			case 1:
				getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_DAMAGED));
				break;
			default:
				getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_DAMAGED));
				break;
		}
	}
}
