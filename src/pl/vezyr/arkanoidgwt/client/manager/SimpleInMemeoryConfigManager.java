package pl.vezyr.arkanoidgwt.client.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pl.vezyr.arkanoidgwt.client.data.config.BlockDefinition;
import pl.vezyr.arkanoidgwt.client.data.config.BlockType;
import pl.vezyr.arkanoidgwt.client.data.config.DifficultyLevel;
import pl.vezyr.arkanoidgwt.client.data.config.LevelDefinition;
import pl.vezyr.arkanoidgwt.client.data.config.TechnicalConfig;
import pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.MediumBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.StrongBlock;
import pl.vezyr.arkanoidgwt.client.gameobject.WeakBlock;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class SimpleInMemeoryConfigManager extends BaseConfigManager {
	
	@Override
	public void load() {
		technicalConfig = new TechnicalConfig(new Vector2<Integer>(1280, 700));
		
		difficultyLevels.add(new DifficultyLevel(1, "The easiest", 150, 150, 12 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(2, "Easy", 200, 200, 10 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(3, "Medium", 250, 250, 8 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(4, "Hard", 300, 300, 6 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(5, "The Hardest", 350, 350, 4 * 60 * 1000));
		
		// Sort on load - just to show how to manage the level's order.
		difficultyLevels.sort(new Comparator<DifficultyLevel>() {
			@Override
			public int compare(DifficultyLevel o1, DifficultyLevel o2) {
				if(o1.getLevel() < o2.getLevel())
					return -1;
				else if (o1.getLevel() > o2.getLevel())
					return 1;
				else
					return 0;
			}
		});
		
		blocksDefinitions.put(BlockType.WEAK, new BlockDefinition(1));
		blocksDefinitions.put(BlockType.MEDIUM, new BlockDefinition(2));
		blocksDefinitions.put(BlockType.STRONG, new BlockDefinition(3));
		
		List<Class<? extends BaseBlock>> blocks = new ArrayList<Class<? extends BaseBlock>>();
		blocks.add(WeakBlock.class);
		blocks.add(MediumBlock.class);
		blocks.add(StrongBlock.class);
		blocks.add(MediumBlock.class);
		blocks.add(WeakBlock.class);
		
		levelDefinition = new LevelDefinition(blocks.size(), blocks);
	}
}
