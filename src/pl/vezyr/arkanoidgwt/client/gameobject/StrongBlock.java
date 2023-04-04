package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Medium block that has 3 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class StrongBlock extends BaseBlock {
	
	public StrongBlock(Vector2<Integer> position) {
		super(position, ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_NORMAL), 3);
	}
	
	@Override
	public void takeDamage(int attackStrength) {
		super.takeDamage(attackStrength);
		switch(currentHealth()) {
			case 1:
				getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_HEAVY_DAMAGE));
				break;
			case 2:
				getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_LIGHT_DAMAGE));
				break;
			default:
				getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_NORMAL));
				break;
		}
	}
}
