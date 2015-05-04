package project.simulation;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;

/**
 * This class takes a configuration and tries to find the statistics wrapped
 * around it.
 * @author Rob
 *
 */
public class ConfigStats {

	ModuleSet modset;
	MarsConfiguration config;
	
	/**
	 * Constructor
	 */
	public ConfigStats(ModuleSet modset, MarsConfiguration config) {
		this.modset = modset;
		this.config = config;
	}
	
	/**
	 * Method returns the total sized move for one module.
	 */
	public int moduleMove(int index) {
		int moves = 0;
		
		int oldX = modset.getModule(index).getX();
		int newX = config.getXCoord(index);
		int oldY = modset.getModule(index).getY();
		int newY = config.getYCoord(index);
		
		moves += Math.abs(oldX - newX);
		moves += Math.abs(oldY - newY);
		
		return moves;
	}
	
}
