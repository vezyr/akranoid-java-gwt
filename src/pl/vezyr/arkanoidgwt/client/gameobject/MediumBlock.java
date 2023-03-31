package pl.vezyr.arkanoidgwt.client.gameobject;

import com.google.gwt.user.client.ui.Image;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

/**
 * Medium block that has 2 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class MediumBlock extends BaseBlock {

	private Image imageDamaged;
	
	public MediumBlock(Vector2<Integer> position) {
		super(position, ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_NORMAL), 2);
		imageDamaged = ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_DAMAGED);
	}

	@Override
	public Image getImage() {
		switch(currentHealth()) {
			case 1: 
				return imageDamaged;
			default:
				return super.getImage();
		}
	}
}
