package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.config.BlockType;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

/**
 * Medium block that has 2 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class MediumBlock extends BaseBlock {
	
	public MediumBlock(Vector2<Integer> position) {
		super(
				position, 
				ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_NORMAL), 
				GameManager.getConfigManager().getBlocksDefinitions().get(BlockType.MEDIUM).getHealthPoints()
		);
	}
	
	@Override
	public void takeDamage(int attackStrength) {
		super.takeDamage(attackStrength);
		
		float currentHealth = currentHealthNormlized();
		if (currentHealth < 0.51) {
			getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_DAMAGED));
			return;
		} else {
			getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_MEDIUM_NORMAL));
		}
	}
}
