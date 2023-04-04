package pl.vezyr.arkanoidgwt.client.data.config;

import java.util.List;

import pl.vezyr.arkanoidgwt.client.exception.InvalidLevelDefinition;
import pl.vezyr.arkanoidgwt.client.gameobject.BaseBlock;

/**
 * Data container class for level definition.
 * It's simple config, where you can define 
 * number of block's rows and type of block on each row.
 * @author vezyr
 *
 */
public class LevelDefinition {

	private static final String EXCEPTION_TEXT = "Level definition invalid. Defined number of rows "
			+ "is different than size of list containing block types.";
	
	private final int numberOfRows;
	private final List<Class<? extends BaseBlock>> blockTypeOnRows;
	
	public LevelDefinition(int numberOfRows, List<Class<? extends BaseBlock>> blockTypeOnRows) {
		this.numberOfRows = numberOfRows;
		this.blockTypeOnRows = blockTypeOnRows;
		
		checkDefinition();
	}

	public int getNumberOfRows() {
		checkDefinition();
		return numberOfRows;
	}

	public List<Class<? extends BaseBlock>> getBlockTypeOnRows() {
		checkDefinition();
		return blockTypeOnRows;
	}
	
	private void checkDefinition() {
		if (numberOfRows != blockTypeOnRows.size()) {
			throw new InvalidLevelDefinition(EXCEPTION_TEXT);
		}
	}
}
