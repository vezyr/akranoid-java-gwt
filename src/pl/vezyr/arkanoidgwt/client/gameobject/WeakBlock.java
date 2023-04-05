package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.config.BlockType;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

/**
 * Weak block that has only 1 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class WeakBlock extends BaseBlock {

	public WeakBlock(Vector2<Integer> position) {
		super(
				position, 
				ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_WEAK), 
				GameManager.getConfigManager().getBlocksDefinitions().get(BlockType.WEAK).getHealthPoints()
		);
	}

}
