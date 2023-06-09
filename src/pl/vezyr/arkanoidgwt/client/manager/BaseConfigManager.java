package pl.vezyr.arkanoidgwt.client.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.vezyr.arkanoidgwt.client.data.config.BlockDefinition;
import pl.vezyr.arkanoidgwt.client.data.config.BlockType;
import pl.vezyr.arkanoidgwt.client.data.config.DifficultyLevel;
import pl.vezyr.arkanoidgwt.client.data.config.LevelDefinition;
import pl.vezyr.arkanoidgwt.client.data.config.TechnicalConfig;
import pl.vezyr.arkanoidgwt.client.exception.DifficultyLevelNotFound;

public abstract class BaseConfigManager implements ConfigManager {

	protected List<DifficultyLevel> difficultyLevels;
	protected TechnicalConfig technicalConfig;
	protected LevelDefinition levelDefinition;
	protected Map<BlockType, BlockDefinition> blocksDefinitions;
	
	public BaseConfigManager() {
		difficultyLevels = new ArrayList<DifficultyLevel>();
		blocksDefinitions = new HashMap<BlockType, BlockDefinition>();
	}
	
	@Override
	public List<DifficultyLevel> getDifficultyLevels() {
		// Return the copy of the original list
		// just to be sure nobody modify the original config.
		// Unfortunately GWT dosn't support List.copyOf() method.
		return new ArrayList<DifficultyLevel>(difficultyLevels);
	}
	
	@Override
	public DifficultyLevel getDifficutlyLevel(int level) {
		for(DifficultyLevel l : difficultyLevels) {
			if (l.getLevel() == level) {
				return l;
			}
		}
		throw new DifficultyLevelNotFound("Difficulty level " + level + " not found.");
	}
	
	@Override
	public DifficultyLevel getDifficultyLevel(String levelName) {
		for(DifficultyLevel l : difficultyLevels) {
			if (l.getLevelName().equalsIgnoreCase(levelName)) {
				return l;
			}
		}
		throw new DifficultyLevelNotFound("Difficulty level " + levelName + " not found.");
	}

	@Override
	public TechnicalConfig getTechnicalConfig() {
		return technicalConfig;
	}
	
	@Override
	public LevelDefinition getLevelDefinition() {
		return levelDefinition;
	}
	
	@Override
	public Map<BlockType, BlockDefinition> getBlocksDefinitions() {
		return blocksDefinitions;
	}
}
