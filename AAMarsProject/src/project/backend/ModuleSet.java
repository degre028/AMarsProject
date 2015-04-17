package project.backend;

import java.util.LinkedList;

import project.gui.GUI;


/**
 * This class is the class that represents the current list of modules the user has entered.
 * It contains lists of modules and configs that have been generated.
 * 
 * @author Rob
 *
 */
public class ModuleSet {
	//Private variables
	LinkedList<MarsModule> modList;
	GUI gui;
	
	/**
	 * Constructor for ModuleSet.  This class will instantiate the first configuration.
	 */
	public ModuleSet() {
		modList = new LinkedList<MarsModule>();
		gui = new GUI(this);
	}
	
	/**
	 * Adds the module to the list.
	 * @param mod The module to add.
	 */
	public void addModule(MarsModule mod) {
		modList.add(mod);
	}
	
	
	/**
	 * Removes a module from the list
	 * @param mod The module to add.
	 */
	public void removeModule(int i) {
		modList.remove(i);
	}
	
	
	/**
	 * Returns a count of the modules based on an inputted string.
	 * @param type String representation of the type of module to research.  A user can type "all" to
	 * get the size of the module list.
	 */
	public int getCount(String type) {
		
		if(type.equals("all")) return modList.size();
		
		int count = 0;
		for(int i=0; i<modList.size(); i++) {
			if(modList.get(i).getType().equals(type)) {
				count++;
			}
		}
		return count;
		
	}
	
	/**
	 * Gets a specific index of module from the list.
	 * @param i
	 * @return
	 */
	public MarsModule getModule(int i) {
		return modList.get(i);
	}
	
	/**
	 * RReturns the current GUI wrapper.
	 * @return
	 */
	public GUI getGui() {
		return gui;
	}
	
}
