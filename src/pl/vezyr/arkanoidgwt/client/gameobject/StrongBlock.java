package pl.vezyr.arkanoidgwt.client.gameobject;

import pl.vezyr.arkanoidgwt.client.ImagesPool;
import pl.vezyr.arkanoidgwt.client.data.config.BlockType;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;
import pl.vezyr.arkanoidgwt.client.manager.GameManager;

/**
 * Medium block that has 3 health points.
 * @author vezyr
 * @see pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock
 */
public class StrongBlock extends BaseBlock {
	
	public StrongBlock(Vector2<Integer> position) {
		super(
				position, 
				ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_NORMAL), 
				GameManager.getConfigManager().getBlocksDefinitions().get(BlockType.STRONG).getHealthPoints()
		);
	}
	
	@Override
	public void takeDamage(int attackStrength) {
		super.takeDamage(attackStrength);
		
		float currentHealth = currentHealthNormlized();
		if (currentHealth < 0.34) {
			getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_HEAVY_DAMAGE));
			return;
		} else if (currentHealth < 0.67) {
			getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_LIGHT_DAMAGE));
			return;
		} else {
			getImageComponent().setImage(ImagesPool.getImage(ImagesPool.IMAGE_BLOCK_STRONG_NORMAL));
		}
	}
}
