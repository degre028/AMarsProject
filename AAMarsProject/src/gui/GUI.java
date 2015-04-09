package gui;

import backend.Configuration;

/**
 * Large, primary class for the GUI aspects of the lab.  
 * Each GUI should contain and build all visual elements of the
 * MARS simulator.
 * @author Milan
 *
 */

public class GUI {
	/**
	 * Global variable for the live Configuration.  
	 * Modules and such are referred to by public methods in liveConfig.
	 */
	private Configuration liveConfig;
	
	
	/**
	 * Constructor for GUI, takes in the default configuration and assigns it, should instantiate the GUI classes
	 * and build the UI displayed in the onModuleLoad method (main method).
	 */
	public GUI(Configuration passedConfig) {
		liveConfig = passedConfig;
		
		//Code for GUI here.
	}
	
}
