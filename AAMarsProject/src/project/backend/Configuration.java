package project.backend;

import java.util.LinkedList;


/**
 * This class contains a list of modules in different positions.
 * @author Rob
 *
 */
public class Configuration {
	//Define private variables.
	private LinkedList<MarsModule> moduleList = new LinkedList<MarsModule>();
	
	
	public Configuration() {
		
	}
	
	/**
	 * Default constructor for configuration.
	 */
	public Configuration(LinkedList<MarsModule> list) {
		
		this.moduleList = list;
		
	}
	
	
}
