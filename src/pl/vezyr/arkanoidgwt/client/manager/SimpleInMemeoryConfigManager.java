package pl.vezyr.arkanoidgwt.client.manager;

import java.util.Comparator;

import pl.vezyr.arkanoidgwt.client.data.config.DifficultyLevel;
import pl.vezyr.arkanoidgwt.client.data.config.TechnicalConfig;
import pl.vezyr.arkanoidgwt.client.helper.Vector2;

public class SimpleInMemeoryConfigManager extends BaseConfigManager {
	
	@Override
	public void load() {
		technicalConfig = new TechnicalConfig(new Vector2<Integer>(1280, 800));
		
		difficultyLevels.add(new DifficultyLevel(1, "The easiest", 4, 6 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(2, "Easy", 5, 5 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(3, "Medium", 6, 4 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(4, "Hard", 7, 3 * 60 * 1000));
		difficultyLevels.add(new DifficultyLevel(5, "The Hardest", 8, 5 * 1000));
		
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
	}
}
