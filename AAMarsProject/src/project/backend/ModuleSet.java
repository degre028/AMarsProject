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
	LinkedList<Configuration> configList = new LinkedList<Configuration>();
	GUI gui;
	MarsStorage storage;
	String user;
	GraphicPack graphics;
	
	/**
	 * Constructor for ModuleSet.  This class will instantiate the first configuration.
	 */
	public ModuleSet(String user, GraphicPack graphics) {
		modList = new LinkedList<MarsModule>();
		this.user = user;
		storage = new MarsStorage(this);
		this.graphics = graphics;
		gui = new GUI(this,user);
		configList.add(new Configuration(this, modList));
		
	}
	
	/**
	 * Adds the module to the list.
	 * @param mod The module to add.
	 */
	public void addModule(MarsModule mod) {
		modList.add(mod);
		storage.saveToLocalStore();
	}
	
	/**
	 * Adds the module to the list at a given index.
	 * @param mod The module to add.
	 */
	public void addModule(int i, MarsModule mod) {
		//modList.add(mod);
		modList.add(i, mod);
		storage.saveToLocalStore();
	}
	
	
	/**
	 * Removes a module from the list
	 * @param mod The module to add.
	 */
	public void removeModule(int i) {
		modList.remove(i);
		storage.saveToLocalStore();
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
	
	/**
	 * Returns the associated storage object.
	 * @return storage
	 */
	public MarsStorage getStorage() {
		return storage;
	}
	
	
	/**
	 * Getter for the graphics pack.
	 * @return
	 */
	public GraphicPack getGraphics() {
		return graphics;
	}

	
	
}
