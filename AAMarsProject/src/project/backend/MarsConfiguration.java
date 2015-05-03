package project.backend;

import java.util.LinkedList;


/**
 * This class contains a list of modules in different positions.
 * @author Rob
 *
 */
public class MarsConfiguration {
	//Define private variables.
	private ModuleSet modset;
	private LinkedList<Integer> xCoords = new LinkedList<Integer>();
	private LinkedList<Integer> yCoords = new LinkedList<Integer>();
	
	/**
	 * Constructor for configuration.
	 * @param modset
	 */
	public MarsConfiguration(ModuleSet modset) {
		this.modset = modset;
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			xCoords.add(modset.getModule(i).getX());
			yCoords.add(modset.getModule(i).getY());
		}
	}
	
	
//	/**
//	 * Method used to move a module within an individual config.
//	 */
//	public void setInitialCoords(int index, int xcoo, int ycoo) {
//		modlist.get(index).setX(xcoo);
//		modlist.get(index).setY(ycoo);
//	}
//	
	public Integer getXCoord(int i) {
		return xCoords.get(i);
	}
	
	public Integer getYCoord(int i) {
		return yCoords.get(i);
	}
	
	public void setXCoord(int index, int xcoord) {
		xCoords.set(index, xcoord);
	}
	
	public void setYCoord(int index, int ycoord) {
		yCoords.set(index, ycoord);
	}
	
	public MarsModule getModule(int i) {
		MarsModule mod = new MarsModule();
		mod.setCondition(modset.getModule(i).getCondition());
		mod.setID(modset.getModule(i).getID());
		mod.setOrientation(modset.getModule(i).getOrientation());
		mod.setX(xCoords.get(i));
		mod.setY(yCoords.get(i));
		
		return mod;
	}
	
	
}
