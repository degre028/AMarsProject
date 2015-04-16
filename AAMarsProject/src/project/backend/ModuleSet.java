package project.backend;

import java.util.LinkedList;


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
	
	/**
	 * Constructor for ModuleSet.  This class will instantiate the first configuration.
	 */
	public ModuleSet() {
		modList = new LinkedList<MarsModule>();
		
	}
	
	/**
	 * Adds the module to the list.
	 * @param mod The module to add.
	 */
	public void addModule(MarsModule mod) {
		modList.add(mod);
	}
	
	public void removeModule(int i) {
		modList.remove(i);
	}
	
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
	
	public MarsModule getModule(int i) {
		return modList.get(i);
	}
	
}
