package project.simulation;

import java.util.LinkedList;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;

/**
 * This class contains methods for calculating the quality of
 * a configurations
 * @author Rob
 *
 */
public class QualityChecker {
	
	/**
	 * This method cannot be instantiated.
	 */
	private QualityChecker() {
		
	}
	
	public static int getConfigQuality(MarsConfiguration config, ModuleSet modset) {
		int quality = 100;
		
		//Hard coded failed configurations
		
		//No Power
		if(config.getConfigTypeCount("Power") == 0) {
			return 0;
		}
		
		//No control
		if(config.getConfigTypeCount("Control") == 0) {
			return 0;
		}
		
		//No airlock
		if(config.getConfigTypeCount("Airlock") == 0) {
			return 0;
		}
		
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			
			if(config.getIsUsed(i) && !modset.getModule(i).getType().equals("Plain")) {
				//Module is in the configuration and not a plain.
				//Find its neighbors.
				LinkedList<Integer> neighbors = getNeighbors(modset,config,i);
				
				//Check for 1) Sanitation is not next to canteen
				//AND Check for 2) Sanitation is not next to Food and Storage
				//AND Check for 8) A Gym and Relaxation module should be next to a sanitation module.
				if(modset.getModule(i).getType().equals("Sanitation")) {
					quality += sanitationRules(modset, neighbors);
				}
				
				//Check for 3) Airlock is not next to dormitory.
				//Check for 9) Airlock is next to medical
				if(modset.getModule(i).getType().equals("Airlock")) {
					quality += airlockRules(modset, neighbors);
				}
				
				
			}
		}
			
		
		
		
		
		
		
		return quality;
	}
	
	
	private static LinkedList<Integer> getNeighbors(ModuleSet modset, MarsConfiguration config, int index) {
		LinkedList<Integer> neighbors = new LinkedList<Integer>();
		
		int xCoo = config.getXCoord(index);
		int yCoo = config.getYCoord(index);
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			
			if(config.getIsUsed(i) && !modset.getModule(i).getType().equals("Plain")) {
				//Module is in configuration and not a plain
				if ((Math.abs(config.getXCoord(i) - xCoo) == 1) && (Math.abs(config.getYCoord(i) - yCoo) == 0)) {
					//Module is horizontally "next to" index
					neighbors.add(i);
				}
				
				if ((Math.abs(config.getXCoord(i) - xCoo) == 0) && (Math.abs(config.getYCoord(i) - yCoo) == 1)) {
					//Module is vertically "next to" index
					neighbors.add(i);
				}
				
				if ((Math.abs(config.getXCoord(i) - xCoo) == 1) && (Math.abs(config.getYCoord(i) - yCoo) == 1)) {
					//Module is diagonally "next to" index
					neighbors.add(i);
				}
				
			}
		}
		
		return neighbors;
	}
	
	private static int sanitationRules(ModuleSet modset, LinkedList<Integer> neighbors) {
		int adjust = 0;
		
		//Check for 1) Sanitation is not next to canteen
		for(int i = 0; i < neighbors.size(); i++) {
			if(modset.getModule(neighbors.get(i)).getType().equals("Canteen")) {
				adjust -= 5;
			}
		}
		
		//Check for 2) Sanitation is not next to Food and Storage
		for(int i = 0; i < neighbors.size(); i++) {
			if(modset.getModule(neighbors.get(i)).getType().equals("Food & Water")) {
				adjust -= 5;
			}
		}
		
		//Check for 8) A Gym and Relaxation module should be next to a sanitation module.
		for(int i = 0; i < neighbors.size(); i++) {
			boolean isFound = false;
			
			if(modset.getModule(neighbors.get(i)).getType().equals("Gym & Relaxation")) {
				isFound = true;
			}
			
			if(!isFound) {
				adjust -= 5;
			}
		}
		
		return adjust = 0;
	}
	
	private static int airlockRules(ModuleSet modset, LinkedList<Integer> neighbors) {
		int adjust = 0;
		
		//Check for 3) Airlock is not next to Dormitory
		for(int i = 0; i < neighbors.size(); i++) {
			if(modset.getModule(neighbors.get(i)).getType().equals("Dormitory")) {
				adjust -= 5;
			}
		}
		
		//Check for 9) Airlock is next to medical.
		for(int i = 0; i < neighbors.size(); i++) {
			boolean isFound = false;
			
			if(modset.getModule(neighbors.get(i)).getType().equals("Medical")) {
				isFound = true;
			}
			
			if(!isFound) {
				adjust -= 5;
			}
		}
		
		return adjust;
	}
	
	
	
	
}
