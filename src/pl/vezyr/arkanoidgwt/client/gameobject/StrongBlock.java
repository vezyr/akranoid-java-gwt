package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Medium block that has 3 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class StrongBlock extends BaseBlock {

	private Image imageLightDamage;
	private Image imageHeavyDamage;
	
	public StrongBlock(Vector2<Integer> position) {
		super(position, ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_NORMAL), 3);
		imageLightDamage = ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_LIGHT_DAMAGE);
		imageHeavyDamage = ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_HEAVY_DAMAGE);
	}

	@Override
	public Image getImage() {
		switch(currentHealth()) {
			case 1: 
				return imageHeavyDamage;
			case 2:
				return imageLightDamage;
			default:
				return super.getImage();
		}
	}
}
