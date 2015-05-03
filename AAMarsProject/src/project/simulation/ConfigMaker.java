package project.simulation;

import java.util.LinkedList;

import com.google.gwt.user.client.Window;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;

/**
 * This class builds a configuration given a module set.
 * @author Rob
 *
 */
public class ConfigMaker {
	LinkedList<Integer> usedModules = new LinkedList<Integer>();
	ModuleSet modset;
	
	
	public ConfigMaker(ModuleSet modset) {
		this.modset = modset;
	}
	
	/**
	 * This method builds an apt minimum configuration if the correct
	 * number of modules are present.
	 */
	public MarsConfiguration genMinimumConfig(ModuleSet modset) {
		MarsConfiguration config = new MarsConfiguration(modset);
		
		int cenX = getCenterOfGravity(true);
		int cenY = getCenterOfGravity(false);
		
		int moveMod = -1;
				
		moveMod = getModuleOfType("Plain");
		config.setXCoord(moveMod, cenX);
		config.setYCoord(moveMod, cenY);
		
		moveMod = getModuleOfType("Plain");
		config.setXCoord(moveMod, cenX - 1);
		config.setYCoord(moveMod, cenY);
		
		moveMod = getModuleOfType("Plain");
		config.setXCoord(moveMod, cenX + 1);
		config.setYCoord(moveMod, cenY);
		
		moveMod = getModuleOfType("Canteen");
		config.setXCoord(moveMod, cenX - 1);
		config.setYCoord(moveMod, cenY + 1);
		
		moveMod = getModuleOfType("Power");
		config.setXCoord(moveMod, cenX);
		config.setYCoord(moveMod, cenY + 1);
		
		moveMod = getModuleOfType("Control");
		config.setXCoord(moveMod, cenX + 1);
		config.setYCoord(moveMod, cenY + 1);
		
		moveMod = getModuleOfType("Dormitory");
		config.setXCoord(moveMod, cenX + 2);
		config.setYCoord(moveMod, cenY);
		
		moveMod = getModuleOfType("Sanitation");
		config.setXCoord(moveMod, cenX + 1);
		config.setYCoord(moveMod, cenY - 1);
		
		moveMod = getModuleOfType("Medical");
		config.setXCoord(moveMod, cenX);
		config.setYCoord(moveMod, cenY - 1);
		
		moveMod = getModuleOfType("Airlock");
		config.setXCoord(moveMod, cenX - 1);
		config.setYCoord(moveMod, cenY - 1);
		
		moveMod = getModuleOfType("Food & Water");
		config.setXCoord(moveMod, cenX - 2);
		config.setYCoord(moveMod, cenY);
		
		
		return config;
	}
	
	/**
	 * Method returns an appropriate module index.
	 */
	private Integer getModuleOfType(String type) {
		boolean found = false;
		int index = -1;
		int i = 0;
		
		while(!found && i < modset.getCount("all")) {
			
			if (usedModules.contains(i)) {
				
			} 
			else {
				//module not used already, check the type.
				if(modset.getModule(i).getID().equals(type)) {
					found = true;
					index = i;
				}
				else {
					i++;
				}
			}
		}
		
		Window.alert("" + index);
		return index;
	}
	
	/**
	 * Method returns the center of gravity.
	 * @param isX - True if the user requires the X center, false
	 * for the Y center.
	 */
	private Integer getCenterOfGravity(boolean isX) {
		int center = 0;
		int sum = 0;
		
		if (isX) {
			for(int i = 0; i < modset.getCount("all"); i++) {
				sum += modset.getModule(i).getX();
				
			}
		} else {
			for(int i = 0; i < modset.getCount("all"); i++) {
				sum += modset.getModule(i).getY();		
			}
		}
		
		center = sum / modset.getCount("all");

		return center;
	}
 }
