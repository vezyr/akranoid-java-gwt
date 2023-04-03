package pl.vezyr.arkanoidgwt.client.manager;

import java.util.List;

import pl.vezyr.arkanoidgwt.client.data.config.DifficultyLevel;
import pl.vezyr.arkanoidgwt.client.data.config.TechnicalConfig;

/**
 * An interface for game's config containing classes.
 * @author vezyr
 *
 */
public interface ConfigManager {

	/**
	 * Loads the config from source, ie. remote config,
	 * file, database etc.
	 */
	public void load();
	
	/**
	 * Gets the list of available difficulty levels. 
	 * @return
	 */
	public List<DifficultyLevel> getDifficultyLevels();
	
	/**
	 * Selects difficulty level.
	 * @param level int The level od difficulty to select.
	 * @return DifficultyLevel Level found in config.
	 * @throws DifficultyLevelNotFound if there is no difficulty config for given level.
	 */
	public DifficultyLevel getDifficutlyLevel(int level);
	
	/**
	 * Selects difficulty level.
	 * @param levelName String The difficulty level's name to select.
	 * @return DifficultyLevel Level found in config.
	 * @throws DifficultyLevelNotFound if there is no difficulty config for given name. 
	 */
	public DifficultyLevel getDifficultyLevel(String levelName);
	
	/**
	 * Gets technical config.
	 * @return TechnicalConfig
	 */
	public TechnicalConfig getTechnicalConfig();
}
