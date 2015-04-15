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
	
	LinkedList<Module> modList;
	
	/**
	 * Constructor for ModuleSet.  This class will instantiate the first configuration.
	 */
	public ModuleSet() {
		modList = new LinkedList<Module>();
	}
	
	public void addModule(Module mod) {
		modList.add(mod);
	}
	
	public int getCount(String type) {
		int count = 0;
		for(int i=0; i<modList.size(); i++)
			if(modList.get(i).getType().equals(type)) {
				count++;
			}
			return count;
	}
	
}
