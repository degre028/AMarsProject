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
	private LinkedList<MarsModule> modlist = new LinkedList<MarsModule>();
	private LinkedList<Integer> xCoords = new LinkedList<Integer>();
	private LinkedList<Integer> yCoords = new LinkedList<Integer>();
	
	/**
	 * Constructor for configuration.
	 * @param modset
	 */
	public MarsConfiguration(ModuleSet modset, LinkedList<MarsModule> modlist) {
		this.modset = modset;
		this.modlist = modlist;
	}
	
	
	/**
	 * Method used to move a module within an individual config.
	 */
	public void setCoords(int index, int xcoo, int ycoo) {
		modlist.get(index).setX(xcoo);
		modlist.get(index).setY(ycoo);
	}
	
	public Integer getXCoord(int index) {
		return modlist.get(index).getX();
	}
	
	public Integer getYCoord(int index) {
		return modlist.get(index).getY();
	}
	
	/**
	 * Adds the module to the list.
	 * @param mod The module to add.
	 */
	public void addModule(MarsModule mod) {
		modlist.add(mod);
		
	}
	
	
	/**
	 * Removes a module from the list
	 * @param mod The module to add.
	 */
	public void removeModule(int i) {
		modlist.remove(i);

	}
	
}
