package project.backend;

import java.util.LinkedList;


/**
 * This class contains a list of modules in different positions.
 * @author Rob
 *
 */
public class Configuration {
	//Define private variables.
	private LinkedList<Module> moduleList = new LinkedList<Module>();
	
	
	public Configuration() {
		
	}
	
	/**
	 * Default constructor for configuration.
	 */
	public Configuration(LinkedList<Module> list) {
		
		this.moduleList = list;
		
	}
	
	
}
