package project.backend;

import java.util.LinkedList;

/**
 * This class is responsible for storing data into text files and reading them
 * from textfiles.
 * @author Rob
 *
 */
public class Storage {

	String file;
	
	/**
	 * The constructor simply gets the filename for the storage class.
	 * @param filename. The file to work on
	 */
	public Storage(String filename) {
		file = filename;
	}
	
	/**
	 * This method reads from a file name
	 * @return modset. Returns a configuration class representing the list of modules.
	 */
	public Configuration readFile() {
		Configuration config = new Configuration();
		LinkedList<MarsModule> modlist = new LinkedList<MarsModule>();	
		
		
		
		return config;
	}
	
}
