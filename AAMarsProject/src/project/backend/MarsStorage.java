package project.backend;

import java.util.LinkedList;

import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

/**
 * This class is responsible for storing data into text files and reading them
 * from textfiles.
 * @author Rob
 *
 */
public class MarsStorage {

	ModuleSet modset;
	Storage localStorage;
	boolean localFail = false;
	
	/**
	 * The constructor simply gets the filename for the storage class.
	 * @param filename. The file to work on
	 */
	public MarsStorage(ModuleSet mset) {
		modset = mset;
		localStorage = Storage.getLocalStorageIfSupported();
		
		if (localStorage == null) {
			localFail = true;
		}
		
	}
	
	/**
	 * This method reads from the HTML file for sample data.
	 * @return modset. Returns a configuration class representing the list of modules.
	 */
	public void readServerHtml() {
		Configuration config = new Configuration();
		LinkedList<MarsModule> modlist = new LinkedList<MarsModule>();
		

		 Dictionary d;
		
		 d = Dictionary.getDictionary("dataset");
		 int numbmods = Integer.parseInt(d.get("number"));
		
		try {
			 for (int i = 1; i <= numbmods; i++) {
			 d = Dictionary.getDictionary("module"+i);
			 
			 Integer modID = Integer.parseInt(d.get("code"));
			 String modCond = d.get("status");
			 Integer modOri = Integer.parseInt(d.get("turns"));
			 Integer modX = Integer.parseInt(d.get("X"));
			 Integer modY = Integer.parseInt(d.get("Y"));
			 
			 modset.addModule(new MarsModule(modX, modY, modID, modCond, modOri));
			 
			 
			 }
			} 
			catch (Exception e) {
				Window.alert("Bad File Read! " + e.getMessage());			
			}
		
		
	}
	
	/**
	 * This method writes the contents of the modules to the local store.
	 */
	public void saveToLocalStore() {
		
		
		try {
		 if (!localFail) {
			 
			 StringBuilder mainSave = new StringBuilder();
			 mainSave.append("[");
			 
			 Integer i;
			 
			 for (i = 0; i < modset.getCount("all"); i++) {
				 
				 StringBuilder modString = new StringBuilder();
				 MarsModule mod = modset.getModule(i);
				 
				 modString.append("{id:");
				 modString.append(mod.getID());
				 modString.append(",xcoord:");
				 modString.append(mod.getX());
				 modString.append(",ycoord:");
				 modString.append(mod.getY());
				 modString.append(",orientation:");
				 modString.append(mod.getOrientation());
				 modString.append(",condition:\"");
				 modString.append(mod.getCondition());
				 modString.append("\"}");
				 
				 mainSave.append(modString);
				 
				 if(i != modset.getCount("all")) {
					 mainSave.append(",");
				 }
				 
			 }
			 

			 //Write to local storage.
			 localStorage.setItem("number",i.toString());
			 localStorage.setItem("modlist", mainSave.toString());
		 
		 }
		}
		catch (Exception e) {
			 Window.alert(e.getMessage());
		}
		
	}
	
	
	
	
}
