package project.backend;

import java.util.LinkedList;


/**
 * This class contains a list of modules in different positions.
 * @author Rob
 *
 */
public class Configuration {
	//Define private variables.
	private ModuleSet modset;
	private LinkedList<MarsModule> moduleList = new LinkedList<MarsModule>();
	private LinkedList<Integer> xCoords = new LinkedList<Integer>();
	private LinkedList<Integer> yCoords = new LinkedList<Integer>();
	
	/**
	 * Constructor for configuration.
	 * @param modset
	 */
	public Configuration(ModuleSet modset) {
		this.modset = modset;
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			moduleList.add(modset.getModule(i));
		}
	}
	
	/**
	 * Default constructor for configuration.
	 */
	public Configuration(LinkedList<MarsModule> list) {
		this.moduleList = list;
	}
	
	/**
	 * Method used to move a module within an individual config.
	 */
	public void setCoords(int index, int xcoo, int ycoo) {
		moduleList.get(index).setX(xcoo);
		moduleList.get(index).setY(ycoo);
	}
	
	public Integer getXCoord(int index) {
		return moduleList.get(index).getX();
	}
	
	public Integer getYCoord(int index) {
		return moduleList.get(index).getY();
	}
	
	/**
	 * Adds the module to the list.
	 * @param mod The module to add.
	 */
	public void addModule(MarsModule mod) {
		moduleList.add(mod);
		
	}
	
	
	/**
	 * Removes a module from the list
	 * @param mod The module to add.
	 */
	public void removeModule(int i) {
		moduleList.remove(i);

	}
	
}
